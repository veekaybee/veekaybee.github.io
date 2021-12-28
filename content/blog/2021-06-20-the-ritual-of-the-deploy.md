+++
aliases = ['/2021/06/20/the-ritual-of-the-deploy/']
card = 'summary'
creator = '@vboykis'
date = '2021-06-20'
description = 'We are powerful with our computers and also powerless against them'
site = '@vboykis'
title = 'The ritual of the deploy'
twitterImage = 'https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/ship.png'

+++

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/ship.png" width="300px" >}}


The Ship The Twelve Apostles, Ivan Aiazovsky

Humans have always been terrible at dealing with uncertainty. Historically, we used to cope with it by inventing superstitions. The ancient world is filled with rituals to ward off potential ill omens during times that were especially out of the control of human capability.

A big example of this has been seafaring. Since six months to a year in a ship headed to an unknown destination with unknown weather events was a high-anxiety event, humans developed a host of superstitions to deal with them. In European seafaring cultures, whistling on a ship is discouraged, as it brings attention to the wind, which can cause the weather to change for the worse. There’s a whole separate category of superstitions related to the start of a sea voyage, the most important of which is that you [never set sail on a Friday](https://dtmag.com/thelibrary/seafaring-superstitions-marine-myth-rituals-explored/). 

Once humans mastered the high seas, we moved onto the skies, and finally, the stars.  But, we carried our superstitions with us. For all the science and knowledge that cosmonauts take into space with them, their pockets are also full of ritual in the face of a grueling uncertainty. 

It's customary in Russian culture, when you're taking a long trip, to take a few minutes to sit in silence [before you start the journey](https://realrussia.co.uk/Blog/top-5-russian-superstitions-you-should-know-about-3065). Cosmonauts have a litany of similar rituals they follow before launch in Kazakhstan. They always visit the grave of Yuriy Gagarin in Baikonur in the days before they ship out. They also[ plant trees, to root themselves to the ground](https://www.atlasobscura.com/articles/cosmonaut-hotel-tree-grove). They do not watch the Soyuz rocket being rolled out onto the launchpad. They never launch on October 24, because [two accidents took place on that date.](http://www.esa.int/About_Us/ESA_history/50_years_of_humans_in_space/Gagarin_s_traditions ) 

In software development, we are beyond superstition. It is the year 2021, and we are past the age of ships navigating the world’s uncharted waters, past the elevated guesswork of the first crude Apollo 11 Guidance Computer. We have climbed out of the depths of ignorance on the shoulders of giants, past punch-cards and [actual physical bugs](https://www.atlasobscura.com/places/grace-hoppers-bug), past dial-up modems, away from manually FTPing files to servers. We have  spread our wings from the basement where the garish heat of the ENIAC lay sprawling and wheezing, and we have launched into orbit, to sleek, silent racks of servers in the Cloud that perform our every whim, from carrying out “likes”, to sending us packages, to hosting our virtual meetings. 

We confidently work with containers and pods and serialized data and horizontal scaling and millions of operations in partitioned streams per second, bending thousands of machines to our will with the press of our fingers. 

We possess an entire universe, and we command it with precision and grace as we look at our 99 percentile latency metrics, our POST response times, our model inference code.  We believe in what we can see: git blame, Grafana charts, Apache server logs. We are accurate, and we are in control. 

And yet, there is chaos at the edges. There is a place that is not as full of certainty and bluster, a place full of edge cases and ghosts, and that place is “going to production.”

The production deploy process is highly rigorous. You have some code that’s been tested and rewritten and tested again by others and approved, and then, as you merge a branch into production, you watch the code sprawl out across tens or hundreds or thousands of servers, you start watching the metrics and logs for impact. It’s all part of a very scientific checklist. 

But on the way to production,  there is much that can go wrong, and does. An [intern sends out an email to 30 million people](https://www.bleepingcomputer.com/news/technology/hbo-max-blames-mistaken-integration-test-email-on-an-intern/). A [missing config file](https://www.fastly.com/blog/summary-of-june-8-outage) brings down half the internet. Someone runs an [rm rf](https://twitter.com/vboykis/status/1406039229520613382). Someone else’s system impacts yours. The liminal space of the production deploy is filled with all possible combinations of human and computer error that can potentially occur. 

And when you go into it, no matter how much you think you control the production environment, you are entering that superset. 

---

I spend eight hours a day, mostly silent and alone, in front of a large monitor in the coolness of my semi-dark basement. But that time is never quiet for me. My daily life at work is lively and chaotic and wonderful - lots of original writing and responses to people's comments and RFCs, submitting my own pull requests and reviewing others', Slack messages, meetings, Jira updates, all of the digital detritus of an always-on heartbeat that continues to pulse even when I’m not awake.

But, when I am deploying to production, I am still.    "We're going to prod."  When developers say it this way, it sounds like a faraway place, a destination that will take some time and effort to reach, but also an endpoint, a culmination.

I don’t move when I go to prod, but it is the start of a journey. And like the start of any journey, it has its traditions. I sit down at my desk, clear out all the distractions, and begin the production deployment process. I close all unnecessary windows. I block chats. I test my code one last time. I double-check the GitHub commit. I check the current state of the system. I perform all of these to catch errors in earnest, but also as a part of superstition, a ritual that if I acknowledge them, if I follow a process everything will be ok. 

Deploying is a ritual, one of my coworkers wrote recently. It's a sacred place, a quiet place, and a dangerous place, where anything can happen. In deployment, the system is in a fragile state, and you are in a fragile state.

“The Greek word for superstition,” wrote Ernst Reis in[ On Ancient Superstition](https://www.jstor.org/stable/pdf/2935693.pdf?refreqid=excelsior%3Aa4463bfc46a8506b2d9e157aebe2aa1e) in 1895, “is δεισιδαιμονία, literally fear of demons.”  In ancient times, demons were seen as unpredictable agents of chaos, bringing disease and pestilence, destroying crops, destabilizing lives. 

When you are deploying a system into production, there are demons around you. But,  you are also the possible demon, bringing new unknowns into a system that was, just minutes ago, running fine. 

It is impossible, in the face of all of this, even as you are surrounded and bound by the laws of physics, not to have superstitions. The biggest one around production is whether or not to deploy on Fridays. There are practical considerations around whether it makes sense to do so or not, but it’s become a much bigger issue than whether you’re capable of turning around a broken system on a dime a couple hours before everyone’s out for the weekend. We don’t set sail on Fridays, and we don’t deploy. 

[There are lots of these rules of thumb](https://twitter.com/vboykis/status/1406643329664757760) that we use as guidelines to write the best, most correct code that won’t break a production build.  We say, “If it works the first time, there must be something wrong with it. Try compiling it again.” We say, “You should try to get to [100% test coverage](https://stackoverflow.com/questions/1475520/unit-testing-code-coverage-do-you-have-100-coverage).”  “Don’t deploy code that’s green in CI now but had a lot of failed builds in a row.”  “Write [clean code](https://gist.github.com/wojteklu/73c6914cc446146b8b533c0988cf8d29).” “Don’t deploy [code you’ve been looking at too long](https://flatironschool.com/blog/best-coding-advice-ever-got-take-break).” “Be skeptical [even of code that works.](https://twitter.com/lefft/status/1406648341140697089?s=20)” 

We say, “Keep variable names short, [but not too short.](https://wiki.c2.com/?GoodVariableNames)” “If the p value is very small, then we [reject the null](https://journals.plos.org/plosbiology/article?id=10.1371/journal.pbio.1002106).” (All numbers are made up, [but some are useful.](https://vicki.substack.com/p/all-numbers-are-made-up-some-are)) We say, “If you have CI/CD, you have resilience.”

We do all of these things because (given the codebase we’re dealing with at the time we’re dealing with it) they are best practices, but also, not to anger the gods of the deploy. 

If we do everything right, our build will turn green, and we can exhale.  We, writers of code, manipulators of hardware, creators of software that is eating the world, passers of LeetCode tests and grueling whiteboard interviews, grizzled veterans of the production environment, are all-powerful and yet, in this liminal land of deploys, we’re only halfway in control. The bottom breaks out, and the demons arrive. To keep them at bay, we say a prayer, press the merge button, and start watching our logs. 
