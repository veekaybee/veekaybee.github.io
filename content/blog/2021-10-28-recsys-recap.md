+++
aliases = ['/2021/10/28/recsys-recap/']
card = 'summary'
creator = '@vboykis'
date = '2021-10-28'
description = "What's up in recommender systems"
site = '@vboykis'
title = 'Recsys 2021 Recap'
[twitter]
  image = 'https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/april.png'


+++


{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/april.png" alt="wheat field" width="300px">}}
April, Allan D'Arcangelo

[Recsys 2021](https://recsys.acm.org/recsys21/) was in October. Since I'm now [focusing on this area of ML at work](https://applyingml.com/mentors/vicki-boykis/), I attended (virtually only, unfortunately, but the online experience was as good as it could be at a hybrid conference, so major hats off to the organizers!) at took around 30 pages of notes. 

There was SO much content! In the interest of condensing my learnings before I forget them, here is some of the  high-level content that was most interesting to me.  If you're looking for more recaps, Eugene [also has a great one.](https://eugeneyan.com/writing/recsys2021/) 

+ **Theme 1: Algorithmic responsibility** A big overall conference theme this year, both for Recsys2021 and other ML conferences (such as [Instagram’s Workshop on Recommendations at Scale](https://igworkshop2021.splashthat.com/)) is the role of recommender systems in surfacing relevant content to users, responsibly. This is something [I've thought extensively about](https://vicki.substack.com/p/the-reign-of-big-recsys) [before](https://vicki.substack.com/p/big-recsys-redux-recs-at-netflix), but mostly on my own, so it was great to hear some industry and academia perspectives on what that means holistically. [An interesting paper surfaced](https://www.hbs.edu/ris/Publication%20Files/17-086_610956b6-7d91-4337-90cc-5bb5245316a8.pdf) from the conference was that people prefer algorithmic recommendations to human ones, in specific contexts.  
 
    [Cynthia Liem’s](https://www.tudelft.nl/en/eemcs/the-faculty/departments/intelligent-systems/multimedia-computing/people/cynthia-liem) [keynote on what it means](https://twitter.com/search?q=%40informusiccs%20%23recsys2021&src=typed_query&f=top) for recommender systems to surface “worthy” content in the context of how people consume classical music was definitely noteworthy. (Video forthcoming.)   A good technical deep dive on [ways to avoid bias](https://github.com/jiawei-chen/RecDebiasing) was this repo with lots of resources, and an interesting talk featured work on trying to reverse-engineer YouTube’s recommender to audit [whether they were filtering out negative content.](https://dl.acm.org/doi/pdf/10.1145/3460231.3474241) 

+ **Theme 2: Performance evaluation of recsys** How do you know whether recommendations offered and consumed by the user are “good”? From a business perspective, we often use metrics like clickthrough rate  and other related engagement metrics and even then don’t often agree on what good metrics should be. On the system side, [evaluation of the performance of recommender systems](https://dl.acm.org/doi/10.1145/3460231.3478848) also remains a non-standardized concept across industry, with precision being the only key universally recognized metric among both academic and industry papers. Precision is generally thought of as the percent of returned results (aka recommendations) in any given set of items that are actually “good”, for some definition of “good”. 

    A recent paper surveying academic papers  found that, “in 47% of cases, we cannot easily know how the metric is defined because the definition is not clear or absent.” So we as an industry still have a while to go before we understand how to best evaluate these systems. (For more on metrics, [see my post](https://vicki.substack.com/p/all-numbers-are-made-up-some-are) about how all numbers are made up) 

+ **Theme 3: Online (aka realtime) recommendations** Another common theme has improving online recommendations, aka offering refreshed recommendations based on what the user has seen in the past n time, where n &lt; 1 minute.  This is an increasingly [hot area of machine learning](https://huyenchip.com/2020/12/27/real-time-machine-learning.html) which is also very hard to do, so it's fun to see how different companies approach it. GrubHub for example, [deals with this problem by re-training only a part of its data](https://dl.acm.org/doi/pdf/10.1145/3460231.3474599) (1 day versus 4 days) to avoid model drift (aka changes in the underlying model’s features or business environment that make models out of date (for example, user behavior changes due to Covid.) Nvidia presented an interesting way (one of the most talked-about things during the conference) of a way to serve session-based recommendations [based on HuggingFace’s Transformers](https://twitter.com/Even_Oldridge/status/1442541594343661579).

* **Theme 4: Comparable Company Architectures:**  One of the interesting points of the conference was hearing about how other companies are tackling the issues of recommendations. [Nike](https://dl.acm.org/doi/pdf/10.1145/3460231.3474621), GrubHub, [Netflix](https://dl.acm.org/doi/pdf/10.1145/3460231.3474620), and [Peloton](https://dl.acm.org/doi/10.1145/3460231.3474610) all presented some versions of their architectures and reinforced my belief that building these systems is [still very new and very hard](http://veekaybee.github.io/2021/09/23/enlightenment/) and we're all kind of building the staircase as we climb it. 




