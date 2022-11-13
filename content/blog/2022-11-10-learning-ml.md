+++
card = 'summary'
creator = '@vboykis'
date = '2022-11-10'
site = '@vboykis'
title = "How I learn machine learning"
description = '1. Learn broadly. 2. Learn deeply. 3. Don't be afraid to re-learn'
+++


"Almost all advice is contextual, yet it is rarely delivered with any context", [writes Justin in this post](https://www.simplethread.com/20-things-ive-learned-in-my-20-years-as-a-software-engineer/) of things he's learned in 20 years as a developer. 

The context for the advice I'm about to share is: I started without an engineering background and [through hard work and a lot of luck](https://increment.com/planning/the-best-laid-plans-tech-careers/) ended up as a machine learning engineer. 

My overarching as an MLE is to continuously work towards designing and deploying well-designed, and transparent machine learning systems. 

[The best engineers I know that do this well ](https://vickiboykis.com/2021/08/05/the-local-minima-of-suckiness/) are constantly asking questions and learning, and this is a goal I have for myself in my career, as well.

It's important for me to understand the machine learning stack end to end, and I've felt [most valuable and fulfilled](https://vickiboykis.com/2022/01/02/2021-work-recap-or-the-conjoined-triangles-of-success/) in roles where I can contribute to both modeling and infrastructure. The way I do this best is [by thinking in patterns.](https://vickiboykis.com/2021/06/06/the-humble-hash-aggregate/) 

I am also (painfully) realizing that most machine learning work, at its core, is [software engineering fundamentals and gruntwork](https://vickiboykis.com/2021/09/23/reaching-mle-machine-learning-enlightenment/). 

So, as always, take this blog post mostly as advice to my past self that may or may not work for you based on your goals.

---
As a machine learning engineer, staying up-to-date with my field is an absolute nightmare. 

On the one hand, there is the explosive rise of [deep learning](https://vickiboykis.com/2017/12/11/data-ex-machina/), [transformers](https://e2eml.school/transformers.html), and t[text-to-image models](https://jalammar.github.io/illustrated-stable-diffusion/) that are pushing the rest of the field forward.  As a result of these new ways of working with data for machine learning, new ways of thinking about and storing data for these models, such as [vector databases](https://dmitry-kan.medium.com/landscape-of-vector-databases-d241b279f486), have also grown over the past five yeas. 

On the infrastructure side, the proliferation of machine learning across and within companies has led to [the rise of MLOps,](https://www.mihaileric.com/posts/mlops-is-a-mess/) an ML-adjacent discipline that tries to wrangle data-centric workflows into streamlined production scopes of work. Then, there is [modern data stack](https://www.moderndatastack.xyz/), [data mesh](https://aws.amazon.com/blogs/architecture/lets-architect-modern-data-architectures/), and now, [data contracts.](https://mlops.community/an-engineers-guide-to-data-contracts-pt-1/) 

Underneath all of this is a [foundational bouquet of cloud-based services](https://vickiboykis.com/2018/01/28/working-with-aws/) catering to all kinds of machine learning workflows across multiple cloud providers. 

So, just to summarize, all you have to know as an MLE is: 

+ computer science fundamentals: data structures, algorithms, object-oriented programming
+ an in-depth understanding of production engineering best practices: monitoring, alerting, unit testing, version control, latency, throughput, scale
+ fundamentals of machine learning and statistics: traditional models, sampling, A/B testing
+ an informed opinion about all the currently popular trends in a machine learning stack so you can select from them (RIP [Lambda architecture](https://en.wikipedia.org/wiki/Lambda_architecture))
+ an understanding of the developments in the field you specialize in (medical AI, recommendations, search, ML for security, each of which are formulated as different problems and have their own context and vocabulary)
+ YAML


# Learn the basics

When I first started making sense of this landscape, this learning task felt insurmountable. So, I did what I always do, which was to [stress-buy books and hope I learn through osmosis](https://vickiboykis.com/2022/01/02/2021-work-recap-or-the-conjoined-triangles-of-success/). 

I bought a lot (a LOT) of different books across a lot of different disciplines and also started very widely reading academic literature.

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/bookstack.jpeg" width="600px">}}

But as soon as I finished one book, a new one came out. As soon as I read one paper, there was another one that beat SOTA. New frameworks rise and fall every day. Pip updates happen every day. It seemed like a never-ending uphill slog. 

One day, I stood looking at the pile of books and papers in dismay, and my husband, [Dan](http://danboykis.com/), walked by, sipping his coffee. 

"I have to learn ALL of this," I wailed.

"It's not so bad," he said. "You know why? Because all of these books are not distinctly unique in their contents. For example, once you learn OOP in Python, you're going to have a much easier time learning it in Java and now you can throw away half the book. And once you know Java, you're already 70% of the way to Scala (except the weird monad-y parts, but don't touch those). And so on." 

When I started thinking of learning as a process that involves going extremely wide on core fundamentals, then going deep and building links across fundamentals, it made it the process a LOT more manageable. 

Here's a concrete example: Let's take the idea of the graph data structure. Here's a small, friendly graph. (This is actually a binary search tree, but let's roll with it for now.)

```
        5         
       / \        
      4     8     
     /     / \    
  11     13    4  
 /  \         / \ 
7    2       5   1
```

A graph, like all data structures, has special properties: it's not linear, it has nodes, the numbers, and edges, which connect the numbers. 

First we go broad: In order to understand a graph, we have to understand what the alternatives to it could be: It's different from other data structure, like arrays, lists, and hashmaps. So now we're covering algorithms. 

Why am I bringing up graphs? Because once we  understand graphs as a data structure, we understand that [trees are a form of graph.](https://stackoverflow.com/questions/7423401/whats-the-difference-between-the-data-structure-tree-and-graph) 

And once we understand trees, we understand that DAGs, one of the canonical data structures in data data work, are a type of tree, as well, and can also be traversed. 

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/dagsdfs.png" width="600px">}}

This gets us now from abstract data structures to things that we work with every single day:  [Airflow DAGs](https://airflow.apache.org/docs/apache-airflow/1.10.12/concepts.html), [execution plans in Spark](https://stackoverflow.com/questions/7423401/whats-the-difference-between-the-data-structure-tree-and-graph), and Tensorflow/PyTorch [computational graphs.](https://pytorch.org/tutorials/beginner/blitz/autograd_tutorial.html). 

We can also work with graphs themselves, i.e. [GraphX and Pregel.](https://spark.apache.org/docs/3.2.1/api/java/org/apache/spark/graphx/Pregel.html), if we're dealing with data that examines social networks or connections. 

So just by learning one foundational concept, we unlock being able to work with it across various domains within machine learning and relating concepts to each other. We can also now reason about what data structure might work best where and what kinds of engineering tradeoffs we make when implementing X or Y.  

While we're here on graphs, it would be a good idea to go a little deep on arrays, because arrays are the underpinnings of most of our linear algebra work. [NumPy Nd Arrays](https://numpy.org/doc/stable/reference/generated/numpy.ndarray.html), for example, form the basis of much of scientific computing, including implementations and expansion to [Pandas and PyTorch](https://pytorch.org/tutorials/beginner/blitz/tensor_tutorial.html). 

So, once you understand arrays, you understand how they build to Pandas DataFrames and PyTorch tensors.  On and on it goes, until the space of things you don't know is less than what you do. 
 
The more foundational concepts you learn, the more you put into your working memory to synthesize with what you already have available and the easier it becomes to create connections, as Felienne Hermans wrote in [The Programmer's Brain](https://vickiboykis.com/2021/11/07/the-programmers-brain-in-the-lands-of-exploration-and-production/): 

> As a quick primer, the human brain has several types of memory, short-term, working, and long-term. Short-term memory gathers information temporarily and processes it quickly, like RAM. Long-term memory are things you’ve learned previously and tucked away, like database storage. Working memory takes the information from short-term memory and long-term memory and combines them to synthesize, or process the information and come up with a solution.

And, another thing: everything you read in machine learning and engineering repeats. 

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/sovietunion.png" width="600px">}}

So, if you don't understand the first time, don't feel bad. Just pick up a different book (or three). You will already have the foundations of the first book to guide you. 

So, how did I figure out what's foundationally important in the broad sense? 

I wish I had a concrete answer for this. Like for a lot of people, I think it's just a LOT of reading of different things that reinforce concepts from different directions and building things in industry. 

But if I had to relearn some of these concepts, I'd want to use heuristics, the first one of which is how to think about machine learning areas of expertise. 

## The Envelope and the Letter

When you're learning ML is that, generally, as a machine learning engineer, you will tend to focus on one of two areas: building the model or building the infrastructure that runs the model. 

Shreya has [a brilliant piece](https://www.shreyashankar.com/phd-year-one/) about these two responsibilities that has shaped my thinking about this problem over the past couple months.

She writes, 

> There are two types of business-critical MLEs. The first is the Task MLE, who is responsible for sustaining a specific ML pipeline (or small set of ML pipelines) in production. They are concerned with specific models for business-critical tasks. They are the ones paged when top-line metrics are falling, tasked with “fixing” something. They are the ones who can most likely tell you when a model was last retrained, how it was evaluated, etc. 

> second MLE: the Platform MLE, who is responsible for helping Task MLEs automate tedious parts of their jobs. Platform MLEs build pipelines (including models) that support multiple Tasks, while Task MLEs solve specific Tasks. It’s analogous to, in the SWE world, building infrastructure versus building software on top of the infrastructure.

One framework that really guides me is thinking about whether I'm currently trying to learn the envelope or the letter of machine learning, and this is largely depending on what I'm currently working on and what I'd like my next job to look like. 

If you want to do MLOps, you should focus very heavily on data engineering, feature stores, best principles for production, devops, and distributed systems. 

If you want to get good at modeling, you should focus on ML principles, you should read a ton of papers and implement them. 

However, based on my own personal experience, if you don't understand both the model and the ecosystem it's running in, you will have hard time leveling up in your understanding of the machine learning ecosystem as a practitioner.  

As Shreya says,

> But I call them Platform MLEs instead of Platform SWEs because I think it’s impossible to automate ML babysitting without a decent understanding of ML. 

And, as a lifelong generalist, I find it impossible for myself to stick to one or another and always end up getting back to a place where I try to understand my entire workflow end to end.

## Learning the letter

The model itself is a good place to start. 
I wrote that [you won't get very far by not reading the literature](https://vickiboykis.com/2022/07/25/looking-back-at-two-years-at-automattic-and-tumblr/). 
 
This is especially true in my field of interest, recommender systems, and, more broadly, machine learning for text and image processing, a field that's moving incredibly quickly these days. 

But the same applies for all areas of machine learning. AI as a whole is moving very quickly these days, and many ideas that industry takes off and runs with come from papers that have been written at most 5 years ago. 

How do you even get started, and how do you keep up with the field? 

Learning to read and understand academic literature is a distinct skill. Luckily, there's an amazing course from Harvard that covers how to do this. [All of the lecture notes are online.](https://www.cs197.seas.harvard.edu/)

Probably the most important lecture for me has been [the section on learning to read academic papers](https://docs.google.com/document/d/1bPhwNdCCKkm1_adD0rx1YV6r2JG98qYmTxutT5gdAdQ/edit), which teaches you how to take a very broad research area and narrow it down to what you're interested in. 

The intro echoes exactly what I just outlined: 

>I’m going to break down the process of reading AI research papers into two pieces: reading wide, and reading deep. When you start learning about a new topic, you typically get more out of reading wide: this means navigating through literature reading small amounts of individual research papers. Our goal when reading wide is to build and improve our mental model of a research topic. Once you have identified key works that you want to understand well in the first step, you will want to read deep: here, you are trying to read individual papers in depth. Both reading wide and deep are necessary and complimentary, especially when you’re getting started.

So, when I first started learning about recommender systems, I did two things. I asked the experts in recsys that I knew what I should read, and I also googled "recsys foundational papers." 

The first paper I read was very hard to understand. I also wasn't used to reading academic literature, which is [entirely different than reading even non-fiction.](https://web.stanford.edu/class/ee384m/Handouts/HowtoReadPaper.pdf)

So what I did was what the course recommended: keeping a list of papers, outlining what I knew and what I didn't know, what what words didn't make sense. Here are some sample notes I took from recsys this year, for example:

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/recsys2022.png" width="600px">}}

Once you cross-read enough papers, particularly introduction sections, you start seeing the same terms you've looked up over and over again, and things start to click. 

Second, I go to industry conferences, a task made MUCH easier (and cheaper!) by the amount of conferences that stream online these days.  

The big ones for me specifically lately have been NeurIPS, CIKM, and [Recsys.](https://vickiboykis.com/2021/10/28/recsys-2021-recap/) There are others. Usually I'll watch a few sessions live and download the conference proceedings to go through them. The first time I did this, it took forever because I didn't know what I was looking for and felt the need to read every paper. Now, I can get pretty specific about my areas of focus. 

Finally, I look to the experts. For example, [Sebastian is a wonderful source of content](https://magazine.sebastianraschka.com/p/ahead-of-ai-2-transformers-fast-and) on machine learning, and recently he wrote a newsletter on how HE keeps up with the industry. 

> In each list, I collect interesting books, research articles, blog posts, videos, Reddit discussions, and sometimes even Twitter threads. Do I read it all? No, keeping up with that would be a full-time job. And I only capture some of the interesting stuff. Typically, I only focus on resources that can help me learn something new (as opposed to building a broad knowledge base and recreating a personal Wikipedia).

> Then, I pick out a handful of resources I want to read throughout the week (having a weekly review routine is a topic for another time). Doing this, I realize that 95% of the stuff I captured is not that important (versus some higher priority stuff related to your current project). So, ignoring resources doesn't mean you were lazy or missing out, but that we are defining your priorities and doing the most important stuff. 

I also have, somehow miraculously, over the course of about three months, fine-tuned my LinkedIn feed so it's actually valuable to me and surfaces papers, links, and talks that are more signal than noise. 

## Engineering the envelope

At the same time as I am reading theory, I am also constantly looking to improve my production engineering skills, because at its core, machine learning is an engineering discipline - otherwise you're simply doing research. 

[Machine learning has been changing](https://vickiboykis.com/2019/02/13/data-science-is-different-now/) from the exploratory angle the field took at the very beginning. You will have a distinct advantage in the marketplace if you can deploy your ideas to production. 

Learning to build machine learning systems means learning to be a production engineer. This means, first and foremost, becoming a solid backend engineer. 

What does it mean to be a solid engineer? First, learning the classics that translate across most ecosystems: [git, SQL, and command line.](https://vickiboykis.com/2022/01/09/git-sql-cli/)

Then, learn one programming language well - extremely well. All the language features, the idioms, the build tool, the ecosystem. Then, learn two more. Each one will give you a way to compare and contrast the strengths of the system you're working on. 


Specific to machine learning, there are a lot of great resources (thanks to everyone who replied [to this thread](https://twitter.com/vboykis/status/1591600366113918976). I've pulled out the best ones here:

+ Machine Learning [Production Papers.](https://twitter.com/gantry_ml/status/1559555730880942080) I'd recommend starting with [this paper](https://research.google/pubs/pub43146/)
+ [MadeWithML](https://madewithml.com/) 
+ Monitoring: [this doc on monitoring and alerting](https://docs.google.com/document/d/199PqyG3UsyXlwieHaqbGiWVa8eMWi8zzAn0YfcApr8Q/edit) forms the foundation of my philosophy, too
+ [MLOps Overview](https://arxiv.org/ftp/arxiv/papers/2205/2205.02302.pdf) (you'll notice this is an academic paper too, so your paper-reading skills from the last section will not be wasted here)

Separate from this, I'm not sure of good ways to get intuitions for the ways production systems work [except to work on them](https://vickiboykis.com/2021/06/20/the-ritual-of-the-deploy/), which brings me to my last point. 

## Build things

As I'm reading all this, I usually get bored of taking notes and get the itch to build. That's why we ultimately do this thing, right? To build something fun and interesting, and useful for users. 

I cannot emphasize how important it has been for me to build things when I'm learning. And particularly, small, practical projects that I know for sure I'll finish and that have an interactive component (i.e. not just data results or internalcode) have been absolutely key. 

Some good warm-ups are to implement parts of ML algorithms or libraries from scratch: gradient descent,one-hot encoding,  minhash, [Jaccard similarity](https://boringml.com/docs/recsys/jaccard-similarity/), [tf-idf](https://gist.github.com/veekaybee/3f05047a179384888c235b409ea528a5) are just some of the ones I've played around with to get a sense of how they work from the inside out.

Once you've implemented some basic stuff, it's time to go bigger. Want to learn a new language? Write a static site generator in it! 

Some recent ones I've done:

+ Understanding OOP in Java [with a static site generator](https://github.com/veekaybee/caffeine)
+ [BoringML:](https://boringml.com/) a site that catalogs thing that are boring but important in machine learning, a place for me to take notes, as well
+ A small Python [Streamlit app that simulates the survivorship bias plane](https://vickiboykis.com/2021/10/10/doing-small-fun-projects/)
+ [End to End ML with GPT-2](https://vickiboykis.com/2020/06/09/getting-machine-learning-to-production/)
  
  
You'll notice that for me, two of these projects ended up being [the equivalent of keeping research notebooks.](https://jamesmckay.net/2017/02/how-to-keep-lab-notes-as-a-software-developer/). Actively taking notes has been really important in my process of memorization. 

I am now gearing up to do a side project that involves embeddings, streaming, and semantic search that should be a ton of fun. The important thing is that these projects are interesting to you and that you set an end goal in line, and that you learn something from them. 


## wrap-up

Learning machine learning is hard and can be frustrating sometimes. But if I can summarize the post, it would be these this: 

1. Make sure to learn broadly
2. Then narrow your focus and learn deeply
3. Don't be afraid to use multiple resources to learn the same concept from different angles
4. Do projects to reinforce your learning

Good luck out there and see in you the codebase!


## Thanks!

Thanks to Julia for prompting this post with her tweet,

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/handson.png" width="600px">}}

And also a big thank you to [Sebastian](https://twitter.com/rasbt) for his list of resources and generally being an amazing source of machine learning knowledge. 
