+++
card = 'summary'
creator = '@vboykis'
date = '2022-09-212'
site = '@vboykis'
title = "On the team as a system"
description = 'How humans work together to build software'
+++
{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/crowdedboat.png" width="600px">}}

**Crowded Boat II, Robert Goodnough**

I read [this great post over the weekend ](https://jacobian.org/2022/sep/9/quality-is-systemic/)and this sentence is the heart of understanding how to operate a great software team: 


    Software quality is more the result of a system designed to produce quality, and not so much the result of individual performance. 

The whole post is good and you should read it, but for me it immediately brought to mind a podcast from 2019 called “[The Dysfunction of Organizations](https://podlodka.io/132)” on the same topic. The content of that podcast has become an important part of my development philosophy. Unfortunately, it is only in Russian (unless someone wants to entirely transcribe it, which they should, because the content is really good), so I re-listened to it and took key notes. 

For context, [Podlodka ](https://podlodka.io/)is a popular tech podcast with great guests and great topics.  For this particular release, they talked to [Oleg Soroka](https://www.linkedin.com/in/olegsoroka), who titles himself as a “specialist in dysfunctional organizations” who is “interested in why we do some things maximally ineffectively even though the industry is filled with extremely smart people.”  Soroka has 20+ years of tech experience, starting as a developer (“even though I’m embarrassed by the code I wrote”), leading to management, across 12-15 different companies across both public/private sector and international clients. He spent a significant part of his career (18 years) at Kamaz, [the Russian truck/bus manufacturer](https://en.wikipedia.org/wiki/Kamaz), which becomes important, because the way he thinks about software problems is framed by the way car companies have been thinking about and building assembly lines. 

At the beginning of the podcast, he says that dysfunction exists wherever management exists (7:00) because management is one of those things that is both simple and complicated at the same time, much like human digestion. On the one hand, you have food, it goes into the system, and it comes out. At a high level, it’s an extremely simple process. But once you start to look into the intricacies of how metabolism works, how the human digestion process works, it becomes extremely complicated and hard to change the system to get exactly results you want. In response to a question of whether  “are there companies or industries that do management better than others,” he replies, drawing on his experience, that everything we’re working on in tech now like lean, kanban, kaizen, agile, etc, are things that car companies have already been working on and proving out for decades. (10:40)

First, it’s important to understand that, although the podcast uses the analogy of the car production line as similar to development work, there are a ton of differences. Soroka says that “Not all industries are on the same page, because programming is not an industrial profession. It’s a mix of science, art, charlatanry, craftsmanship (when you work with your hands), and craft (11:30).”

The critical idea of the blog post is that developers do not work alone: building a product is a team project, and the product itself [is a system](https://vicki.substack.com/p/what-we-talk-about-when-we-talk-about). And a group of developers working on a single goal also becomes a system that works either together or at cross-purposes with itself. 

The central thesis of the podcast is that, as someone who works on the system, you need to understand that different inputs create different changes in the system, and to be able to reason about those changes. 

**The team as a system**

As such, it can be very hard to reason about what good management means, because the results can be cross-purposes with the process. Around 12:30, one of the hosts asks, “What is good management? When we have medicine that is effective, we can measure its efficacy. How can we do the same with a team? How do we know that kanban is 146% better than scrum?”

Soroka says that all good management starts with understanding there exists some ideal system, which we can never reach in real life, that fulfills its function, shipping high-quality code, with 100% effectiveness. An ideal system might look something like: having a group of developers who take perfectly-written tasks from a perfectly-formulated backlog, the task is instantly achievable, the person understands the task, writes the code without mistakes, and as soon as it’s written, they press a button and deploy, and the user gets the feature immediately. 

Then, he asks the podcast co-hosts to play a game: we know that we can take away elements from that ideal system, and they will make that system less ideal, but in exchange, we will get something that we want. So, what might make a system produce 22x less output? One example is that you can add code review to the system. You lose something, speed, but you get numerous other elements in return, for example you get knowledge sharing, code that is more ergonomically correct, and a number of other, unseen positives. 

So you are taking away speed from the system but you get some intangibles in return. When you take away something from a system, you get something in return, for example, maybe psychological safety, control, less risk, etc.  but you have to make sure that what you get is worth what you take away. There are many ways to make a system, or a team, bad. But what is bad management? It’s taking elements like KPIs or OKRs or kanban, that you may have heard about in a conference, and implementing them to work on the team without considering tradeoffs.  Another example is adding the overhead of coordination between teams. When we break up teams, we make working within them quicker, but working between teams slower, because now we have to coordinate through Jira or similar, [which results in queues](https://en.wikipedia.org/wiki/Little%27s_law). 

This all goes back to the post of what makes a good team, and that is effective processes. The problem with understanding these tradeoffs is that humans are very bad at understanding things that don’t exist (with the exception of, say, missing wallets or phones,) but a critical aspect of systems is that the absence of something is just as important of a signal as its presence (22:00). For example, when we implement kanban, we set a limit on the amount of work that can be in progress. The improvement in productivity actually results from [slack in the system](https://kanbanzone.com/2019/slack-time/) - the lack of extra work adds effectiveness. 

Not only is it hard to examine gaps in the system, it’s also hard to understand what you’re giving up and what you’re adding when you institute any one thing, and it’s important when making these decisions, to look at the “diff” of the two systems. As an example, everyone says one-on-ones are important, but when you add them, what are you changing or giving up? What you’re taking away is the developer’s ability to do 8 hours of uninterrupted work, and just as there are many good ways to do one-on-ones, there are many ways to do bad ones.  The essence of a 1/1 is to provide regular feedback. 

But if you make feedback a practice that takes place, like most 1/1s weekly, we no longer have a direct way to provide feedback as it happens. Alternatively, if you can discuss anything at any time, it means that we have no formal mechanism for feedback. In order to allow yourself to manage effectively, you have to consider these changes as you implement them.  (43:18)

Not only do you have to hold the system in your head, you also have to consider that any change you implement will not be one you see the results of immediately, you need to also consider the results of the thing that you took away. 

**Unshipped code as inventory**

The second key point that Soroka made, which is something I now think about daily, is that, in the context of this system, and thinking about what things are and what they aren’t, we can consider continuous integration. (55:41)  If we talk about what it is, we get caught up in GitHub Actions, Jenkins, build systems, etc. Instead, we need to talk about what continuous integration is in the context of what we lose when we implement it. And that is, continuous integration is the **absence of long-living branches.** 

When we add continuous integration, what we lose is the surprise that happens when we have long-outstanding pieces of code that are merged into our main branches. Let’s say person A merges, then person B merges, but B’s merge breaks something. This change in state was a surprise for both A and B, which ruins the “ideal” system, and the more long-standing branches, the more inventory you have, the more surprises there are once the code gets merged into the main codebase. So it’s no wonder then, that 

Just like, again, with car factories, we pay a price for code storage, and the absence of CI means a lack of slack and productivity in the system. Long-standing code is an issue that prevents the team from being fully productive. 

**So what**

There are lots and lots of gems just like this in the podcast, and I’ve now re-listened to it three or four times. Management is complicated, computer science in industry is just as much of an art as a science, and systems made of humans are complex components that take years to get right.  The result, if applied correctly, and understood together, is a system that works for everyone, and that, when you’re thinking about building an effective team, 


    Instead of spending tons of time and effort on hiring because you believe that you can “only hire the best”, direct that effort towards building a system that produces great results out of a wider spectrum of individual performance.




