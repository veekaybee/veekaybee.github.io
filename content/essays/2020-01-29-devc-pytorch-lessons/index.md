---
title: 3 Things I learned on DevC Bandung Meetup (Pytorch)
date: "2020-01-29T00:00:00.000Z"
description: "This note is about what I learnt from FB Bandung DevC Meetup last Wednesday (2020-01-22)...."
featuredImage: './facebook_devc_Jan.jpg'
---

<!-- TODO: Add Event Photos -->
This note is about what I learnt from FB Bandung DevC Meetup last Wednesday (2020-01-22), to be specific it is about Amajid Sinar Guntara - Jedi's talk on using Pytorch from research to deployment. His original slide can be found on Bandung DevC Facebook Group. AS this note is about what I learnt, I may not cover all of his talk and will focus more on what interests me and also serve as my cue on what I want to learn next.

For a bit of background, Jedi is an AI Engineer at Nodeflux which uses Pytorch for Industrial application (as opposed to research usage) where the constraint that he faced is not only about the accuracy and speed of inference, but also about the hardware limitation. As in some cases he needed to implement the model in edge devices. So in the implementation he has the incentive to be able to optimize the model to also cater to the Hardware limitation.

With that in mind, let's get into what I learnt during and after the talks. Disclaimer : the following may differ greatly on what was actually in the talk, as I have already mix and blend the information with what I know and what I found online.

## 1. So there is this thing called Static and Dynamic Graph

In Deep learning / Maachine Learning Frameworks or Libraries there are different ways the libraries/ frameworks handle your model in terms of how they define and run your model. For rough comparison we can refer to the following table:

| Property      | Static        | Dynamic       |
| ---------     | :--------:    | :--------:    |
|Execution Mode |Non-Eager      |Eager          |
|Debug-ablity   |Hard           |Easy           |
|Speed          |Fast           |Slow           |
|Example        |Tensorflow     |Pytorch        |

In environment where rapid changes and experiments are needed, dynamic graph wins over static graph especially on the ease of debugging and its capability of eager execution (define-by-run) which makes libraries with dynamic graph feels more _natural_.

After searching around a bit, I came upon this article about [the state of ML frameworks in 2019](https://thegradient.pub/state-of-ml-frameworks-2019-pytorch-dominates-research-tensorflow-dominates-industry/) that mentioned the popularity of Pytorch in research, and one of its reason is for the ease of debugging Pytorch Models. Another interesting point that may worth to be explored is that while dynamic graph is said too be slow, the article mention of anecdotal reports about Pytorch performance being as fast (if not better than) Tensorflow (with static graph)

Another thing that surprised me was the realisation that He used Pytorch in production (I should have realised from the title of the talk, but yeah I just realised it). Because all this time I was under the assumption that when deploying the model in production companies used Tensorflow.

__Things to explore :__ Other companies that use Pytorch in production and how they do them?, are there anyway to _translate_ pytorch model into Tensorflow for deployment?, What makes static graph harder to debug and how worthy is the difficulty?

## 2. It is not that Easy to Implement Pytorch in Production

In my mind, to deploy pytorch model I could just export the _model_ and _put it_ into cloud server with pytorch installed and run it from there when someone hits certain endpoint as mentioned in [one of pytorch tutorial on deployment](https://pytorch.org/tutorials/intermediate/flask_rest_api_tutorial.html). It turns out that it is more complicated than I thought. Especially if there are hardware limitation and the overhead introduced by running python on the server (I want to fact check this later). Luckily there are several solution for this problem, they are:

- Torch JIT or TorchScript (from Pytorch)
- Open Neural Network Exchange - ONNX (from Microsoft)

### Torch JIT

Basically Torch JIT _"compiles"_ pytorch functions into an _Intermediate Representation_ called TorchScript that can be run in [high performance environment such as C++](https://pytorch.org/tutorials/beginner/Intro_to_TorchScript_tutorial.html) without Python dependencies, and possibly [optimizes it](https://pytorch.org/blog/optimizing-cuda-rnn-with-torchscript/). In implementation there are two ways of doing it, by __Tracing__ and __Scripting__.

With  __Tracing__ we _trace_ how an input go through all the operation performed on all the tensors. As this is ideal on code that only operates on Tensors, list, dictionaries, and tuples of Tensors. Because of the _tracing_ nature, it is not suitable for model with flow control operation with possibly dynamic length (like RNN). For those kind of model it is recommended to use __scripting__

As for __Scripting__ it can be as simple as adding decorators to a function or using method in Torch JIT to compile a module into its TorchScript version. But because TorchScript itself is only a subset of Python Language, it is a good idea to refer to the [documentation](https://pytorch.org/docs/stable/jit.html#torchscript-language-reference) to get a better view on the capabilies.

### ONNX

In the talk Jedi didn't delve deep into ONNX (as currently he didn't actively using it), and basically explains that ONNX may be an important tools to learn (or at least know) for people aspiring to work as data scientist. As this tool (as it name suggests) will help in implementing model from a library onto a wide arrange of devices and applications. (Need Reference)

__Things to explore :__ Limitation of ONNX and current state of using Pytorch with it, what can`t be done using Torch JIT __Scripting__?, is the overhead of python really that significant ?

## 3. Some Companies that Need Realtime Application Used Pytorch

Knowing that several companies use Pytorch in production was already a surprise for me, but knowing that even it is used for realtime application added another layer of surprise for me. Jedi mentioned that in Nodeflux several applications required the system to generates inference in _realtime_, he didn't specifically mentioned what jobs is inferred, but I got the impression of it as a job that more or less analyzing from a video and locating if there are certain _object_ inside it, wheteher human or car, etc.

And later he mentioned that [Tesla also uses Pytorch for their stack](https://www.youtube.com/watch?v=oBklltKXtDE) and for a second I thought that they also use Pytorch for the inference part (the one that needed real time processing). But as it turns out, in the video You can see that he only mentioned using Pytorch for training and didn't say anything about using it in inference. Well it's just my assumption getting ahead of myself.

__Things to explore :__ What Tesla uses for their real time inference?, Any other company that uses Pytorch for real time applications?

## Additional Thought

Those are three things that I learned from the last DevC Meetup, but there are several things that I didn't know how to put into the above three things. Thus I will dump it in the notes bellow:

- Jedi mentioned about Software Engineering team being the one who is in charge of optimizing the torchscript in Nodeflux, why is that so and what kind of software engineering needed to optimizes the model.
- I asked Jedi about TF 2.0 eager execution mode and he answered that even though it is eager, it can be really slow compared to pytorch. And he mentioned about it caused by TF naturally using static graph and converting it into dynamic graph and back into static graph to make it eager. At that time I didn't think much about it, but it turns out that I didn`t really understand after all.
- At the start he mentioned about doing inference in edge devices, and later I asked him about how edge the device is for deep learning application. It turns out that not all inference is done in edge device, several higher load may be distributed to another device (whether another computer or the cloud). And this made me thinking about what kind of design decision that would require me to put my model in edge devices.

Aaand, that`s a wrap. If You spot any mistakes or wrong information in my notes please let me know, and if You want to discuss anything with me hit me up at Twitter [@BanditelolRP](https://twitter.com/BanditelolRP). Thanks for reading.

