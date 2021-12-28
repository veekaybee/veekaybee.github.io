+++
date = '2018-03-12T00:00:00Z'
title = "It's still hard for beginners to get started with Python"

+++

<meta name="twitter:card" content="summary">
<meta name="twitter:site" content="@vboykis">
<meta name="twitter:creator" content="@vboykis">
<meta name="twitter:title" content="It's still hard to get started with Python.">
<meta name="twitter:description" content="New developers don't know what they don't know.">
<meta name="twitter:image" content="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/snakes.jpg">

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">&quot;All of my local Python environments&quot;<br>M.C. Escher, 1969 <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/iosQJnXJXj">pic.twitter.com/iosQJnXJXj</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/925467002642354176?ref_src=twsrc%5Etfw">October 31, 2017</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

Python is, for a good reason, one of the easiest programming languages to get started with for people new to computational data analysis specifically, and new to programming in general. 

But it's still really hard to get started with. [Allen Downey](http://greenteapress.com/wp/think-python/), who's been teaching Python for years, [recently wrote a great post about this](http://allendowney.blogspot.com/2018/02/learning-to-program-is-getting-harder.html). He says,  

> I have written several books that use Python to explain topics like Bayesian Statistics and Digital Signal Processing.  Along with the books, I provide code that readers can download from GitHub.  In order to work with this code, readers have to know some Python, but that's not enough.  They also need a computer with Python and its supporting libraries, they have to know how to download code from GitHub, and then they have to know how to run the code they downloaded.

The amount of cognitive overhead needed to be a developer today results in a problem I've seen with senior developers sometimes. They forget how much context people need to learn in order to become proficient at development. And they forget that they were beginners once, too. 

There's two recent pieces that adress exactly how much context is needed in order to program. One is Paul Ford's beautifully voluminous ["What is Code,"](https://www.bloomberg.com/graphics/2015-paul-ford-what-is-code/) in which he tries to summarize the enormously complex world of computer programming. He starts, "A computer is a clock with benefits." 38,000 words later, after moving through logic gates,RAM, Microsoft Word,algorithms,the Go gopher,and OOP,  he ends with simply, "Hello, world." 

In a way, that's how beginners work, too. They wrestle and wrangle with layers and layers of understanding, of local environments and the cloud, the difference between JSON, SQL, and Python, NAND gates, CPUs, mega versus gigabytes, and everything in between, until they finally, finally are able to install Python and `print()` that "Hello, World!" to the screen.  (By the way, while changing print to a function [is a really nice standardizing move](https://snarky.ca/why-print-became-a-function-in-python-3/) appreciated by senior developers, this is a really confusing and annoying change for beginners.)

Think about how complicated computers are in general, and how many layers of things need to work together, and how you need to have at least some high-level understanding of how those layers of things work, and you'll get a better understanding of what a junior developer is up against.  One of my favorite posts on this is [this one.](https://plus.google.com/+JeanBaptisteQueru/posts/dfydM2Cnepe), which goes through what happens when you go to google.com on your browser, and winds up talking about thinly sliced wafers of highly purified single-crystal silicon ingot. 

> That is also why it's so hard for technologists and non-technologists to communicate together: technologists know too much about too many layers and non-technologists know too little about too few layers to be able to establish effective direct communication.

Another - more recent -  post that touches on this difference, the boundary between people just at the edge of technical discovery and experts is ["Building for muggles."](https://austenallred.com/building-for-muggles-101679e12a0a) The author writes that Slack was able to become successful because they understood that most people don't know how to access IRC, or even what the word client means, and were able to successfully translate the world of distributed chat protocols into something anyone could access. 

Each person gradually becoming more technical goes down this path, from not knowing anything, to [building serverless applications](http://veekaybee.github.io/2018/02/19/creating-a-twitter-art-bot/), by stumbling around and asking [a lot of questions](https://jvns.ca/blog/good-questions/). I went down this path when I started my journey into data science (although it definitely didn't feel like a journey at the time - more like a blindfolded freefall into the darkness of the command line.)

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/pythonwhat.png)

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">All the roads are pointing to Python for me. <a href="https://twitter.com/hashtag/resistanceisfutile?src=hash&amp;ref_src=twsrc%5Etfw">#resistanceisfutile</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/240519556043526145?ref_src=twsrc%5Etfw">August 28, 2012</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

I started installing Python in 2012 on a Windows machine: 

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">I must have done something pretty bad because karma is coming back to me in the form of installing Python for Windows. <a href="https://twitter.com/hashtag/attempt3?src=hash&amp;ref_src=twsrc%5Etfw">#attempt3</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/466678032200384512?ref_src=twsrc%5Etfw">May 14, 2014</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

And, instead of being able to immediately write code, I was angry a lot, even more than Twitter would have you believe. In fact, it was so frustrating, that I channeled all of my energy into [this post](http://vickibwrites.com/2015/06/05/implementation/). 

As someone with a lot of Python experience now, my stance is that it's still incredibly hard to understand how to install Python for people new to both Python and development. The best way to get an idea of how hard it can be is to do a Google search.   

If you're just Googling for Python installation instructions, you'll get this page, which tells you how to install 3.6 on Windows, as the first result.  

![windows](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/windowspython.png)

It doesn't tell you anything about why you should be installing Python 3.6 over anything else. The second link is the [official Python download page.](https://www.python.org/downloads/), which has [a helpful link](https://wiki.python.org/moin/Python2orPython3) to an overview of why you'd want to use Python 2 or 3, and says that the version you want to use depends on what you want to get done. But what if you don't know what you want to get done? You can look around, and [you'll see some articles](https://www.dataquest.io/blog/python-2-or-3/) that tell you to start with 3, some that say [it doesn't matter](http://www.practicepython.org/blog/2017/02/09/python2-and-3.html), and [as late as 2016](https://learnpythonthehardway.org/book/nopython3.html), the author of Learn Python the Hard Way didn't advocate switching, and [even when he rewrote the course](https://news.ycombinator.com/item?id=14722349), there was controversy. 

If you don't have any experienced developers telling you that you should be using 3 (you should be starting with Python 3 😁), how are you supposed to understand what to do?

Fortunately, the third link on Google is Kenneth Reitz's wonderful guide on [Python for beginners.](http://docs.python-guide.org/en/latest/)  But, once you do come across the Hitchiker's Guide, there is lots to learn: What's an interpreter? And,  focusing on Mac only, [Why do I need Xcode](http://docs.python-guide.org/en/latest/starting/install3/osx/#install3-osx)? What's Homebrew? What are environment variables? And, what is all this stuff? What's venv? Pipenv? [Which one do I need to just write "hello, world?"](https://stackoverflow.com/questions/41573587/what-is-the-difference-between-venv-pyvenv-pyenv-virtualenv-virtualenvwrappe). 

These are some of the questions I could think of that beginners would ask, but most definitely not all of them. Because I'm not a beginner anymore. In fact, I'm so far away from being a beginner that I don't understand what would be hard for a beginner anymore. This is not a brag, but is actually a problem for senior developers working in teams with people with less experience than them. 

A senior developer is able to easily overcome issues that come up. For example, understanding the benefits of Python 3, why `print()` should be a function, understanding the issues of installing Python on different operating systems, virtual environments, how two installs of Python living on the same machine would work, and much more. 

For example, try asking a novice what this means: 

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">We have heard your feedback on Python 3 and tomorrow will be making `brew install python` and `brew install python@2` PEP 394 compliant: <a href="https://t.co/Euhy1v33MO">https://t.co/Euhy1v33MO</a>. We will not change this again until PEP 394 has changed. Sorry for the pain; we&#39;ve tried our best ❤️</p>&mdash; Homebrew (@MacHomebrew) <a href="https://twitter.com/MacHomebrew/status/972192919489826816?ref_src=twsrc%5Etfw">March 9, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

What's PEP? What's a symlink? How does Homebrew work? (True story: In writing this blog post, I tried to create a new user on my computer that didn't have Python so I could see what the experience was like for a beginner and ended up somehow uninstalling and reinstalling Homebrew because I overestimated my understanding of how it works across users on MacOS. )

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">Working on a post about how it can be hard for beginners to install Python and just hosed my own environment.  🐍</p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/968315412579520513?ref_src=twsrc%5Etfw">February 27, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

The main problem with the communication gap between beginners and experts is that junior developers have [a whole hierarchy of things they don't understand](http://web.stanford.edu/~himalv/unknownunknownsws.pdf), and aren't even aware of the right way to ask the question. 

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/unknowns.jpg)

For example, [after you install Python on a Mac](https://gist.github.com/patriciogonzalezvivo/77da993b14a48753efda), you have to set the Python path. The instructions in this particular tutorial tell you to, 

`Add PATH to ~/.bash_profile and ~/.zshrc`

For someone unfamiliar with Unix systems, this sentence is like a heiroglyph to be deciphered. 

A senior developer's mental model of a language and its environments looks something like the Unknown Unknowns model on the left, whereas for a junior, it looks something like the one on the right: 

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/juniorvssenior.jpg)

And, even senior developers (aka developers who wrote the book on how to install Python) get frustrated toggling between different versions of Python: 

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">I wish `$ brew install conda` worked.</p>&mdash; Kenneth Reitz 🐍 (@kennethreitz) <a href="https://twitter.com/kennethreitz/status/972106533596168192?ref_src=twsrc%5Etfw">March 9, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

If this is frustrating to people like Allen Downey and Kenneth Reitz, imagine what a new person starting with an open command line running 

`export PYTHONPATH=/path/to/dir:$PYTHONPATH"` 

is feeling. 

If you are a senior person and have junior people that you work or interact on online forums with, it's a good idea to keep these things in mind. The people that have been most important to me in my career are the ones who were able to help me navigate through the maze of questions I had and turn my own mental model to decrease the amount of unknown unknowns I had.  

The good news, though, is that we were all beginners once, and we have the power to remember how frustrating things can be, and make it easier for the next person coming up.  We can imagine how it felt and say, "yeah, that sucks, here let me explain this to you," and be the person we needed when we were on our fifth millionth Stack Overflow search. 
