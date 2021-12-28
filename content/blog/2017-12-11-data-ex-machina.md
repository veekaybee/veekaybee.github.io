+++
date = '2017-12-11T00:00:00Z'
title = 'Data ex machina'

+++

<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@vboykis">
<meta name="twitter:creator" content="@vboykis">
<meta name="twitter:title" content="Data ex machina">
<meta name="twitter:description" content="We shouldn't throw away the current software development process for a new paradigm based on neural nets.">
<meta name="twitter:image" content="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/washing-machine-1961.jpg">

![lichtenstein](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/washing-machine-1961.jpg)

Washing Machine (1961), Roy Lichtenstein 

Recently, Andrej Karpathy, [head of AI](http://cs.stanford.edu/people/karpathy/) at Tesla, wrote ["Software 2.0."](https://medium.com/@karpathy/software-2-0-a64152b37c35), describing what he sees as a fundamental shift in the way software works.  Software 1.0 is "explicit instructions to the computer written by a programmer."

Instead of writing lines of code, he writes that, "Software 2.0 is written in neural network weights."

> No human is involved in writing this code because there are a lot of weights (typical networks might have millions), and coding directly in weights is kind of hard (I tried). Instead, we specify some constraints on the behavior of a desirable program (e.g., a dataset of input output pairs of examples) and use the computational resources at our disposal to search the program space for a program that satisfies the constraints.

>It turns out that a large portion of real-world problems have the property that it is significantly easier to collect the data than to explicitly write the program. 

Reading this, I remembered a project I worked on several years ago on a team that was tasked with ingesting and presenting insights from streaming log data. 

Originally, that data was being transformed into entries that could be parsed out into relational tables, but at one point, the throughput became too much for writing linearly to the database. 

The team that was working upstream from us then switched Cassandra for processing and storage.  The conversion was tasking for the developers new to distributed systems, and even worse for us analysts, because we no longer had a way to query the data directly. CQL was [still in its infancy](https://docs.datastax.com/en/cql/3.1/cql/cql_intro_c.html), and, at that time, the only way to use it was to write Java. Additionally, we also had to understand how to query Cassandra's columnar architecture and transpose the results into a relational model that we could then report on.  

As a result, we had to wait a long time to be able to get value out of that data. During that experience, I realized that there are three fundamental parts of any data project: 
+ data
+ creating code that moves or analyzes data 
+ human judgment to interpret the results of parts one and two

If any one of the three are deprioritized, a data project goes awry. 

Karpathy's post asks for a focus on input and output data, rather than software development, while at the same time eschewing human judgment. It's true that a neural network becomes very good at code generation.  But, it can't create or clean the initial input data, which Software 1.0 can do. And, it can't interpret the output, which is a task best left to humans. 

A neural network is a model used to classify, cluster things, or recognize patterns -anything that is similar to the function of the human brain. There are many different kinds of neural nets, all used in different situations. 

The most common example is networks that operate on [MNIST](http://yann.lecun.com/exdb/mnist/), which feeds in handwriting training data and has the computer classify the number into a typewritten category. Other neural nets include [hot dog classifiers](https://aboveintelligent.com/using-tensorflow-to-classify-hotdogs-8494fb85d875) and [self-driving cars](https://devblogs.nvidia.com/parallelforall/explaining-deep-learning-self-driving-car/), which are steered by passing in images of a road and commands saying what a car should do when it encounters objects from those images.   

At its core, a neural net takes a set of input features, `x1`, `x2`, and so on, with associated weights `w1` and `w2`, and a bias (aka beta, or deviation,) and, produces an output, `Y`. `Y` is a classifier that tells you which category your item falls into. 

If this is starting to sound a bit like the equation for a line from algebra class, it's because it is. Logistic regression, which uses  `Y = B0 + B1X1 + B2X2 + e` as its equation, is a very simple example of a neural network that classifies objects into two categories. 

A significant amount of work happens in the middle to reach a decision point, depending on how many layers of inputs and weights you'd like your network to have. Because each input and weight is connected to each one following it in the sense that the functions are dependent on each other to reach a final end state, trying to trace all of the steps of even a simple neural network can be daunting.  Complicating this is that neurons are modified by activation functions, adding an additional layer of complexity to "reverse-engineering" them. 

To find the optimal value of the network, or figure out whether the weights assigned were correct, a process known as backpropagation takes place. Backpropagation starts from the classifiers, and goes in reverse through each layer to find out what the change in weights was, and how tweaking them will reduce the prediction error. 
Ultimately, after training and testing this network, it aggregates each layer, as well as the final neurons, to come up with a label for images, or decide if your car is going off a cliff<sup>[1](#myfootnote1)</sup>.

What Karpathy proposes in his post is that the computer now handles the entire process of moving forward and backward through the model. This seems extremely optimistic. 

All in all, there are several steps to creating the neural net described above: 

1. Data collection 
2. Data cleaning 
3. Feature selection
4. Network design
5. Training
6. Evaluation

The computer can really only take over at steps four and five. To get to that point involves an important combination of writing custom code and human interpretation. 

First, the problem of data generation still makes up a significant part of data science and machine learning. In order to get to a point where you can model, you need to write systems that collect, clean, and perform validation - sometimes [human validation](http://karpathy.github.io/2014/09/02/what-i-learned-from-competing-against-a-convnet-on-imagenet/)  - on that data. Collecting data [is a lot harder](https://twitter.com/fchollet/status/806814476607619072) and figuring out of that data is valid than anyone anticipates. 

Second, data cleaning, feature selection, and setting up the neural net also all both judgment and development heavy tasks. For example, here are all the things you need to know to implement a simple neural network, if you're implementing it in Tensorflow: 

+ How to [install Python libraries](http://veekaybee.github.io/2017/09/26/python-packaging/)
+ How to run Python locally
+ How to install Tensorflow
+ What a Python data structure is - most tensors are just lists or numpy arrays, which are combinations of other Python data structures
+ How to [integrate these models](http://nadbordrozd.github.io/blog/2017/12/05/what-they-dont-tell-you-about-data-science-1/) into production environments

That knowledge doesn't magically appear, and by the time you learn all of it, you'll be a developer, or at least close to it. 

Finally,  the beginning of essay implies we can trust the judgment of black-box systems (aka systems we can't entirely reverse engineer because we are simply passing in inputs and outputs), although part of the essay is devoted to some caveats: 

 > The 2.0 stack can fail in unintuitive and embarrassing ways ,or worse, they can “silently fail”, e.g., by silently adopting biases in their training data, which are very difficult to properly analyze and examine when their sizes are easily in the millions in most cases.

The best way to understand software is to understand how it's written and have access to the source code so that it can be changed. Karpathy himself [makes his students perform gradient descent from scratch](https://medium.com/@karpathy/yes-you-should-understand-backprop-e2f06eab496b), a process that  involves significant Python knowledge. 

 > In other words, it is easy to fall into the trap of abstracting away the learning process — believing that you can simply stack arbitrary layers together and backprop will “magically make them work” on your data.

This seems to directly contradict his later statement that people will ultimately be able to manipulate data rather than just programs. What happens when we don't understand why a [neural network is not recognizing](https://twitter.com/lewisshepherd/status/939649491690508288) whether a traffic light is red or green? 

[In an interview](https://www.youtube.com/watch?v=_au3yw46lcg) with Andrew Ng, Karpathy notes that he views neural nets as a "hammer," and that teaching a Stanford class on neural nets, he felt like he was "in a position to hand out a hammer to people," which means that, when all you have a hammer, everything looks like a neural net.  


Modeling is important. But it needs to be coupled closely with programming and human judgment when putting neural networks to use.  I'm concerned that now the hype for deep learning is going to overpower all the tools, methodologies, and human intuition we've spent decades developing to reconcile the tug and pull between data and development.

<a name="myfootnote1">1</a>
If you're interested in diving deep into the internals of deep learning, I highly recommend [this talk from a technical perspective](https://www.youtube.com/watch?v=o64FV-ez6Gw) and [this theory video](https://www.youtube.com/watch?v=aircAruvnKk). 
