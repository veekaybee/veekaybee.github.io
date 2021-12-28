+++
date = '2018-01-28T00:00:00Z'
title = 'Working with AWS'

+++

<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@vboykis">
<meta name="twitter:creator" content="@vboykis">
<meta name="twitter:title" content="Working with AWS">
<meta name="twitter:description" content="AWS is an extremely flexible environment to work in, but one that comes with a lot of choices.">
<meta name="twitter:image" content="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/clouds.jpg!Blog.jpg">

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/clouds.jpg!Blog.jpg)

**Clouds, Isaac Levitan**

_**TL;DR:** AWS is an extremely flexible environment to work in, but the flexibility can lead to an overwhelming amount of choices that may initially overwhelm and make someone used to working on-prem less productive until they get the hang of it._

More and more of my projects lately have been either doing data science on AWS, or moving data into AWS for data science, and I wanted to jot down some brief thoughts on coming from an on-prem background about what to expect from working in the cloud. 

For some context, in my day-to-day, I work with a variety of tools, including Spark,R, Hadoop, and machine learning libraries like scikit-learn, and plain vanilla Python, so my experience with AWS is coming from that perspective. 


## Get ready to send a lot of JSON 

In on-prem environments, I've usually had direct access to data through direct software interfaces.  Some examples of these have been JDBC/ODBC SQL connections, secure FTP folders, or direct connections to on-prem (or vendor-managed, but behind a company firewall) HDFS volumes through Spark. 

The overarching goal of AWS is to abstract away anything that can't be accessed through a REST protocol, meaning that, instead of dealing with SQL UI tools, direct Spark shell access, or RStudio, I found myself dealing with a lot of command line scripts that passed JSON data structures as configurable parameters. 

To understand why AWS does this,  check out [this fantastic post on the mandate Jeff Bezos's issued around 2002](https://plus.google.com/+RipRowan/posts/eVeouesvaVX)
	
	>  1) All teams will henceforth expose their data and functionality through service interfaces
	> 2) Teams must communicate with each other through these interfaces.

These days, these interfaces are now all customer-facing, and accessible through JSON. 

