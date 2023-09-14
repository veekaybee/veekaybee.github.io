+++
card = 'summary'
creator = '@vboykis'
date = '2023-09-13'
site = '@vboykis'
title = "Build and keep your context window"
description = 'My keynote at PyData Amsterdam 2023'
+++

This is the keynote I prepared for PyData Amsterdam 2023. The TL;DR is that we must understand the historical context of our engineering decisions if we are to be successful in this brave new LLM world. 

The text here isn't exactly what I said, it was my notes ahead of time.  My slide template is by [Barbara Asboth](https://barbasboth.com/), who [also did the templates for Normconf.](https://normconf.com/) 

Good morning PyData Amsterdam! An enormous thank you to the organizers, the sponsors, to PyData, and to you, the attendees, for coming!  It’s wonderful to be able to see everyone in person again. This talk is about building and defending our context windows. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/14a24237-acf4-42da-8180-b1016e405ac1">}}

I’m Vicki, and I’m a machine learning platform engineer at Duo Security.  You might know me from previous hits like the [Normcore Tech](https://vicki.substack.com/), the newsletter, [NormConf](https://normconf.com/), the conference about totally boring problems in machine learning, [What are Embeddings](https://vickiboykis.com/what_are_embeddings/), a paper about weird numbers, and [Viberary](https://viberary.pizza/), a semantic search engine that surfaces books to read based on vibe.


I also used to make bad jokes on Twitter and do #devart, and you'll see some of that here. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/e5486a0d-a1b6-4fdb-ae6a-c06c5f874772">}}


Today though, I want to talk to you about something I’ve been worried about over the past year.  As someone who is Eastern European and works on ML infra, there are a number of things I’m worried about at any given time. Data drift,unpinned dependencies, service uptime alerts,  red CI/CD builds, NaNs, and cell execution order in notebooks and whether I left GPUs instances running overnight.

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/697ff2fa-2bf0-4038-b8d0-015d2a987d54">}}


But I have something I’m worried about even more than all these things: I’m worried that, in data land, we have forgotten how to deal with the two fundamental problems of computer engineering.  You might know them already: Cache invalidation and naming things.

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/d0fad0ed-d2c0-4b22-b866-9e1dee8289f6">}}


This joke concept was originally coined by Phil Karlton, a computer scientist at Netscape, in the 1990s. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/8fa63eab-20e8-4038-b384-aa38ac20d54a">}}

A lot of important ideas that came out of Netscape at the time that shape the way we use the Internet today: JavaScript the language (unfortunately), HTTP cookies, and SSL. Karlton was responsible for architecture at Netscape, the browser. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/df8b4c3a-b6e8-4eb4-8416-bbb8c2e9af4f">}}

Why are we taking a side track into early 1990s internet history?  Because history is context and context is power. After all, machine learning, as Russell and Norvig write in [Artificial Intelligence: A Modern Approach](https://aima.cs.berkeley.edu/) is the art of extrapolating patterns based on past data. And are we, humans not the initial thinking and learning machines?

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/6774c820-1eeb-40a5-9d53-2f7ff580f154">}}

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/05ed2021-c00b-42cf-bf42-1945e49e5e8a">}}

Without past context, we cannot make valuable inferences about the future. We cannot train our own mental model. And training our own internal machine is just as, if not even more important than the models and data we work with. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/2269ddb7-3de2-4c66-bac6-82479249dfe7">}}


In computer science, like in all other disciplines,  ideas don’t live in isolation. They are recycled, reused and repurposed constantly, and the more we understand about where they came from, the more we can benefit from the richness of hundreds of years of past human experience to master our current tools and ecosystems. 



## Two Piranesis
To understand what we lose when we lose context, I want to talk about the two Piranesis. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/dc2e89b9-65c5-4546-922b-a2dfbbd55806">}}


The first Piranesi is a character from a fantasy book by Susanna Clarke, about an unnamed man who is lost in a remote, but semi-familiar world.  The unnamed narrator lives in what seems to be an enormous palace. The house he describes is infinite, has hundreds of corridors and levels and halls of statues, and ceaseless rushing waters that flood different corridors and carry away the narrator’s few belongings.

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/567cc24f-a322-4ed2-a12f-31ccb2d4429f">}}


[Image Source](https://www.tumblr.com/sennetrip/725447247668641792/alternative-book-cover-for-piranesi-by-susanna?source=share)

We learn both through his internal monologue and diary entries that he uses to meticulously track and diagram the neverending halls of the house and its movements, that he feels he has always lived alone in the house. There are no other humans, only sea birds and fish, and the ceaseless tides at the lower levels of the house keep him company. 

One of his most notable activities is looking at the statues, of which there are infinitely many in the house, many of which are allusions to classical statues or to books like Narnia, which he doesn’t understand. He lives unmoored, at the mercy of the house. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/0b4d43df-588e-4870-b1ce-e2e81e862dc8">}}


[Image Source](https://www.tumblr.com/werian-wintertid/698758728538849280/its-beauty-immeasurable-its-kindness?source=share)


There is one other person, a well-dressed man who comes every so often to give the unnamed narrator clues about the house. We gradually learn that the narrator, in reality, has suffered from enormous memory loss, which is why he has no idea of where he is in the house or how he can get out, and also why he doesn’t recognize anything from the world we know. The man who observes the unnamed narrator, The Other calls him, jokingly, Piranesi.

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/d5f0789a-70af-4fd8-ac20-27d93946d851">}}


[Image Source](https://www.tumblr.com/thinkanamelater/714277015713366016/i-am-sorry-that-i-was-angry-with-you-forgive-me?source=share)

This is not explained in the book, but the allusion of “Piranesi” is to the second Piranesi, the Venetian artist,sculptor, and archaeologist, Giovanni Battista Piranesi, who drew imagined landscapes of grandiose prisons in the style of Greco-Roman civilization. 


{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/364cab33-d066-4709-8398-4eaec6a5764f">}}



Piranesi was born in The republic of Venice in 1720 and introduced to Latin literature by his brother Andrea. He worked with his uncle in the *Magistrato delle Acque*, the organization responsible for restoring historic buildings. He went on to Rome to study under classical Italian engravers.

While he was studying and sketching views of the city of Rome, he was very precisely measuring all of the antique buildings in the city, which led to the publication of his book, "Roman Antiquities of the Time of the First Republic and the First Emperors.”


{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/698644c4-df41-4c78-ad88-0f755dd4c61a">}}


He fell in love with classical structures and styles and always longed for a return to Roman engineering principles. Some of his most lasting contributions are his sketches of imaginary prisons and monuments of the greatness of the Roman empire, things that could never be built in reality, but look like they could have been. He spent a great deal of time studying classical Rome to recreate these architectural elements as they should have been.

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/8377cd4a-b223-4f4a-b872-5140e14d74a1">}}



In completing these drawings, he preserved a sense of respect and love for the classical style of architecture. In addition to these, he also meticulously documented past Roman monuments, which were, at the time, mostly abandoned. 

Because he had a strong foundation in the past, he was able to stand on that past and build beautiful new things, and, additionally, preserve the past for even us today to study.  He wrote, 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/a86f1fc1-1abc-4c72-bd05-1f75e78bac47">}}


These skills in perserving real buildings then led him to be able to riff on the classics and create his famous series of Carceri, imaginary prisons, which is what The Other alluded to. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/83854436-432d-4759-a582-d17ba014664f">}}


{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/937468c7-05a3-4e84-9247-35ee181f539f">}}


What do Piranesi the book, and Piranesi the archaeologist and artist have to do with the way we do engineering in the data space today? They both revolve around the concept of the importance of keeping historical memory of context. 


{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/ae2e0374-08ef-4393-b11c-08344d6a7ae8">}}

[Image Source](https://www.tumblr.com/sairobee/700841194633265152/lilies-for-the-dead-piranesi?source=share)

 In Piranesi the book, the narrator was initially helpless without his memory, swayed by the tides in the house and at its whims and mercies. The only thing that saved him was his diary that he kept and found old pages which allowed him to understand what actually happened. The diary rooted him to the past and allowed him to move both freely around the house, and to, eventually, leave it. 

Our second Piranesi would have not been able to create beautiful, haunting new works, without carefully studying the past and bringing that context forward both for himself and, later, to us to examine and remix on. 

What do either of these Piranesis have to do with the two fundamental problems of computer science?  Industrial software engineering is still a brand new field compared to hard sciences like math and physics - thousands of years old, and electrical engineering is 250 years old at most . Depending on how we define it, software engineering as a discipline has only been around since Ada Lovelace built the first computer. Alan Turing, the father of computer logic and Vannevar Bush, one of the fathers of information retrieval,  only worked in the 1940s.  The first internet connection only happened in the 1970s. 


{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/56a0e6aa-5790-402e-a0dc-c4191d2fc0ac">}}


[Hadoop Image Source](http://www.notecolon.info/2015/07/hadoop-is-new-black.html)

Modern machine learning is, at most 50 years old, which means the churn for ideas is unbelievably high and results in needless noise in our engineering processes. Hadoop, which we consider ancient in machine learning conferences (and which is still running in production in many places!), was released in 2006.  

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/7ebb7d3a-b558-463a-8b0a-99d6fc3d53c1">}}



The data space particularly is vulnerable to this: data science is only 10 years old and already losing mindshare to areas like AI research and machine learning engineering. AI research changes every week. Everyone is talking about RAG like it’s a household term that we didn’t learn about last week and read up on just so we could make joke tweets about it. 


{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/4f2a5e4e-7d07-4f7e-b45f-8980935df3f8">}}

We, like the book Piranesi, are being flooded with new terminology, new concepts, and a new landscape being created around us without any of our own input. We are at the mercy of the House of Data. Look at the latest AI landscape chart - can you even see it? (Surprisingly, this is less services than Amazon currently has in Sagemaker.) 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/4e7daa60-5fb8-42ea-b317-10483648813c">}}
[Image Source](https://mattturck.com/mad2023/)


Thank you to Chris Albon, Luca Belli, Dan Boykis, Roy Keyes, James Kirk, and Ravi Mody for reviewing versions of this talk.

## Taking Control of the Flood

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/8359677d-4aff-4690-9362-b028670ad9e1">}}


Unless, we take control and start adding to our historical knowledge of these systems by dissecting them, we will be swept away in the flood. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/32329e68-25bf-463b-9e38-99293c8cb260">}}


Ellen Ullman,  a software engineer who worked on complex systems starting in the late 1970s including at startups and large companies, does this in an essay called: “The Dumbing Down of Programming: Some thoughts on Programming, Knowing, and the Nature of Easy”, which she wrote in 1998, when she wanted to uninstall Microsoft Windows NT and install Slackware Linux on her desktop. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/6d00aea5-46f3-44d9-a16f-b5b0ce2e7bb6">}}

In order to understand the insanity of what she was doing, you have to remember the situation of computers at the time. Microsoft, like it is today, was riding an all-time high in the market and in public perception.  Windows was extremely popular - people even lined up at midnight to buy Windows 98. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/46f3abcf-4f7b-4b2b-a17d-55d825049328">}}


Microsoft Windows NT Workstation reached an installed base of 15 million units. To understand how significant this was in an era where applications have hundreds of millions of users, you have to understand that only about 43% of households had personal computers at the time.  

Linux, on the other hand, was new and written by a random dude who created a “free” operating system that he meant to be “just a hobby, won’t be big and professional like gnu”, and [only nerds wanted it. ](https://groups.google.com/g/comp.os.minix/c/dlNtH7RRrGA/m/SwRavCzVE7gJ)

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/b9098ec3-5a95-4e25-b7bc-164a413daa08">}}


What was different was that Linux was open, the operating system available to all to introspect. But, there was a price to pay to get to Linux. In order to do this, Ullman first has to purge Windows NT from her machine. Because she was several versions behind the latest, and Microsoft wouldn’t allow her to delete without all of the components being on the same version, Ullman first had to upgrade some software, through bundles of floppy disks, then completely downgrade to a compatible version that could be deleted. Then, she had to actively reformat her hard drive. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/9eed7e41-ff5c-4094-847b-6a9785fda462">}}


She started this process in the morning and, finally, in the middle of the night, she had just a bare machine. But, there was a cryptic error message.  “no ROM basic, system halted.” 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/2c38a66a-f572-47c7-a643-76c28dd5d507">}}


It turns out that the very first IBM-compatible PCs, were written in the BASIC language. The coding facility was located in the ROM. In the 1990s, there was no BASIC ROM in PCs but the message had never been taken out. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/951c3dbb-7cec-4e25-b5ab-eef5b94d941a">}}


“No operating system, look for basic” is what the computer was saying. She said, “I had not seen a PC with built-in BASIC in sixteen years, yet here it still was, a vestigial trace.”

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/4af68dcf-b2d9-43d0-9dbd-e0e72031041a">}}

There are vestigial traces, previous patterns, archaeological remnants and clues, the Roman empire of computing around us everywhere we look. Ullman has written,

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/7898fc85-6bae-445f-a8f6-946b2b3e66df">}}

But it doesn’t have to be this way. 

## The Context Window

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/1a6fe6d5-5df6-4aa0-b3b4-d15a467ee4dc">}}


When humans have no external context as guardrails, we end up recreating what’s already been done or, on the other hand, throwing away things that work and glomming onto hype without substance. This is a real problem in production data systems. In order to do this, we need to understand how to build one. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/a815f8d4-c755-441f-91e2-7847c7d54947">}}


Let’s start by talking about cache invalidation. When we build a data-intensive application,  we have two problems we need to overcome: the cost of moving data and the cost of processing it, also known as understanding whether our process is I/O bound or CPU-bound. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/6cfbedee-0d7c-4ead-b462-3cd9a4cba60c">}}


Usually, in today’s distributed and cloud-based industrial architectures, the data we need is located on one machine and the place we’d like to process it is located on a different machine. In data-intensive workloads, we incur the cost of computation and network calls when we perform expensive queries or machine learning inference on one service that calls out to our model. 

This is a problem at every level of computing, and even at the level of training neural networks. For example, a [recent article from ACM](https://dl.acm.org/doi/pdf/10.1145/3607891) about [model distillation](https://arxiv.org/abs/1503.02531), which is the process of large, teacher models compressed passed down to smaller, more flexible student models for inference.

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/be7254be-8f77-4fd3-bfdd-720d62f234fa">}}

This is part of what Sarah Hooker refers to in the [Hardware Lottery](https://arxiv.org/abs/2009.06489) as a limitation of the deep learning software and hardware architectures when she says that research ideas win because they are compatible with available software and hardware and not because the idea is superior to alternative research directions. This is true in the industrial space as well - we are always constrained by our hardware, our software, (and our budgets).

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/41d965de-05de-4df6-bb4e-8a02efaf794f">}}

However, processing power is not a new problem - it’s been happening since the invention of the Jacquard loom, which essentially solved the problem by hard-coding global variables. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/9eed69e9-a02c-4384-9fa1-de9db48bcd4c">}}

To prevent this limitation, we can pre-compute a set of results and put it into a cache, which is software that typically processes data much more quickly, with the limitation that it can only process data that fits into the memory of our machine.

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/4573a77a-db53-4505-b902-313ff2291d82">}}

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/5e60b540-04ab-4df6-bef8-9f5c20f77a7e">}}


A cache is, essentially, an in-memory lookup table that’s faster than retrieving values from a hard drive.  Or, in other words, it’s a dictionary. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/bbe00503-1be1-49fc-9d1c-097cc63d6871">}}


Instead of constantly hitting the database to get our values, we can save some of the computation and movement overhead by keeping that data in cache and doing a lookup at inference time.   We’ve been using caches forever, in fact since the days of Netscape, and in the browser, which is why Phil Karlton joked about them. Any given time you access any web application, you’re accessing several levels of cache. 

Let’s take an app like ChatGPT as an example. When we ask it a question, the application has to authenticate, reach out to the chat endpoint, and query an instance of the model, perform the lookup, and return results and add them to cache, all within milliseconds based on the user’s expectations of latency for a search/chat app.  This becomes really expensive to engineer well at scale. So expensive in fact, that some estimate that it costs OpenAI about [$700k a day to run ChatGPT](https://futurism.com/the-byte/chatgpt-costs-openai-every-day), or $70 million over the time that it’s been online.

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/ad64cd9f-b93a-458f-bd62-fc87125627d3">}}

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/ae2df0a2-204e-4571-bc54-ebd913cf988b">}}


What we can do is cache some of this. We can’t predict a future user query: who knows what types of stuff we’ve all put into ChatGPT. But, we can cache past queries, which is how ChatGPT keeps track of all of your past conversations and can bring them up quickly. And, we can cache common enough queries for single words, or we can also cache different queries, but ones that happen in the same timeframe. Elasticsearch does this on a per-query basis, as an example. 


This access pattern is really useful because we often need to move elements in and out of cache, particularly if we need lists of items or results from our machine learning models, or other computationally expensive operations. It’s much faster to store results in cache if we need them in a high-scale web environment. 

Now our data is accessible quickly and we have what are known as cache hits: a query hits the cache and returns results immediately in response. However, now we have a problem: we are relying on the cache to consistently be up-to-date when we ask it for data. Otherwise, if there is no data in the cache, the application will try to reach out to the database, which can take a long time. Or, it will simply error out and stop running, depending on how it’s written. This is a cache miss. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/023087a5-2d3a-46a9-b659-62017702743b">}}

Without the cache, which condenses the data from the global state, the system is much slower and is flying blind. The system becomes our first Piranesi. 
In machine learning, and particularly in LLMs, this pattern of building up a cache to populate fast-retrieval for app data  is similar to building a context window. The “context window” is a term that comes to us from the natural language processing community. The context window, very simply, is a range of tokens or words around a given word that allow us to understand the meaning of that word. 

<img width="1007" alt="Screenshot 2023-09-12 at 10 06 58 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/b4181653-c634-4e4e-9b0f-eac443bc73cf">


We keep that context window “in memory” when we train models, and again, in LLMs, when we formulate our queries against them. For example when we say, “Susanna Clarke is an author. She wrote Piranesi”, in our minds, we need the context window of the first sentence, Susanna Clarke,” to understand that, in the second sentence, "She" is referring to Susanna.

<img width="1087" alt="Screenshot 2023-09-12 at 10 06 28 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/ae9a5d51-c39c-4ad2-98c9-55c528777119">

Humans can infer this because our brains are constantly processing things in cache. In [The Programmer’s Brain](https://vickiboykis.com/2021/11/07/the-programmers-brain-in-the-lands-of-exploration-and-production/), Felinne Hermans, a professor of computer science whose research focuses on how people learn to program writes that when we get confused about the code we’re writing, there are actually multiple types of confusion happening at the same time.

As a quick primer, the human brain has several types of memory, short-term, working, and long-term. Short-term memory gathers information temporarily and processes it quickly, like RAM, or much like a cache. Long-term memory are things we’ve learned previously and tucked away, like database storage patterns. Working memory takes the information from short-term memory and long-term memory and combines them to synthesize, or process the information and come up with a solution.

<img width="600" alt="Screenshot 2023-09-12 at 10 08 04 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/1c221672-4d1f-4331-9b7f-93cbd7e19ddc">

When we’re working on building software, (and by working on, we mean most often reading someone else’s code) all of these processes are going on in our brain simultaneously to try to help us make sense of the programming environment.

It turns out that humans can also lose our context windows pretty quickly, because we can really only hold 7 things in working memory. 

It turns out that humans can also lose our context windows pretty quickly, because we can really only hold 7 things in working memory. 

If you were following the news in November of last year, even as ML practitioners, ChatGPT seems like it came out of nowhere. The entire industry turned on its ear. The fact that it could generate limericks in the style of Shakespeare made it an unbelievable party trick that started slowly to turn into potentially useful applications. One of my friends said, “I’ve been working in machine learning for 15 years and I’m not sure I understand how to now interpret what this means for modeling.”  At the time, [I wrote,](https://gist.github.com/veekaybee/6f8885e9906aa9c5408ebe5c7e870698) 


<img width="600" alt="Screenshot 2023-09-12 at 10 09 18 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/a9e78cdb-9a34-4e71-a032-1aeb64a84b7e">

I, myself, like a lot of the industry overnight, became Clarke’s Piranesi. My context window for reacting to ChatGPT was just this: 

<img width="600" alt="Screenshot 2023-09-12 at 10 09 42 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/72092d32-cd37-405c-af5e-cd6959337aed">


However, once we got over the initial shock and started to look into it, it became clear that what LLMs are doing with context windows is nothing new. If we start building our information cache with the very beginning of understanding documents in context, if we dig deep into the collective memory bank of engineering and science culture, we will find Markov chains. 

<img width="600" alt="Screenshot 2023-09-12 at 10 10 19 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/237c2e13-d330-407e-ad42-6a443a6f7be3">

Markov chains are a classical method in statistics that  predict the next action given the previous action. First-order Markov chains don’t carry any more state than the previous action. So if we know there is a .7 chance to move from E to A, and a .3 chance that we’ll experience E again, we can predict what the next action is. 

<img width="600" alt="Screenshot 2023-09-12 at 10 10 33 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/080c60f7-96d6-4c45-934b-5a048fabd29a">

LLMs are not Markov chains. But they are similar enough that it matters, and moreover,  Markov chains have been favorite toy problems for programming. In [The Practice of Programming](), a classic book by Kerninghan and Pike released in 1999, they go over Markov chains as a way to statistically generate words. 
LLMs are not Markov chains. But they are similar enough that it matters, and moreover,  Markov chains have been favorite toy problems for programming. In [The Practice of Programming](https://www.amazon.com/Practice-Programming-Addison-Wesley-Professional-Computing/dp/020161586X), a classic book by Kerninghan and Pike released in 1999, they go over Markov chains as a way to statistically generate words. 

<img width="600" alt="Screenshot 2023-09-12 at 10 19 54 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/42c1fd70-60bb-4cea-a6c8-e50da2de00f6">

In 2015, I created a Markov chain generating probable Hacker News headlines based on a training data set with an insanely simple and embarrassing script that I’m only going to show for a second and then never again to the internet at large. 

<img width="600" alt="Screenshot 2023-09-12 at 10 20 23 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/8c12934f-f172-4f17-947c-56264e0fa79e">

Using this script, I was able to generate probable sounding Hacker news headlines: You can see the results are not very good, and Markov chains were a baby GPT, but I was also only a baby machine learning engineer, so we were perfect for each other. 

<img width="600" alt="Screenshot 2023-09-12 at 10 21 05 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/f007df55-538d-407e-a771-71561555a6bf">

Markov chains are nowhere near LLMs  if you know that we’ve been trying to do context prediction for a long time, and probabilistic models of text sequences (and not only, Markov chains have been used extensively in physics, economics, and math), large language models slowly start to lose some of their mystique. 

<img width="600" alt="Screenshot 2023-09-12 at 10 21 32 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/7c7e37f6-8430-4339-96f2-5be61aae7764">

So, ok we’ve built the first stage of our context window.

<img width="897" alt="Screenshot 2023-09-12 at 10 21 47 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/35cf5824-cf1b-4489-b164-e436ce72ff69">

From Markov chain generation, we moved on to ngrams, which, instead of looking only at the previous state, look at the state of n-1 words, building the context around the word we care about. [Google started to use these](https://blog.research.google/2006/08/all-our-n-gram-are-belong-to-you.html) for statistical machine translation, speech recognition, and spelling correction.  

<img width="600" alt="Screenshot 2023-09-12 at 10 22 38 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/7cbdc5e2-6271-427a-b99b-d1aaa4fddfb3">


<img width="600" alt="Screenshot 2023-09-12 at 10 22 53 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/8473526c-10c6-4506-b180-a56f0ece6549">

<img width="600" alt="Screenshot 2023-09-12 at 10 23 17 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/3d98f969-eb4e-414d-a5ad-c11fa0ea2a39">

However, as our vocabularies and context windows became larger, so did our computation sizes. To get around the limitations of earlier textual approaches and keep up with growing size of text corpuses, in 2013, researchers at Google came up with an elegant solution to this problem using neural networks, Word2Vec.  

Word2Vec is a family of models that has several implementations, each of which focus on transforming the input dataset into vector representations and, more importantly, focusing not only on the inherent labels of individual words, but on the relationship between those representations - building context between various parts of the text. 

There are two modeling approaches to Word2Vec - cbow and skipgrams, both of which generate dense vectors of embeddings but model the problem slightly differently. The end-goal of the Word2Vec model in either case is to learn the parameters that maximize that probability of a given word or group of words being an accurate prediction in the text. 

<img width="600" alt="Screenshot 2023-09-12 at 10 27 13 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/a4f8f8c9-feaf-4d92-a0b5-abc2c4e4abf6">

Let’s look at the case of CBOW: In training CBOW, we take a phrase, let’s say for example, "Hold fast to dreams for if dreams die, life is a broken-winged bird that cannot fly" and we we remove a word from the middle of a phrase known as the context window, for example, fast. When we train our model, we train it to predict the probability that a given word fills in the blank. 


<img width="600" alt="Screenshot 2023-09-12 at 10 27 34 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/c780abc2-cb03-46f0-b947-117cdcee8a68">

<img width="600" alt="Screenshot 2023-09-12 at 10 27 58 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/9995cf8f-3f84-45c9-a0ba-6cca3564b391">

Word2Vec became one of the first neural network architectures to use the concept of embedding to create a fixed feature vocabulary. 

<img width="600" alt="Screenshot 2023-09-12 at 10 28 25 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/15f03164-e2df-45bb-9584-808487d077e6">


However, in Word2Vec, information flows from encoding to embedding and output layers without feedback between the latter two. This limitation prevents the model from understanding long-range context and handling out-of-vocabulary words, or text that wasn’t in the original input.  Word2Vec also struggles with polysemy, where words have multiple meanings in different contexts. 

<img width="600" alt="Screenshot 2023-09-12 at 10 29 04 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/bbfbe194-9ef9-4a2c-92fc-566d1a4b3941">

An important concept allowed researchers to overcome computationally expensive
issues with remembering long vectors for a larger context window than what was available in RNNs and Word2Vec before it: the attention mechanism, combined with previous ideas like the encoder/decoder architecture. The encoder/decoder architecture is a neural network architecture comprised of two neural networks, an encoder that takes the input vectors from our data and creates an embedding of a fixed length, and a decoder, also a neural network, which takes the embeddings encoded as input and generates a static set of outputs such as translated text or a text summary. In between the two types of layers is the attention mechanism, a way to hold the state of the entire input by continuously performing weighted matrix multiplications that highlight the relevance of specific terms in relation to each other in the vocabulary. 

<img width="600" alt="Screenshot 2023-09-12 at 10 29 37 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/bc964950-1d56-4988-abee-3a5981619c4b">

<img width="600" alt="Screenshot 2023-09-12 at 10 32 23 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/646b9360-8211-4e03-9f5b-437c8bd167ed">


We can think of attention as a very large, complex hash table that keeps track of the words in the text and how they map to different representations both in the input and the output.

<img width="1117" alt="Screenshot 2023-09-12 at 10 32 44 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/0d2c3758-3054-4a7e-ba67-8326ead156cd">

So what attention is really just a hashmap or since this is PyData, a dictionary. Which is what a cache also is at its very heart. 

<img width="600" alt="Screenshot 2023-09-12 at 10 32 58 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/3e023b52-8399-4fd4-8854-294f73a607cc">

In fact, there is a reason that Redis, one of the most-often used pieces of software in caching, expands to Remote Dictionary Server.  

<img width="600" alt="Screenshot 2023-09-12 at 10 33 51 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/9e1080aa-aecb-4a37-8b25-50aa5d8798f1">

In ChatGPT4, Bard, and other Large Language Models what we are doing when we train them is increasing the scope or richness of the context window so that they know about the text, and also the input vocabulary we have.  And in, retrieval, we’re also trying to increase the context windows we pass in. 

And now, to bring this all back to LLMs and ChatGPT it turns out that, just like some of the hardest problems in human memory are how to keep context windows and in Transformer models how to keep attention,  some of the hardest problems in LLMs are actually how to operate caches. 

In March, [ChatGPT experienced a data leak](https://openai.com/blog/march-20-chatgpt-outage) precisely because of cache invalidation. 

<img width="600" alt="Screenshot 2023-09-12 at 10 34 51 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/fbe92a20-9873-4b75-833f-d061b7c21d38">

ChatGPT uses Redis  to keep track of your chat history state and user information so that it doesn’t have to perform expensive queries to retrieve your chat history every time. The chat history, on the sidebar, is hot-loaded from Redis using the py-redis library.  Py-Redis uses asyncio, a Python async management framework, to maintain connections between the Python server and the Redis cluster, which recycles stale connections. Those connections behave as two queues, where the caller pushes a request onto the queue and pops a response from the outgoing queue, which returns a connection to the pool. 

If the request is canceled after it’s pushed onto the queue but before the response is popped, the connection is corrupted and the next, unrelated response that’s dequeued can receive stale data. If that data happens to match, it will return bad data to unrelated users. So it’s a cache miss, but where the cache miss propagates other user’s chat titles into your chat history: a context window of the very, very wrong kind. 

<img width="600" alt="Screenshot 2023-09-12 at 10 35 33 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/fc9ef757-7b7c-4445-b653-5d34af10c484">

<img width="600" alt="Screenshot 2023-09-12 at 10 35 53 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/c7861fd7-c631-4589-ae2d-a8ab714d10e8">

Now we’ve made a full circle, cache, context window, and how our minds work are interconnected. 

<img width="600" alt="Screenshot 2023-09-12 at 10 37 01 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/6ba263de-4928-406e-bdfd-a240b8f935dc">

<img width="600" alt="Screenshot 2023-09-12 at 10 37 18 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/2384ba91-ff1f-4358-a230-6783663b2be6">


<img width="600" alt="Screenshot 2023-09-12 at 10 37 59 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/389ee345-b3c3-4d1c-992b-6dc78ee407bc">

## Naming Things

<img width="600" alt="Screenshot 2023-09-12 at 10 38 24 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/98960757-5351-43d6-bf2e-999a51afa674">


So much depends on the context window: how we understand LLMs, how we process them, and even how we deploy them.  In order to work through the hype of LLMs and do good data work today, we need a good context window.  And, if we don’t have a good context window, we also can’t do the second part, which is naming things.  

<img width="600" alt="Screenshot 2023-09-12 at 10 38 47 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/0b56e67d-7d8c-4018-b8aa-847e547aed84">

Let’s start by talking about just why it is that [naming things is really important](https://vickiboykis.com/2023/06/29/naming-things/), and equally hard Think about how many times you’ve sat over a variable name. Should it be Cache or DataStore? Vector or List? GetActions or PullActions? Why is this process so challenging? 

<img width="600" alt="Screenshot 2023-09-12 at 10 39 30 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/a9a9c64e-d25d-4d1c-ae28-32177b21ae72">

There’s a neat little book that came out recently  called, appropriately, “Naming Things” by Tom Benner that talks about this process. Benner writes that naming things is hard because there are a lot of expectations on us, the namer.  We, the namer, need to know 
1. exactly what the thing does
2. The term for the thing both in a business and technical context and 
3. The name will be reused throughout the codebase

When we name things, we need to think about how easy the name is to understand - aka the name should not have multiple meanings and can’t be redundant throughout the codebase, the name needs to be consistent with other parts of our codebase, and yet it also needs to be distinct enough to name the context of that thing. 

<img width="600" alt="Screenshot 2023-09-12 at 10 40 14 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/6e7bde5e-9d1b-4c2d-bdad-f179540e9f84">


<img width="979" alt="Screenshot 2023-09-12 at 10 40 50 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/1c023642-a0ed-40cf-9798-6abb9f2fc7b3">

Then, we also have the external industry context of the name, particularly if we’re working with code that’s open-source, or will be read by practitioners like us across the organization. And finally, when we look at names, it should make sense

What we’re doing when we pick a good name is reconciling context windows on more than two dimensions, and in fact, on likely four dimensions, with the fifth, unspecified dimension being our own personal preferences.  When we perform these levels of thought, we are really putting our Programmer’s Brain to work. 

It turns out that, when we name things, we really are just also building up a context window! And the more historical context we have from reading different codebases across different industries, and from keeping the historical context window from our own industry, the more successful we’ll be in our own codebases. 

Of course, we could just do this with ChatGPT.  But we won’t be as successful!

For example, let’s hypothetically say we’re working on, a semantic search library that takes a vibe query, vectorizes the query and performs lookup, in, say a cache, that also has a search module which stores all your learned embedding vectors and then returns the results via a Flask API.  

<img width="600" alt="Screenshot 2023-09-12 at 10 44 49 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/ebd740ba-d414-4931-9c52-4fb0750bfaad">

Something you might want to write is a module or class that [repopulates the cache with embeddings.](https://vickiboykis.com/what_are_embeddings/) What would you call such a class, one that takes a static file of embeddings generated via sentence-transformers and writes them to redis, where an API call will perform a nearest-neighbors search?


<img width="1066" alt="Screenshot 2023-09-12 at 10 45 13 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/21f363be-fc7e-463c-9c70-044b6bd899b1">

If we look about rules for how we should name classes, we ideally want classes to be “nouns”, consistent with the rest of our codebase. 

We could call it something like EmbeddingsWriter. Seems ok, but what if we want to write something other than embeddings later on? How about DataWriter? Too Generic because we could be writing different kinds of data, and it’s not clear where. How about VectorPutter. Next time, absolutely. If we were working in Java, instead of Python, I’d be giving this presentation at JavaData Amsterdam, and likely we could use some term like VectorSetterFactory, which makes complete sense in the context of that language. 

<img width="600" alt="Screenshot 2023-09-12 at 10 46 20 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/f439f55c-6ea0-4af6-9121-773dbee6dce7">

<img width="600" alt="Screenshot 2023-09-12 at 10 46 37 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/90c75d68-edf9-4e27-aaf6-9e8d3d277d02">

What helps us here is building our context window. For decades in information retrieval, both within search and recommendations a common pattern has been to write a large amount of data to a data structure called an index. For example, in one of the first papers on industrial recommendation systems, [Tapestry paper architecture](https://dl.acm.org/doi/pdf/10.1145/138859.138867), one of the first recommender systems, written in 1992, which was used to recommend email messages from newsgroups to read (it turns out we were overwhelmed with data even at the very beginning of the internet), has a structure it calls an Indexer, which the paper describes as, 



<img width="600" alt="Screenshot 2023-09-12 at 10 47 18 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/958836cf-e26b-4ab2-bc79-964f28e2be3d">

“Reads documents from external sources such as electronic mail, NetNews, or newswires and
adds them to the document store. The indexer is responsible for parsing documents into a set of indexed fields that can be referenced in queries.” 

<img width="600" alt="Screenshot 2023-09-12 at 10 47 38 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/8a895570-5bfa-4576-ae1b-cbcbf6b37c0c">

 In search, particularly, this is known as an inverted index. And there we have it. We now have an indexer class. 

One of the ways we are good (or bad) at naming things is all the different naming patterns we’ve come up with for our data practices.  We often talk about phenomena in the data sphere as happening in the watery realms. 

In a post called the Union of Data and Water, [Pardis Noorzad writes](https://djpardis.medium.com/data-water-terms-6bf9e9c7aad6), “Data toolmakers and the larger data community have long been reclaiming water terms to refer to products, services, actions, and concepts.”  She has a whole list of ways we’ve been talking about data tools and products as water: data lakes, buckets of data within the data lakes, cascading (and scalding on top of that), two of the first map-reduce paradigms used to get data out of the data lake, and data in the cloud. 

But, underneath the waters, if we decide to dive in, under the data lakehouse, the data lake, the LLMs, and is actually a very solid, single foundation that people have spent hundreds of people years thinking about, exactly so we don't have to reinvent the wheel, but instead build on what’s come before. 

## How to build and keep your context window

<img width="600" alt="Screenshot 2023-09-12 at 10 48 48 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/6ce8f4cf-f5c2-49f8-bab0-bd64f44cf307">

Now that we have named our problems, what can we do about this so that we, as humans who are swimming in the deluge of new data, new concepts, and new terminology, are not cast adrift? 

First, become a binary tree. What this means is reading broadly, then reading deeply. Be like Piranesi 2 and read the classics. We train better large language models by either adding more data or changing the architecture so that the attention mechanism captures more of the relationships in the data. We can do this for ourselves by reading broadly and deeply in our field. It’s hard to overestimate how much reading will do in helping anchor our contexts. 
This can be very hard to do and disheartening in the face of the enormous amount of literature, blog posts, and now social networks that propagate content about machine learning and its operations. When I first started reading, I stress-bought this enormous pile of books and [hope I learn through osmosis.](https://vickiboykis.com/2022/11/10/how-i-learn-machine-learning/)

But as soon as I finished one book, a new one came out. As soon as I read one paper, there was another one that beat SOTA. New frameworks rise and fall every day. Pip updates happen every day. It seemed like a never-ending uphill slog.
One day, I stood looking at the pile of books and papers in dismay, and my husband, Dan, walked by, sipping his coffee.
“I have to learn ALL of this,” I wailed.
“It’s not so bad,” he said. “You know why? Because all of these books are not distinctly unique in their contents. For example, once you learn OOP in Python, you’re going to have a much easier time learning it in Java and now you can throw away half the book. And once you know Java, you’re already 60% of the way to Scala (except the weird monad-y parts, but don’t touch those). And so on.”

<img width="600" alt="Screenshot 2023-09-12 at 10 50 51 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/12626f82-a5cd-4df5-a270-ba59e416baed">

When I started thinking of learning as a process that involves going extremely wide on core fundamentals, then going deep and building links across fundamentals, it made the process a LOT more manageable.

What also helps here is not reading blog posts, but reading books, particularly books and papers, such as the Tapestry paper, now foundational, that have stood the test of time. Read papers that are ten years old with lots of citations, read blog posts that have been highly upvoted and shared over and over again. 

Within those blog posts and papers, look for links to previous works. For example, just the other day I saw a paper that talked about transformers really being SVMs - support vector machines - which were models that were used heavily just before Word2Vec came into the picture. Learn these fundamentals and see how they link together. 
Learn foundational things, fill your context window with good breadcrumbs that will allow you to see the larger picture. 

<img width="600" alt="Screenshot 2023-09-12 at 10 51 10 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/4610d078-49cf-4d86-8814-796c7d9aef62">

And, just as we should be reading books, we should be reading code. As engineers we spend most of our days reading rather than writing code and it’s a skill we can improve on.   In any given library, click through the methods. Look at them. Write down the code. Go down the rabbit hole. 


<img width="600" alt="Screenshot 2023-09-12 at 10 51 26 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/9748eb68-fccd-49e1-b850-f4c32a5c9d64">

That’s what we’re here for at this conference! It’s so rare to get the ability to openly talk to other people about what they do at work. Do it here! Piranesi wandered through the hallways, unmoored, until he found other people to anchor his point of reference to.  As he writes in his diary, “This experience led me to form a hypothesis: perhaps the wisdom of birds resides, not in the individual, but in the flock, the congregation.”

<img width="600" alt="Screenshot 2023-09-12 at 10 51 59 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/5b179dc4-33a9-40bd-b7d1-1dfa594a8fd5">

If you go to an interesting talk, and you see people gathering after the talk, discuss the talk. Ask questions, come up to people after the talk. Ask what other people are working on, what problems they’re having, what technologies they’re using. Presumably you’re here because you were looking to seek out the power of connection - do it! 

Particularly in an era of social media fracture, in-person events are so important for doing this and for bringing knowledge back to our organizations.

Finally, build from fundamentals. Become close to the machine. Ellen Ullman writes, low is good. Understanding is good. The further away we get from abstractions and understand the code we're writing, the better developers we become. 

<img width="1129" alt="Screenshot 2023-09-12 at 10 54 51 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/087e4e91-ba3e-476a-bedb-85b1808994bb">

Whether that's building a vector database out of np.vector or 
<img width="1027" alt="Screenshot 2023-09-12 at 10 55 28 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/88956223-a871-4767-b662-34454b83012d">

[coding your own algorithms from scratch](https://sebastianraschka.com/faq/docs/implementing-from-scratch.html) like Sebastian does,
<img width="1158" alt="Screenshot 2023-09-12 at 10 55 54 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/a323ad39-b1d8-43f3-92ec-d320abb75632">

each of these moves concepts from your short-term to long-term memory and allows you to master your craft.  [The best engineers I know that do this well](https://vickiboykis.com/2021/08/05/the-local-minima-of-suckiness/) are constantly asking questions and learning, and, most importantly, taking things apart and digging deep into them to see how they work. 

Finally, build with joy. Ship things!

<img width="600" alt="Screenshot 2023-09-12 at 10 57 58 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/e3e1a8f4-5389-40de-af62-c23cea86d364">

<img width="600" alt="Screenshot 2023-09-12 at 10 58 12 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/1ec85315-0154-4300-8946-6aea72ab158e">


## Conclusion
When we look at any engineering system, we need to look hard, but find that it has still been created by people.  We need to aggressively fight to build and keep our context windows.  So, be Piranesi. Be Ellen Ullman. Be Phil Karlton. Be yourself and build out and hold on to your context windows. We need them.

Thank you and have an amazing conference.  Enjoy Pydata Amsterdam!




<img width="1110" alt="Screenshot 2023-09-12 at 10 53 35 AM" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/094b0bbe-56d7-4109-a0ab-381ddd0ed20d">






Thank you to Chris Albon, Luca Belli, Dan Boykis, Roy Keyes, James Kirk, and Ravi Mody for reviewing drafts of this talk. 











