+++
card = 'summary'
creator = '@vboykis'
date = '2022-07-25'
site = '@vboykis'
title = "Looking back at two years at Automattic and Tumblr"
description = 'Lessons in life and engineering'
+++

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/seacoast.png" width="600px">}}
**SeaCoast Farewell, Ivan Aivazovsky**

For the past two years, I've been building and breaking recommender systems at `Wordpress.com` and `Tumblr`. I'm starting a new adventure soon, but this scope of work has been the most meaningful and fun of my career so far, and I wanted to reflect on a few things I'm taking away.  

The usual disclaimer in the age of [context collapse and hyperbole](https://vicki.substack.com/p/we-are-all-gaga-now) apply: all of these takes are extremely personal and don't necessarily generalize beyond `you = (me - 2 years)`.  

## On Engineering and Machine Learning

+ In the tech industry, [there is a vast scope of engineering work](https://vickiboykis.com/2019/05/10/it-runs-on-java-8/) that people don't talk about in conferences or on Twitter or in blog posts. For me, this work involved [a lot of PHP](https://boringml.com/docs/languages/php/arrays/). Since Wordpress.com and Tumblr are PHP-based monoliths, [you simply will not be able to make impactful feature changes or get to A/B test](https://twitter.com/vboykis/status/1397291014461829126) if you don't dive in. Abstracting away from PHP specifically, as a machine learning engineer, the closer you can get to working on code that impacts [production](https://twitter.com/vboykis/status/1436048726703972360), the clearer it will become what the value of your work is. Strive to move well across the stack in these systems.   

+ Cache rules everything around me. If you are working on sites with any kind of load at all, [it's in your best interest to become super friendly with Redis.](https://cachingexplained.com/)

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/cache_tweet.png" width="600px">}}

+ Along the same lines, as an engineer in 2022, regardless of where your area of depth is, you will also need to acquire an understanding of the breadth of the system that you operate in.  There are a lot of moving parts and you don't need to intimately know all of them, but the more you are able to see your work through [a systems perspective](https://twitter.com/vboykis/status/1249054452134354947), the more valuable you will be.  This means, if you don't understand how something works (i.e. aforementioned cache), it's a good idea to stop and learn it or ask someone about it. Here is a good post about [how to ask questions.](https://jvns.ca/blog/2021/10/21/how-to-get-useful-answers-to-your-questions/)


+ The best way to work through impostor syndrome is to [write and ship a lot of code that's reviewed in a supportive environment.](https://vickiboykis.com/2021/08/05/the-local-minima-of-suckiness/)


+ When in doubt, [cosine similarity](https://twitter.com/vboykis/status/1523848599691530242) as your distance measure in recsys will take you a pretty long way.

+ Software engineering has hard constraints in physics and math, but the more complicated our systems get, [the closer we get to seeing it as an art rather than an exact science](https://vickiboykis.com/2021/06/20/the-ritual-of-the-deploy/), where we're divining log entrails across five systems to figure out what went wrong. 

+ The best way to understand something is to break it. The second best way is to rewrite it from scratch without using any external libraries. 

+ Writing [data-driven unit testing is still hard](https://boringml.com/docs/platforms/spark/testing-dataframes/) but we need to do it. 

+ [Getting to production](https://vickiboykis.com/2021/09/23/reaching-mle-machine-learning-enlightenment/) is more important than having something that's feature-complete. Shipping an app that prints "Hello world" in Kubernetes, with ingress/egress/monitoring, CI/CD/etc, [is infinitely more useful](https://twitter.com/vboykis/status/1488630120994775044) than having a complex machine learning model that kind of works on your machine. 

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/abraham.png" width="600px">}}

+ [Chesterton's fence](https://www.meyerperin.com/posts/2022-04-02-chestertons-fence.html) is an important concept to keep in mind when starting a new job or a new scope of work. The [context of why a certain architectural choice was made](https://vickiboykis.com/2021/11/07/the-programmers-brain-in-the-lands-of-exploration-and-production/) is just as important as understanding its current pain points. 

+ 10x engineers are those engineers who not only know their area of expertise but help others master it as well so that they can also become more productive. Part of the implicit responsibilities of a senior developer are to help others around you also become senior. Another sign/responsibility of a senior engineer is to [leave something better than you found it.](https://vickiboykis.com/2022/02/21/on-owning-a-software-problem/) 

+ You will not get far in today's ML environment without diving into [the academic literature](https://twitter.com/vboykis/status/1546815131505643521) around as a starting point, but you will not get anywhere by just reading academic papers. At some point you need to jump and write some terrible code. 

+ All programming languages have their weaknesses and strengths. 

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/hates.png" width="600px">}}
 
+ The current machine learning boom is about 15-20 years old at this point, and [yet it's still very much just as much an art as it is a science](https://vicki.substack.com/p/were-still-in-the-steam-powered-days), just at this point starting to congeal. This goes doubly so for recommender systems, which rely on a lot of math, but need just as much human curation and judgment at the final stages. We as humans have been doing information retrieval, candidate generation, and ranking implicitly for millennia but are just starting to understand it at a formal level.

+ Feature engineering is the main bottleneck (after data cleaning) in any machine learning pipeline. The sooner you're done with it the sooner you can do other, cool stuff, which is why there are now so many solutions that focus on around feature engineering, whether it's explicitly (feature stores) or implicitly (neural networks.) 

+ In order to continue to grow as an engineer, you need to ship either systems that impact more people (wide), or systems that are tightly scoped but more complex (deep). There is no other way to grow. 


## On People, Culture, and Communication 

+ It is impossible to onboard remotely without good sync practices. When you are just meeting a team, you need to work on a set cadence with them to understand them as people and to understand the team culture and the product.  It is impossible to stay onboarded without good async practices such as documentation. Documentation is not a luxury, [it is a critical path for remote engineering work.](https://vickiboykis.com/2021/07/17/writing-for-distributed-teams/) 
  
+ Communication is the most important part of any organization but, as an engineer, you will sadly never get credit for just [being a good communicator]( https://locallyoptimistic.com/post/glue-work/). You also need to execute (see "Shipping to production".)

+ 100% of the work is shipping clean, working code to production. The other 50% is [marketing the work that you've shipped.](https://vicki.substack.com/p/between-sales-and-execution-culture?s=r) 

+ You need to physically meet everyone you work with at some point to get a true understanding of your work environment. We have not yet gotten to the place in remote work life where all of the nuances of in-person signals and communication can be replaced by Slack, Zoom, Jira, Confluence, GitHub, and online happy hours. You do not need to continue physically meeting everyone you work with every day.  

+ Sometimes you will have to explain the same thing to multiple people and sometimes to the same people in different ways, and sometimes, also, even to yourself in different ways. This is where writing and documentation helps a lot. It's not just for others. It's for you to clarify and explain your thoughts.  

+ Seek out the [ghost knowledge](https://vickiboykis.com/2021/03/26/the-ghosts-in-the-data/) in your organization and write it down. You will make someone's day. 

+ All organizations start to evolve into slime molds, especially if they are growing. [Recognize this pattern exists and actively seek to fight it.](https://komoroske.com/slime-mold/)

+ All organizations have rules. The rules are either explicitly stated or implicit. If you don't state the rules, people will still find out about them, [it will just take much, much longer.](https://www.jofreeman.com/joreen/tyranny.htm) 

+ [Spain and Sweden](https://twitter.com/vboykis/status/1273976444104966144) are in the same timezone. 

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/timezone.png" width="600px">}}

## On the joy of engineering 

+ If you find a [product you love that intersects with an organization that has purpose and good people](https://vickiboykis.com/2022/01/02/2021-work-recap-or-the-conjoined-triangles-of-success/), working on it really will not seem like work. It will seem like a big, joyful game, a puzzle to solve. Seek out these spaces in your career and reflect on what it is about these settings that make them work for you.  

+ Making something work and run for other people is one of the greatest joys you can experience as an engineer. 

The second greatest joy is having Jenkins turn green on the first deploy. 