Here's a specific example. If I wanted to move some data that landed in a shared directory to, say, an Oracle database, I'd probably set up an ETL job, either by writing some [cron](https://code.tutsplus.com/tutorials/scheduling-tasks-with-cron-jobs--net-8800) scripts, or using a tool like Pentaho [to set up an ETL job](https://wiki.pentaho.com/display/EAI/Move+files) to communicate between the data directory and Oracle using JDBC. All of this would involve combination of cron scripts, possibly custom Java for Pentaho, and UI-generated transformation steps.  

In AWS, what I could do would be to set up file movement from S3, the object storage service, by triggering a lambda function (for more on lambdas, [read on](#lambdas)) to write to Redshift, [a common data warehousing solution in S3.](https://aws.amazon.com/redshift/). 

My steps would include: 

1) Creating a [bucket:](https://docs.aws.amazon.com/cli/latest/userguide/using-s3-commands.html) 

```
aws s3api create-bucket --bucket my-bucket --region eu-west-1 --create-bucket-configuration LocationConstraint=eu-west-1
```

The results of which would be: 

```
{
    "Location": "http://my-bucket.s3.amazonaws.com/"
}

```

Then, I'd create a lambda that accesses that bucket:

```
aws lambda create-function \
--region region \
--function-name helloworld \
--zip-file fileb://file-path/helloworld.zip \
--role role-arn \
--handler helloworld.handler \
--runtime python3.6 \
--profile adminuser 
```

Here's what that POST request syntax looks like behind the scenes in JSON [based on a script from an S3 bucket:](https://docs.aws.amazon.com/lambda/latest/dg/API_CreateFunction.html) 

```
	{
   "Code": { 
      "S3Bucket": "string",
      "S3Key": "string",
      "S3ObjectVersion": "string",
      "ZipFile": blob
   },
   "DeadLetterConfig": { 
      "TargetArn": "string"
   },
   "Description": "string",
   "Environment": { 
      "Variables": { 
         "string" : "string" 
      }
   },
   "FunctionName": "string",
   "Handler": "string",
   "KMSKeyArn": "string",
   "MemorySize": number,
   "Publish": boolean,
   "Role": "string",
   "Runtime": "string",
   "Tags": { 
      "string" : "string" 
   },
   "Timeout": number,
   "TracingConfig": { 
      "Mode": "string"
   },
   "VpcConfig": { 
      "SecurityGroupIds": [ "string" ],
      "SubnetIds": [ "string" ]
   }
}
```
Alternatively, passing it in Python would look [something like this.](https://boto3.readthedocs.io/en/latest/reference/services/lambda.html#Lambda.Client.create_function) 

As a result, I've gotten very friendly with all sorts of JSON structures: 

+ simple objects, `{'key': 'value}` that map directly to Python dictionaries,

+ arrays that map to dicts with list values `{key', : [value, value value]}` 

+ and, my favorite, nested arrays that map to lists of dictionaries nested in lists: `{'key': [{'inner_key':'inner_value'}]}`

Fortunately, the Python dictionary is a workhorse data structure that's easy to loop through and reference. Even still, there are a couple of Python dictionary methods that have made working with JSON in AWS much easier for me: 

1) [`items`](https://docs.python.org/3/tutorial/datastructures.html#looping-techniques) - which accesses keys and values and loops through the dictionary. Remembering this syntax makes accessing elements easier. 
2)  The `collections` library, particularly [OrderedDict and defaultdict](https://docs.python.org/3.3/library/collections.html)  which will enforce order and missing values on your dictionaries (although order for dictionaries will be a default in Python 3.6...[kind of](https://mail.python.org/pipermail/python-dev/2016-September/146348.html))
3) The [`json` library](https://docs.python.org/3/library/json.html), particularly `load` and `dump`. Usually the AWS SDK and command line tools take care of this for you, but there are times when you'll want to create some JSON in the CLI to test out. This library is specifically designed to convert Python dictionaries to JSON data structures and vice versa, and is good for understanding the internals of JSON structures relative to your code. 

## You'll be interacting with the AWS APIs in lots of different ways

There are three main ways to access AWS, from easiest to most involved:

1) Management Console - UI
2) AWS CLI - Command Line + Some tweaking 
3) AWS SDKs - Full-on writing code 

The contrast between this and on-prem work is that (usually) there's only one way to access a development endpoint if you're working inside a single data center. 

These three different interfaces mean that, often, I'm spending a lot of time split in three different development modes: making one-time manual changes to IAMs in the console, running some Linux file-system like commands doing inventory in AWS CLI (for example, listing bucket directories,) and building an application made of several components using `boto3` in PyCharm. 

In this way, AWS can really divert your attention if you let it. That is, there is no pre-defined workflow like there can be in on-prem environments, and you really have to make cognizant choices about how you set up your processes and why. 

For example, if you're looking to create an MLLib job doing linear regression in Spark, in an on-prem environment, you'd SSH into your Spark cluster edge node, and write a script accessing HDFS data, to be run through spark-submit on the cluster. 

In AWS, you could potentially do the same thing through EMR. You could spin up an EMR cluster using either the management console or CLI, or even the SDK, and run a job any of those three ways, as well. None is inherently better than the other; or at least, Amazon doesn't tell you it is, which is where the issue is - you'll have to figure out what combination of flexiblity/reproduceability works for your given workflow. 

Now, a closer look at each three of the environments: 

First, there's the **Management Console**, which was my first point of entry into AWS. You can do many basic things with the services there, like create lambdas, set triggers, upload files to S3 buckets, set up EC2 instances, and much, much more.  But the UI doesn't let you replicate what you've done or automate certain processes. 

Second, there's ***AWS CLI***.  It's a [Python-based tool](https://aws.amazon.com/cli/) that you can install (`pip install awscli`) and run recurrent commands with. Once you run it the first time, it will also configure with your [local AWS credentials file](https://docs.aws.amazon.com/cli/latest/userguide/cli-config-files.html), which is a must-have for working with AWS. 

For example, you can take a look at all of your S3 buckets with `aws s3 ls`, or bootstrap [an EMR instance](https://docs.aws.amazon.com/cli/latest/reference/emr/create-cluster.html) `aws emr create-cluster --release-label emr-5.3.1  --instance-groups InstanceGroupType=MASTER,InstanceCount=1,InstanceType=m3.xlarge InstanceGroupType=CORE,InstanceCount=2,InstanceType=m3.xlarge --auto-terminate` . You can customize many of these commands by passing in config files that are stored in - you guessed it- JSON in S3 buckets. 

Since a lot of these commands get repetitive, or need to be run again and again (for example, spinning up multiple clusters, or continuing to move code to AWS), I've started to [enrich cli commands](https://blog.sethbergman.com/aws-ec2-automation-using-bash-scripts/) with bash scripts. 

Finally, there are SDKs, which allow you to develop more complex applications outside of the command line.  I really like using `boto3`, [the Python SDK](http://boto3.readthedocs.io/en/latest/), because the documentation is pretty nicely done. The only pain point is that there are numerous different ways to do the same thing. 

For example, in order to access an S3 bucket, you can call  a resource, a client, or a session.  Just to connect to S3, you can do: 

```
s3 = boto3.resource('s3') # resource
boto3.session(region_name = 'us-east-1') # session
s3 = boto3.client('s3')  # client

```

At a high level, resources are similar to objects, sessions manage connections between various AWS profiles, and clients are per-service connections. Crystal clear? 

[This](https://stackoverflow.com/questions/42809096/difference-between-resource-client-and-session) and [this](http://www.oznetnerd.com/python-demystifying-aws-boto3/) are good reads about the top-level differences between the nuances. 


## May I have some permissions, please?

Since you are pushing data to and from someone else's servers, Amazon is very strict about what any given action can do, and each action has fine-grained security permissions, to the point where you sometimes feel like you're navigating through the bureacracy of an embassy trying to get a passport.  

At a [high level of abstraction,](https://docs.aws.amazon.com/IAM/latest/UserGuide/access_controlling.html) 

> AWS is composed of collections of resources.  An IAM user is a resource. An Amazon S3 bucket is a resource. When you use the AWS API, the AWS CLI, or the AWS Management Console to take an action (such as creating a user), you send a request for that action. Your request specifies an action, a resource, a principal (group, user, or role), a principal account, and any necessary request information. All of this information provides context.

There is also a [helpful diagram.]( https://docs.aws.amazon.com/IAM/latest/UserGuide/intro-structure.html)

At an even more abstract level, it helps to understand that IAM is the central place that manages permissions, and it grants out users, and roles, which can write or read to and from the various services on AWS. There's also the concept of policies, which define what users and roles can access within the context of a service. 

This was a completely new ballgame for me, because, other than setting up Jira tickets to get access to, say, Oracle, or admin access on my laptop, I never really had to think about whether the services I used were secure. 

In this context, since everything is abstracted away from you onto a different server, every POST/GET request you make needs to be authenticated correctly, and services within AWS also need to talk to each other securely in order to keep track of who's accessing what. 

## Before you can read the docs, you'll need to find the docs

AWS has a LOT of documentation, and it's not great. I've often riffed on Twitter about how terrible it is as a way of letting off some steam about it,  

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/docs1.png)

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/aws3.png)


but in a way, it makes sense. In the beginning of the post I linked to Bezos's famous edict, decreeing that 

>All teams will henceforth expose their data and functionality through service interfaces.

meaning that the APIs themselves are extremely well-documented, but what happens after you call an API, and what the implications are, are not. This culture seems to have carried through to the modern day. The JSON parameters and CLI calls do have good documentation, but there are few examples, and few explanations, particularly for newer or less-used services. 

For example, the documentation for creating an S3 bucket [doesn't explictly have a link to the Python SDK,](https://docs.aws.amazon.com/AmazonS3/latest/dev/create-bucket-get-location-example.html), probably one of the most common ways to create buckets, and points instead to the "Sample Code and Libraries" section, which, after some navigation, has a place to download a link to awspylib, which is not the official Python documentation (Boto is). 

Much like the API itself, the documentation is not opinionated, and there is no single, concrete place to get started with anything, no mind map or understanding of how documentation is written and structured. In a way, finding your way through AWS documentation is not unlike being dropped into a library without a map, where the books are not ordered alphabetically, and the pages in the books are constantly being rewritten. 

It makes sense from a business perspective. Writing documentation is often the hardest and most time-consuming part of development with more returns to the end users than to developers.  And,  since Amazon is the market leader in cloud, it has no incentive to improve it in the way that, say, Digital Ocean has to maintain a competitive edge with friendly and accessible documentation. 

All of this is to say, I spent a lot of time banging my head against finding concrete examples of AWS CLI recipes I wanted to follow, a lot of time sharing snippets of code with other developers on my team, and much, much more time on StackOverflow, in the amazon-web-services tag. 

<a name="lambdas"></a>
## Learning to love lambdas

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/bemylambda.png)

[Lambdas](https://aws.amazon.com/lambda/) - not to be confused with Python anonymous lambda functions are fantastic.  I've used them in several projects and each time I find a great new use case for them. 

In short, they are the glue that holds AWS processes together. Lambdas are temporal services that run in response to given triggers in the AWS ecosystem. 

Here's a short list of things they can do ([much more](https://docs.aws.amazon.com/lambda/latest/dg/invoking-lambda-function.html#intro-core-components-event-sources) in the docs): 

+ Run when a file is placed in an S3 bucket and move the file to another location
+ Process logs with Kinesis streams and store the logs in DynamoDB
+ Compile a static website based on a new code change and reload the site
+ Backup files and put them into cold storage

And much, much more. Think of them as small functions that can be called across the AWS universe. 

A good thing to keep in mind is the limitations of lambdas: 

+ 512 MB disk space
+ Execution timeout after 5 minutes of activity
+ 128 to 1536 MB
+ Have [boto included](https://docs.aws.amazon.com/lambda/latest/dg/current-supported-versions.html) as a runtime library 

And, a really neat thing about them is that they have temporary file storage (Each lambda has 500MB of non-persistent disk space in its own /tmp ) so [you can perform operations inside the lambda](https://stackoverflow.com/questions/35641994/accessing-local-filesystem-in-aws-lambda/35642189) at runtime.  
![](lambda.png)

I've only begun to explore their full capabilities in my projects, but, coming from environments where communication between services was tedious and required a compelte build out of new ETL tools, lambdas are lightweight, relatively easy to use, and one of the few recent development tools that I've found to be fun. 


## I heard you like services. Here's 131 of them (including Glue)

Another interesting service I had the chance to work with is [Glue](https://aws.amazon.com/glue/), one of AWS's newest offerings. [Glue was introduced](https://www.youtube.com/watch?v=4N_ktE4NFIk) in December 2016 and launched to consumers late in the spring, so it's just over six months hardened at this point. 

At launch, it was described as an ETL tool that handles data movement through PySpark on auto-generated EMR instances, creating a metadata catalog of all your data in S3 buckets, and being able to manage those jobs from a single interface. 

This is great, except there are already several similar services. It only makes sense that some overlap, given that AWS currently stands at [131 total services.](https://twitter.com/anildash/status/955476924402487296) For Glue, the analogous services are EMR, Data Pipeline, Kinesis Analytics, and AWS Batch. Which one to use? 

It turns out, that each one has a slightly different use case, and it takes some playing around to figure out if your specific case is covered by the service, whether your data can move between one and the other correctly. 

I found working with Glue to be relatively straightforward, but the real key was trying to figure out which workflow AWS was optimizing the product for. In my case, Glue felt like the right tool if:

A) You have sane and clean S3 bucket structures to pull data from 
B) You have standard, scheduled data flows
C) You just want to move files from S3 into Athena-readable Parquet files or similar
D) You're comfortable with not knowing what your EMR spin-up will look like, or how long it will take
E) You're comfortable with working with Spark code that's 2 layers removed from Spark (wrapped in EMR code, which is then wrapped in Glue code) 

My main takeaway is that each service takes time to evaluate, price, and figure out the API for, whereas in an on-prem instance, you're usually limited to a range of 3-4 tools that you then have to get creative with. 


## Testing is harder 

For both Lambdas and Glue, and many of the other services I worked with, I discovered that testing is harder. 

In on-prem, in-house applications, you write some code, and then write unit tests against that piece of code. You can then also run integration tests to see how those pieces will work with other pieces of code external to the codebase you're working on. 

For example, you can unit test PySpark code by creating a cluster locally and [using pytest fixures](https://engblog.nextdoor.com/unit-testing-apache-spark-with-py-test-3b8970dc013b) to recreate a session and assert your expected results. 

This is harder to do in AWS, because it's next to impossible to recreate an entire AWS environment, with all the services you need, locally. For example, you're trying to put files into an S3 bucket, or create a table in Athena, or stream files through Kinesis, and tie those actions together with Lambdas. 

One way I've tried to fill this gap is with [moto](https://github.com/spulec/moto), which mocks out (creates dummy copies) of calls to services through boto by making use of Python decorators. 

For example, you can mock out a connection to S3, create a bucket, pass text into that bucket, and, call that "fake bucket" to return text, and make sure that text matches what you have in your code, all without accessing AWS. 

You can't necessarily test what S3 will pass to other AWS services, but you can at least test parts of your code locally. The rest involves testing job runs in an AWS dev account, meaning that you are racking up costs for each test you run. 

This brings me to: 

##  **You're paying Amazon money every time you make a typo** 

This was hard to wrap my head around since, in an on-prem environment, you pay for servers up front and depreciate those costs over time. You're not paying to spin up a Hadoop cluster, or for every job run. In AWS, you are. 

So, every time you test something in AWS, and you forget a semicolon or a tab, or point the service to the wrong directory, you're paying money for your mistakes.  

[Every single action you perform](http://calculator.s3.amazonaws.com/index.html), from loading, to storing files, to running models, to saving those models back out, incurs some sort of fractional cost that is hard to keep track of. I've groused before about how hard it is to estimate your AWS costs for the month ahead.

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/docs2.png)

and I think that still mostly holds. Because AWS is so flexible, there are a million different options to consider for each possible service, each byte of data you move, each user permission, region, and availability zone.  

It's easy to approximate costs, but harder to pinpoint, and even harder to forecast. The best thing I did was set up [Billing Alerts](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/monitor_estimated_charges_with_cloudwatch.html) to monitor proactively. 

## Summary

Overall, if I had to describe my experience of working with AWS, I'd say that it requires a different mindset than on-prem. Instead of having everything included and being locked into a particular environment, AWS is a lot more granular. You're trading lock-in for flexibility, with the caveat that what you get may be too flexible, and with the knowledge that AWS doesn't give you any opinions on how to work with what you have. It's on you to make the right choices, with the consequence that you can also make more mistakes along the way, but once you figure out how to best leverage the environment, you can get a lot out of it.   

Thanks to [Mark Roddy](https://twitter.com/digitallogic), [Jowanza Joseph](https://twitter.com/Jowanza), and [Austin Rochford](https://twitter.com/austinrochford) for reading drafts of this post. 





 
 



