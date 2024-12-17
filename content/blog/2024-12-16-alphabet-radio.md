+++
card = 'summary'
creator = '@vboykis'
date = '2024-12-16'
site = '@vboykis'
title = "Write code with your Alphabet Radio on"
description = 'We can only write good code together'
+++

There is a lot of debate in the software community around whether LLMs can replace developers.  Part of the reason is the way we formulate the problem of what it means to write software. In industry, we still give outsize cultural deference to software developers as lone wizards who come into the room, put on their hoodie, crank up the techno, and write the application on their own. 

When we sit down at our keyboard, we assume that we sit down alone, with our precious treasure trove of programming knowledge that we have built up over the years, hoarding the nuggets about byte size and data types like a dragon guarding its gold. It is us and our hard-earned brain patterns from years of algorithm classes and hundreds of pages spent trawling StackOverflow versus the machine, working alone, against each other. 

We sit down, we crack our knuckles and deserialize the knowledge from our brain into clear streams of beautiful code, in solitude and blessed silence.  These days, we are spending less time with books and search results and more time with ChatGPT or Claude, and it’s this prompting process, we assume, that will ultimately replace us. But that’s only true if we consider the developer as an individual unit of work separate from anything else, without our own context window.

[Machine learning is compression](https://vickiboykis.com/2024/01/15/whats-new-with-ml-in-production/) and LLMs doubly so.  When we sit with an LLM, we are not only asking the average of the internet but the average, [as Karpathy puts it](https://x.com/karpathy/status/1862565643436138619), of every human data labeler that has contributed to moving LLMs forward.

When we ask LLMs to write code, we get back compressed representations of the entire field, optimized via RLHF, sorted by descending probability, and limited by the size of the model’s context window. So we are not alone, but we are also often don’t ascend outside a local minimum of average code, sometimes not even outside the [local minima of suckiness.](https://vickiboykis.com/2021/08/05/the-local-minima-of-suckiness/) Not to mention, these models have no access to hundreds and thousands and millions of lines of elegant code that has never seen the public internet because it sits quietly, undiscoverable in corporate git servers. When we ask the model, almost any model (with the exception of finetunes on our own data), we are peeking into a very small window of what good code could be. 

When I sit down at the keyboard, I also sit down with the average of the local minima, but I also have a secret superpower. I’ve had the enormous good fortune to work with and learn from very good developers, and it’s the average habits of all of these developers that lift me out of the depths of compressed scraped GitHub.  

These developers’ good habits stream from my memories into my consciousness.  As I write code, they enrich my decision-making process, like a radio station of good advice that never turns off.  

_A_ insists that I need to learn fundamental algorithms and data structures inside and out because they are the basis for what makes good code fast. “Even if you never implement a LinkedList in your  own code, you’ll be able to more clearly reason through the decisions others have made and use the libraries that make the most efficient use of them.”

_B_ tells me, through hundreds of PR comments over years and years, that it’s not good enough to have a PR that just passes tests. The code needs to be clean, reasonable, and legible for others because we read code more than we write it. 

_C_ tells me to write code that is elegant.  C says it will take longer to write this kind of code than something that just ships, but that it is our professional responsibility as developers to carve out that time, to demand it. “Write your code, then write a second time. Make it work, make it right, make it fast.”

_D_ tells me not to mess with fancy tools. A simple print debug statement goes a long way. A 2-line unit test fixes 100 lines of obtuse code. Code completion editors don't work when you don't know what you want to autocomplete, yet. 

_E_ tells me to master my tools, to dig into problems I don’t understand, to get to a reproducible example, to play with things in the terminal, take them apart, write smaller and smaller pieces of code until they make sense. 

_F_ tells me to take time to analyze the data correctly, and to be crisp and concrete about my analysis. 

_G_ and _H_ tell me to start without machine learning if I can, and if I have to use it, to use a simple model first of all. 

_I_ tells me to get to a local development loop as soon as possible. 

_J_ tells me to take my craft seriously, but never myself. 

_K_ tells me to be patient and have grace, both for others, and for myself. Code is hard, but humans are harder. 

[Above all of them is Grug](https://grugbrain.dev/), constantly whispering “complexity bad” with every line I add. 

Over and over my Alphabet Radio plays my greatest hits, fixing a variable name, digging through a class hierarchy, adding a test, using dependency injection, avoiding unnecessary libraries, deleting lines of code until the number of lines I add is less than the number I subtract, refactoring, refactoring, refactoring.  

I am so lucky to have my Alphabet Radio. Engineers like me who come from data land sometimes don’t get the privilege of building a playlist like this [because we don’t work in teams](https://www.ethanrosenthal.com/2023/01/10/data-scientists-alone/), and for a long time at the beginning of my career, I was one of these people. 

It was very lonely and I felt wrong a lot of the time without guardrails or intuition as to why what I was doing was inefficient and incorrect.  LLMs don’t and can’t help with this part. Repeated exposure to the best practices of good developers does. 

When I am at the keyboard, my joyous, cacophonous radio station keeps streaming the greatest hits on an infinite loop and allows me to rise above the local minimum, encouraging me to work harder to produce code that is not just functional, but easy for others to read, fast to modify, and doesn’t break for longer. 

Nothing is black and white. Code is not precious, nor the be-all end-all.  The end goal is a functioning product. All code is eventually thrown away. [LLMs help with some tasks](https://vickiboykis.com/2023/02/26/what-should-you-use-chatgpt-for/), if you already know what you want to do and give you shortcuts. But they can’t help with this part.  They can’t turn on the radio. We have to [build our own context window](https://vickiboykis.com/2023/09/13/build-and-keep-your-context-window/) and make our own playlist.   

When LLMs can stream advice as clearly and well as my Alphabet Radio, then, I’ll worry. Until then, I build with my radio on. 
