+++
card = 'summary'
creator = '@vboykis'
date = '2022-07-25'
site = '@vboykis'
title = "How to prepare an AWS EC2 image for PyTorch"
description = 'Easy once you already know how to do it'
+++

I've been getting started in open-source development with PyTorch, starting with [running and testing the examples for Pytorch in distributed mode.](https://github.com/pytorch/examples/pull/988) Big thanks [to Mark](https://twitter.com/marksaroufim) for reviewing and merging my first PR. 

My current Macbook Pro doesn't support using PyTorch with GPUs, although as of [1.12 that's changed](https://pytorch.org/blog/introducing-accelerated-pytorch-training-on-mac/) and you can read Sebastian's [review of his experience with it here](https://sebastianraschka.com/blog/2022/pytorch-m1-gpu.html). But for me, the easiest way to run PyTorch and its associated tests is to spin up a relatively small GPU-based instance in AWS for testing (GCP offers similar functionality) and then tear it down. 

I've updated the instructions [in the official docs](https://github.com/pytorch/examples/blob/main/CONTRIBUTING.md#for-bug-fixes), but thought I'd add them here as well, mostly as reference to myself for how to do it. 

These instructions assume you have: 
1) An AWS account and 
2) [AWS CLI set up](https://aws.amazon.com/cli/)
3) A [unique key-pair](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-key-pairs.html) for logging into your instance


We'll be spinning up  a `g4dn.4xlarge` [instance](https://aws.amazon.com/ec2/instance-types/g4/). This is the lowest-cost GPU instance and is fine for a couple hours of testing example runs. If your model is large and memory-intensive, you'll want something larger. 

Current specs (as of summer 2022) are: `1 GPU, 16 vCPUs, 64 GiB of memory, 225 NVMe SSD, up to 25 Gbps network performance`. The cost is $1.20/hour, so if you accidentally leave it running for a month, [it's going to cost ~$560.](https://calculator.aws/#/estimate) One quick way to pre-empt this is to set up [billing alarms.](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/monitor_estimated_charges_with_cloudwatch.html) 

We'll be creating this instance from the AWS CLI, although you can also do this from the console, just takes a bit longer. 

From the command line, run the command to create a new EC2 instance: 

```bash
--image-id - the ID of the g4dn.4xlarge AMI
--instance-type - g4dn.4xlarge, our Deep Learning Instance Type
--key-name pytorch - the EC2 key you created
--security-groups [your security group] - make sure this security group has ingress/egress for port 80, 22, and 443
```

```bash
aws ec2 run-instances --image-id ami-0403bb4876c18c180 --instance-type g4dn.4xlarge --key-name pytorch  --security-groups [your security group]
```

Once it's set up, ssh into it using: 

`ssh -i "yourkey.pem" ubuntu@theinstancename.compute-1.amazonaws.com` (`ubuntu` is the user here)

```bash
=============================================================================
       __|  __|_  )
       _|  (     /   Deep Learning AMI (Ubuntu 18.04) Version 59
      ___|\___|___|
=============================================================================

Welcome to Ubuntu 18.04.6 LTS (GNU/Linux 5.4.0-1069-aws x86_64v)

Please use one of the following commands to start the required environment with the framework of your choice:
for TensorFlow 2.7 with Python3.8 (CUDA 11.2 and Intel MKL-DNN) ____________________________ source activate tensorflow2_p38
for PyTorch 1.10 with Python3.8 (CUDA 11.1 and Intel MKL) ______________________________________ source activate pytorch_p38
for AWS MX 1.8 (+Keras2) with Python3.7 (CUDA 11.0 and Intel MKL-DNN) ____________________________ source activate mxnet_p37
for AWS MX(+AWS Neuron) with Python3 __________________________________________________ source activate aws_neuron_mxnet_p36
for Tensorflow(+AWS Neuron) with Python3 _________________________________________ source activate aws_neuron_tensorflow_p36
for PyTorch (+AWS Neuron) with Python3 ______________________________________________ source activate aws_neuron_pytorch_p36
for AWS MX(+Amazon Elastic Inference) with Python3 ______________________________________ source activate amazonei_mxnet_p36
for base Python3 (CUDA 11.0) _______________________________________________________________________ source activate python3
```
The deep learning instance comes with PyTorch pre-buildt in a conda environment, but these dependencies tend to fall out of sync with each other. Usually it's easier to start from scratch, especially since `examples` [installs its own dependencies.](https://github.com/pytorch/examples/blob/7ed7ac7b01add7ca29d45f25700e73a4b517ccea/run_python_examples.sh#L41) 

So then you can run: 

```bash
conda create --name pytorchenv 
conda install  -c pytorch torchvision cudatoolkit=10.1 
mkdir my_examples 
cd my_examples 
git clone https://github.com/pytorch/examples.git 
./run_python_examples.sh "install_deps"
```

And you should be good to go!

There is an even easier way to do this if you want to avoid having to run a bunch of bash commands, you can also package the commands as part of the install command using `--user-data`, [which pushes data to your image at launch.](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/user-data.html) Here's [more on how user-data works.](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/user-data.html) 

In order to push data to the image, you have to use an instance profile, [which is tied to an IAM role](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_roles_use_switch-role-ec2_instance-profiles.html),and which you can set up using the command line.  


```
aws ec2 run-instances --image-id ami-0403bb4876c18c180 --instance-type g4dn.4xlarge --key-name pytorch --security-groups [your security group] --iam-instance-profile '{"Name": "EC2_Access" }' --user-data file://install_pytorch.sh 
```

In order to make sure that your bash script ran correctly, you can tail the image setup logs, which are located in hese two places: 

```
/var/log/cloud-init.log 
/var/log/cloud-init-output.log
```





