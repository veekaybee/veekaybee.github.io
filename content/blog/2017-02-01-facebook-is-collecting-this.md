+++
date = '2017-02-01T00:00:00Z'
redirect_from = ['/facebook-is-collecting-this/']
title = 'What should you think about when using Facebook?'

+++

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/winnowers.jpg)

**Courbet, The Winnowers**

TL;DR: Facebook collects data about you in hundreds of ways, across numerous channels. It's very hard to opt out, but by reading about what they collect, you can understand the risks of the platform and choose to be more restrictive with your Facebook usage.

## Translations

 + Dutch [translation here](http://benbrandenburg.nubeter.nl/wat-je-moet-weten-als-je-facebook-gebruikt/) Big thank you to [Ben](http://benbrandenburg.nubeter.nl/)!
 + Portuguese [translation here](https://medium.com/@ibrahimcesar/sobre-o-que-voc%C3%AA-deveria-pensar-enquanto-usa-o-facebook-8982b722d5f7#.cbe422iq8). Thank you to [Ibrahim](https://github.com/ibrahimcesar)! 
 + Ukranian [translation here](http://texty.org.ua/pg/article/newsmaker/read/74461/Velykyj_Mark_Cukerberg_stezhyt_za_vamy_facebook)
 + French [translation here](https://medium.com/@motdiem/a-quoi-devez-vous-penser-lorsque-vous-utilisez-facebook-1a9c8dc125f6)
+ Russian [translation here](http://adjelly.ru/2018/02/25/facebook-is-collecting-this/) 
+ Chinese [translation here](https://chenmei.xyz/post/what-should-you-think-about-when-using-facebook)

Contents:

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [How Facebook collects data](#how-facebook-collects-data)
- [What does Facebook know before you post?](#what-does-facebook-know-before-you-post)
- [After you post: what Facebook collects about you](#after-you-post-what-facebook-collects-about-you)
- [What does Facebook do with your data internally?](#what-does-facebook-do-with-your-data-internally)
  - [Shadow profiles](#shadow-profiles)
- [What relationship does Facebook have with marketers?](#what-relationship-does-facebook-have-with-marketers)
- [What data does Facebook give to the government?](#what-data-does-facebook-give-to-the-government)
- [What does Facebook track after you leave Facebook?](#what-does-facebook-track-after-you-leave-facebook)
- [What should I think about when I use Facebook?](#what-should-i-think-about-when-i-use-facebook)
- [What should I do if I don't want Facebook to have my data?](#what-should-i-do-if-i-dont-want-facebook-to-have-my-data)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->




Facebook, for better or worse, has become our living room, our [third place](https://en.wikipedia.org/wiki/Third_place) online. It's the place where we talk to friends, sound off on the news, organize events,  grieve over people we've lost, celebrate babies, engagements, new jobs, new haircuts, and vacations. 

Facebook, the platform, has taken up such a large part of our mindshare and has started to serve as our [pensieve](http://harrypotter.wikia.com/wiki/Pensieve). Because of this, it's important to understand what Facebook, the company, is doing with our hopes, dreams, political statements, and baby pictures once it gets them. 

And gets them it does. In 2014, Facebook engineers wrote that they have about [600 terabytes of data coming in on a daily basis](https://code.facebook.com/posts/229861827208629/scaling-the-facebook-data-warehouse-to-300-pb/). 

For perspective, the size of War and Peace, the text is 3.1 megabytes. The 1966 Soviet movie version of War and Peace the movie is 7 hours long, or [8 gigabytes](https://www.reddit.com/r/iphone/comments/462isr/get_some_storage_back_by_trying_to_download_a/) in size. 

So people are uploading the equivalent of 193 million copies of War and Peace books, or 75,000 copies of War and Peace movies, every single day. 

[Facebook's Data Policy](https://www.facebook.com/policy.php) outlines what it collects and what it does with that data. However, like most companies, it leaves out the actual points that tell customers what exactly is happening. 

Frustrated by the constant speculation of where those keystrokes are going for every status update I write, I decided to do some research. All of the information below is taken from tech trade press, academic publications, and what I was able to see on the client side as a Facebook user. I've added to this post my own interpretations as a data professional working with user data for 10+ years.

If anyone working at Facebook wants to add corrections to this post, I would love to hear from them that they're not collecting and processing as much as everything below says they are. 
 
# How Facebook collects data  

To understand how Facebook data collection works, I drew a (very, very) simplified diagram. The user enters data into the UI (the application). This is the front end. 

 The data is then collected into Facebook's database (of which they have many.) This is the backend. 
 
The data that users see in the front-end is a subset of the backend data. 

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/facebookui.png)

If you're interested in more of the technical specs, there are lots of architecture diagrams on the googles. Facebook is at the cutting edge of working with big data, and their stack includes [Hive, Hadoop, HBase, BigPipe, MySQL, Memcached, Thrift,](https://www.quora.com/What-is-Facebooks-architecture-6) and much, much more. All of this is housed in lots of massive data centers, such as one in [Prineville, Oregon](https://www.facebook.com/PrinevilleDataCenter/).  


# What does Facebook know before you post? 

Facebook data collection potentially begins before you press "POST". As you are crafting your message, [Facebook collects your keystrokes](http://www.slate.com/articles/technology/future_tense/2013/12/facebook_self_censorship_what_happens_to_the_posts_you_don_t_publish.html). 

Facebook [has previously used to use this data to study self-censorship](http://www.aaai.org/ocs/index.php/ICWSM/ICWSM13/paper/viewFile/6093/6350) (PDF.)

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/prepost.png)

The researchers write, 

> We report results from an exploratory analysis examining “last-minute” self-censorship, or content that is filtered after being written, on Facebook. We collected data from 3.9 million users over 17 days and associate self-censorship behavior
with features describing users, their social graph, and the interactions between them. 

Meaning, that if you posted something like, "I just HATE my boss. He drives me NUTS," and at the last minute demurred and wrote something like, "Man, work is crazy right now," Facebook still knows what you typed before you hit delete.

 Here are the data points they used to conduct their study: 

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/prethought.png)

Something of interest here is: deleted posts, deleted comments, and deleted checkins. Just like there's no guarantee that things you didn't write won't be stored, there's no guarantee that,if you delete data, the data is actually deleted. 

So, even if you delete a post, Facebook keeps track of that post. Facebook keeps track of the metadata, or the data about your data. For example, the data of a phone call is what you actually talked about. The metadata is when you called, where you called from, how long you called for, etc. 

For Facebook, metadata is just as important as real data, and it uses that data to make extrapolations about who you are. Using Developer Tools on Chrome, it's relatively easy to see the plethora of data passed to Facebook from your client to their backend via xhr. I'm not a front-end ninja (but would actually love to talk to one to see what else we could pull), but from 
one image, you can see that facebook is tracking the time you spend doing ...something? Not clear what, but it probably figures into time spent on the site, [which Facebook reports out](https://www.nytimes.com/2016/05/06/business/facebook-bends-the-rules-of-audience-engagement-to-its-advantage.html?_r=0). 

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/xhr-fb.png)

Incidentally, this is true for account deletions, as well. 

Since Facebook has so many systems and so many places where data can co-mingle, as a [former Facebook consultant writes](https://www.quora.com/When-you-permanently-delete-your-Facebook-account-what-happens-to-your-information), 

> To answer the first part of your question, "Could you pay Facebook to properly delete all your information?", assuming "properly" means completely wipe away any trace that you ever existed on Facebook, the answer is no.  

Similarly, with deleted posts, there's no guarantee that Facebook doesn't even keep the post itself on the backend database; just that it won't show up on the client-facing site. 

Once you actually do write a post, upload a picture, or change any information, everything is absolutely fair game for Facebook's internal use in research, reselling to marketing aggregators like Acxiom, and to give to the United States government, [through agencies like the NSA](http://www.nytimes.com/2013/09/29/us/nsa-examines-social-networks-of-us-citizens.html), through its [PRISM program](https://www.theguardian.com/world/2013/jun/06/us-tech-giants-nsa-data). 

# After you post: what Facebook collects about you

Facebook obviously collects all of the data you volunteer to them: your political affiliation, your workplace, favorite movies, favorite books, places you've checked in, comments you've made, and any and all reactions to posts. Facebook [allows you to download a subset](https://www.facebook.com/help/302796099745838) of the data they have in their database about you. 

In my personal subset, I was able to see:

+ photos I uploaded and photos tagged of me
+ videos
+ everything I've ever posted to my own timeline (including events I indicated interest in, what people posted to my timeline, memories shared,)
+ friends and when I became friends with them
+ All of my private messages
+ Events I've attended
+ Every single device I've ever logged in from

And, which ads I would be interested in. This is not something I put in myself. This is something Facebook generated algorithmically based on every single thing I've posted: 

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/ads.png)

But we'll get to that in the advertising section. 

In addition to the data and metadata, Facebook also tracks intent. One of the ways it does this has already been explored: unposted statuses. Another is [heatmap tracking](https://techcrunch.com/2016/08/10/360-storytelling/) of engagement during videos. 

In addition to everything it knows about you, it also knows everything about your friendships. All of this is to say, Facebook knows quite a bit about you, even if you don't flesh out your profile or actively post to the site. 

# What does Facebook do with your data internally? 

Facebook does quite a few things with the data it collects.  

First, it conducts simple queries on information to improve site performance or to do business reporting (for example, what was the uptime on the site, [how many users does Facebook have](http://veekaybee.github.io/how-many-users-do-you-have/), how much ad revenue did it make today?) This is true for every single company anywhere. 

However, with Facebook, there's a twist.  It has an entire engineering team dedicated to building tools to make data easier to query with SQL-like language built on top of Hadoop, with [Hive](https://www.facebook.com/notes/facebook-engineering/hive-a-petabyte-scale-data-warehouse-using-hadoop/89508453919/), and, although Facebook claims that access is strictly controlled, some accounts say [otherwise](http://www.techtimes.com/articles/36186/20150227/who-access-account-details-facebook.htm#sthash.GXFknTxA.dpuf). 

> Paavo Siljamäki, director at the record label Anjunabeats, brought attention to the issue when he posted, on Facebook, that on a visit to the company's L.A. office an employee was easily able to access his account without asking him for his password.

Here are [some more accounts of Facebook employees accessing private data](https://www.quora.com/Do-Mark-Zuckerberg-or-Facebook-employees-have-a-skeleton-key-granting-them-access-to-every-members-Facebook-profile-page-and-information). 

Second, Facebook conducts academic research by using its users as guinea pigs, a fact that is not mentioned in the Data Policy, which is interesting, given that on [Facebook Research's main page](https://research.fb.com/), a header reads, "At Facebook, research permeates everything we do."

It has a pretty large data science team ([41 people at last count](https://research.fb.com/people/page/3/?cat=6). ) To put in perspective, a similarly-sized company of 15,000 might have 5 data scientists, if it's really trying to aggressively push a data science research program. 

However, as late as 2014, there was no process to keep check on what kind of data was accessed, and for what kind of studies. As a [former Facebook data scientist wrote](http://andrewledvina.com/code/2014/07/04/10-ways-facebook-is-the-devil.html), 

>While I was at Facebook, there was no institutional review board that scrutinized the decision to run an experiment for internal purposes. Once someone had a result that they decided they wanted to submit for publication to a journal, there definitely was a back and forth with PR and legal over what could be published. If you want to run a test to see if people will click on a green button instead of a blue button you don't need approval. In the same way, if you want to test a new ad targeting system to see if people click on more ads and revenue goes up, you don't need institutional approval.

While he goes on to note that this is normal at most software-as-a-service companies, most SaaS companies also don't granularly collect the most intimate details of peoples' lives over the course of over a decade. 

He goes on to note, 

>The fundamental purpose of most people at Facebook working on data is to influence and alter people's moods and behaviour. They are doing it all the time to make you like stories more, to click on more ads, to spend more time on the site.

While this is understandably a goal of most websites, you may want to think twice about spending more than [40 minutes a day](http://www.businessinsider.com/how-much-time-people-spend-on-facebook-per-day-2015-7) on a site that is aimed to undermine you emotionally. 

In addition to Facebook mining text and [studying our emotions](https://research.fb.com/support-when-you-re-feeling-blue/), it also [manipulates them](https://www.theguardian.com/technology/2014/jun/29/facebook-users-emotions-news-feeds). 

The News Feed is prime for manipulation, specifically because Facebook has engineered it to be as engaging as possible: it's synaptic sugar to our nervous systems. Facebook wants to make sure you spend as much time on the [Feed as possible](http://newsroom.fb.com/news/2015/06/news-feed-fyi-taking-into-account-time-spent-on-stories/), and to this end will spend [more time surfacing pictures of babies](https://www.wired.com/2014/04/babies-on-facebook/) and other happy things, as well as news items that generate controversy and outrage, over normal statuses like "I had breakfast today." that don't garner reactions. 

This is how today's so-called filter bubble got started. Because people click on things that are interesting to them, Facebook shows only things that engage people, meaning that other points of view, friends, and images, are omitted from a person's Facebook Feed diet. For an excellent example of how this works, check out [Red Feed, Blue Feed](http://graphics.wsj.com/blue-feed-red-feed/), which shows how differently liberal and conservative Facebook feeds look. 

What else are they studying? The rate at which [gay people come out](https://research.fb.com/americas-coming-out-on-facebook/), to start with. How do they know this? "Over the past year, approximately 800,000 Americans updated their profile to express a same-gender attraction or custom gender."

A lot of Facebook's studies center on graph theory; that is, how we relate to our friends; in other words, it's performing anthropological research on subjects that have never consented to it. 

For example, recently, the data science team published a study on the [social ties of immigrant communities in the United States](https://research.fb.com/publications/the-social-ties-of-immigrant-communities-in-the-united-states/), where the researchers use the following data: 

> We limited our analyses to aggregate measures based on deidentified social network data for people from the U.S. who used Facebook at least once in the 30 days prior to the analyses. We used the home town specified by the person in their profile to determine the person’s home country. 

> Furthermore, we also restrict our analysis to people with at least two friends currently living in their home country and another two friends currently living in the United States. Our results are based on a sample of more than 10 million people who satisfy these criteria. Throughout the paper, all references to people on Facebook will implicitly assume these constraints

These are the studies we know to be public. What else are they doing under wraps? 

Another thing Facebook likes to study, is, understandably, [faces](https://www.bloomberg.com/news/articles/2016-10-26/is-facebook-s-facial-scanning-technology-invading-your-privacy-rights).  Every time you tag yourself in a picture, Facebook recognizes you and will adjust accordingly. 

>Facebook encourages users to "tag" people in photographs they upload in their personal posts and the social network stores the collected information. The company uses a program it calls DeepFace to match other photos of a person.

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/selfie.png)

This program, called DeepFace, is a fantastic way to get more accurate tags. This is also a fantastic way to violate someone's privacy. For example, what if you don't want to be tagged? Like, if you're at a government protest? Or, even, simply, if you went to a concert with one friend instead of the other and didn't want to let one know? 

Unfortunately, privacy of movement soon won't be an option. Facebook is working on ways to [identify people hidden in pictures.](http://fortune.com/2015/06/23/facebook-facial-recognition/) The Facebook [paper on DeepFace notes](https://www.cs.toronto.edu/~ranzato/publications/taigman_cvpr14.pdf) that, "The social and cultural implications of face recognition technologies are far reaching", and yet does not at all talk about the possible privacy dangers with having your face tagged, [for example](http://spectrum.ieee.org/biomedical/imaging/facebooks-face-recognition-tech-goes-on-trial), 

> "We could soon have security cameras in stores that identify people as they shop,” she says.

How do they know all of this? 

Because this is all data we give them voluntarily, with each time we post a status update, each time we upload a picture and tag it, each time we message with a friend, check in at a location, with each single time we log into Facebook and the system generates a message to be saved to the database, "Hey, this person is now in the Facebook universe", which now includes [Whatsapp](https://techcrunch.com/2016/08/25/whatsapp-to-share-user-data-with-facebook-for-ad-targeting-heres-how-to-opt-out/) and [Instagram](https://help.instagram.com/833836199971426?helpref=uf_permalink). 

## Shadow profiles 

What does Facebook do if you don't share as much data as it likes? It creates [shadow profiles](https://spideroak.com/articles/facebook-shadow-profiles-a-profile-of-you-that-you-never-created), or a "a collection of data that Facebook has collected about you that you didn’t provide yourself."

As the article states, 

> Even if you never provided them, Facebook very likely has your alternate email addresses, your phone numbers, and your home address – all helpfully supplied by friends who are trying to find you and connect.

Even worse, Facebook collects, basically..[your face](http://spectrum.ieee.org/biomedical/imaging/facebooks-face-recognition-tech-goes-on-trial). 

> One recent lawsuit focuses not on email addresses or phone numbers, but instead “face templates”: whenever a user uploads a photo, Facebook scans all the faces and creates a “digital biometric template”.

All this would be concerning even if Facebook collected the data just for itself. But then there are the external vendors. 

# What relationship does Facebook have with marketers? 

Facebook's Data Policy notes that it partners with other vendors to collect data about you: 

> We receive information about you and your activities on and off Facebook from third-party partners, such as information from a partner when we jointly offer services or from an advertiser about your experiences or interactions with them.

[It collects](http://www.techtimes.com/articles/190902/20161231/facebook-knows-a-lot-about-your-offline-habits-buying-third-party-data-to-serve-better-targeted-ads.htm) "roughly 29,000 demographic indicators and about 98 percent of them are based on users' activity on Facebook."

> Roughly 600 data points, meanwhile, reportedly come from independent data brokers such as Experian, Acxiom and others. Users reportedly don't get access to this demographic data obtained from third parties.

In addition to collecting all the details you volunteer about yourself, such as full legal name, birth date, hobbies, religion, and all the places you ever went to school or worked,  Facebook also makes assumptions about things it doesn't know so it can [share that data with Acxiom, and other advertising powerbrokers to more effectively target to you. 

For example: household income, which it then uses to create a data profile to sell to marketers, who are, after all, its paying customers.  Marketers can then buy fine-grained profiles that include [any and all of the following](https://www.washingtonpost.com/news/the-intersect/wp/2016/08/19/98-personal-data-points-that-facebook-uses-to-target-ads-to-you/): 

Location, Age, Generation, Gender, Language, Education level, Field of study, School, Ethnic affinity, Income and net worth, Home ownership and type, Home value, Property size, Square footage of home, Year home was built, Household composition. 

How does Facebook know this? By making [some assumptions about you](https://www.quora.com/How-accurate-are-income-levels-in-Facebook-ad-targeting-and-how-does-Facebook-gather-this-information) based on the data it knows and what it gets from Experian and the like. 

This data can then be used to target Facebook users in the form of ads.  The kinds of targeting you can do on Facebook tells you a lot about the data they keep behind the scenes. For example, you can not only target by location/age/gender/language, you can also do hobbies and life stages (i.e. just engaged, engaged 6 months ago, children in early school age). It's possible to target someone this narrowly and still it have reach a certain number of people (in my example, it was 100-200). 

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/targeted.png)

This data can then be resold downstream, where it's blended with other data that exists about you through credit cards and other marketing sources, to create sites [like this](http://www.familytreenow.com/) that will try to build out a full profile of you.  There is no easy way to get out of this, because once data is created, [it's much harder to delete.](http://idlewords.com/talks/haunted_by_data.htm) This is why one of the primary concerns of privacy activists is getting companies to bulk-delete data after a certain period.  

Facebook also has the right to use your, and [your child's if they're under 18, pictures in ads](https://www.facebook.com/help/116356655118482?helpref=related). 


# What data does Facebook give to the government? 

We don't know everything that Facebook gives to the government. Facebook does have a [Government Reports page](https://govtrequests.facebook.com/), which hasn't been updated since June 2016. We do know, however, that government is asking for [more and more information.](https://techcrunch.com/2016/12/21/government-requests-for-facebook-user-data-up-27-percent-in-first-half-of-2016/)

That data leads to a report which shows how much data was accessed and how many users were impacted, but doesn't say anything additionally about the kind of information given, or what kind of agencies were accessing it (local, state, FBI/NSA). 


| Country       | Total Requests for User Data | Total User Accounts Referenced | Total Percentage of Requests Where Some Data Produced | Content Restrictions | Preservations Requested | Users/Accounts Preserved |
|---------------|------------------------------|--------------------------------|-------------------------------------------------------|----------------------|-------------------------|--------------------------|
| United States | 23,854                       | 38,951                         | 80.65%                                                | 0                    | 31893                   | 56714                    |

Mark Zuckerberg even issued a statement, [saying](https://www.facebook.com/zuck/posts/10100828955847631), 

> Facebook is not and has never been part of any program to give the US or any other government direct access to our servers. We have never received a blanket request or court order from any government agency asking for information or metadata in bulk, like the one Verizon reportedly received. And if we did, we would fight it aggressively. We hadn't even heard of PRISM before yesterday.

Again, here it's important to read between the lines.  Direct access to servers is not necessary to send bulk files. [It's also not necessary to know about PRISM by that name. ](https://www.theguardian.com/world/2013/jun/06/us-tech-giants-nsa-data)

It's also hard to know whether the NSA is collecting data from [Facebook in some other way](https://www.nytimes.com/2014/06/01/us/nsa-collecting-millions-of-faces-from-web-images.html). In Europe, at least, [lawsuits are underway](http://fortune.com/2016/09/12/facebook-class-action-eu-privacy/) on this issue. 

But for now, just assume this [surveillance is ongoing](http://www.cnn.com/2013/09/30/us/nsa-social-networks/). 


# What does Facebook track after you leave Facebook? 

Outside of Facebook.com, Facebook tracks you through [Single-Sign On](http://venturebeat.com/2014/10/06/the-cookie-is-dead-heres-how-facebook-google-and-apple-are-tracking-you-now/). 

If you log out, Facebook also tracks you through cookies. As their [privacy policy states](https://www.macobserver.com/tmo/article/facebook-is-tracking-you-even-if-you-dont-have-an-account), 

> We collect information when you visit or use third-party websites and apps that use our services. This includes information about the websites and apps you visit, your use of our services on those websites and apps, as well as information the developer or publisher of the app or website provides to you or us.

Facebook also is trying to track, or already tracking, [how your cursor moves across the screen](http://blogs.wsj.com/cio/2013/10/30/facebook-considers-vast-increase-in-data-collection/).  

As early as 2011, [it also began tracking how you move across the web if you're still logged into Facebook](http://lifehacker.com/5843969/facebook-is-tracking-your-every-move-on-the-web-heres-how-to-stop-it). 

> Facebook keeps track of where you are on the web after logging in, without your consent. Nik Cubrilovic dug a little deeper, and discovered that Facebook can still track where you are, even if you log out. Facebook, for its part, has denied the claims. 

It's safe to say it [collects your browsing history to enrich ads](http://gizmodo.com/how-to-stop-facebook-from-sharing-your-browsing-history-1589918083). 


# What should I think about when I use Facebook?

What does this all mean? Essentially, it means that every single thing you do on Facebook, and if you're logged in, on other websites, is potentially tracked by Facebook, and saved on their servers. 

To be clear, every company currently does some form of this tracking of users. There would simply be no other way to measure operations. But Facebook has quite clearly been tiptoeing outside the bounds of what is ethically acceptable data business practices for a while.  Even if Facebook is currently not doing some of the things I mentioned (capturing pre-posts, messing with the News Feed,) they're doing very similar work and there's no guarantee of privacy or not being used in an experiment.  It also means if you're not active on Facebook, you could still be tracked. 

Every single like you gave a post, every friend you added, every place you checked in, every product category you clicked on, every photo, is saved to Facebook and aggregated. 

Aggregated how? Hard to say. Maybe as part of a social experiment. Maybe your information is being passed over to government agencies. Maybe individual employees at Facebook that don't necessarily have the right permissions can access your page and see your employment history. Maybe that same employment history is being sent over to insurance companies.  

This includes all private groups, all closed groups, and all messages. And, as Facebook points out, 
There is no such thing as privacy on Facebook. 

Essentially, what this means is that you need to go into Facebook assuming every single thing you do will be made public, or could be used for advertising, or analyzed by a government agency. 


# What should I do if I don't want Facebook to have my data?

Facebook started as a way for college students to connect with each other, and has eventually gotten to the point where it's changing people's behavior, tracking their usage, and possibly aggregating information for the government. 

The problem is that each person, whether he or she uses Facebook or not, is implicated in its system of tracking, relationship tagging, and shadow profiling. But this is particularly true if you are an active Facebook user. 

So the most important thing to is to be aware that this is going on and give Facebook as little data as possible. 

Here is a list of things I do to minimize my exposure to Facebook. 

Not everyone will do what I do. But the most important thing is that, even if you decide to continue using Facebook as you've done, you're aware of what Facebook is doing with your data and are empowered to make the tradeoffs that come with being social. 

1. Don't post excessive personal information. 
2. Don't post any pictures of your children, especially if they're at an age where they can't consent to it. 
3. Log out of Facebook when you're done with it on your browser. Use a separate browser for Facebook and a separate one for everything else. 
4. Use [ad blockers](https://chrome.google.com/webstore/detail/ublock-origin/cjpalhdlnbpafiamejdnhcphjbkeiagm?hl=en)
5. Don't use Facebook, particularly Messenger to organize or attend political events. If you need to organize, use Facebook as a starting point, and then get on another platform. 
	Recommended Platforms: Signal is the gold standard for private chat right now.  Whatsapp is ok for group chat, but, I don't recommend because it's tied to Facebook's system of metadata. Telegram is also good, but not as good because it's closed source.  Again, it depends on your level of risk. Here is [more on](https://www.relativisticramblings.com/ramblings/telegram-vs-signal/) [these platforms](http://lifehacker.com/signal-the-encrypted-chat-app-is-now-available-on-des-1787103250). 
6. 	Don't install the Facebook app on your phone. It asks for a lot of [crazy permissions](http://www.makeuseof.com/tag/use-facebook-android-without-invasive-permissions/). 
7. Don't install [Messenger on your phone](https://www.facebook.com/help/messenger-app/783897061631577).  Use the mobile site. Messenger is blocked on mobile now, so use the workaround of [enabling the desktop site on your browser](http://www.howtogeek.com/176932/how-to-disable-the-mobile-version-of-a-website-on-your-phone/).  

It's very sad that a social network that's done so much good is also the single worst thing about the internet, but until people leave the platform or apply some kind of economic pressure on it, nothing will change. 

For my part, something I personally as a data professional have done is send Facebook recruiters that email me the following message: 

Dear Recruiter, 

The way Facebook collects and uses data, including:
	
+ reselling user data to advertising companies like Acxiom, 
+ [tracking user browsing](http://www.huffingtonpost.com/nate-hanson/how-to-stop-facebook-from_b_8160400.html), 
+ [facial recognition](https://www.allbusiness.com/marketers-use-facebooks-facial-recognition-stalk-customers-2-11934-1.html), 
+ the creation of [shadow profiles](http://www.dailydot.com/news/facebook-shadow-profiles-privacy-faq/),
+ and particularly social science experimentation like [emotional
 contagion*](http://www.pnas.org/content/111/24/8788.full), 
+ the use of algorithms in the News Feed to create a [filter
  bubble](https://www.theguardian.com/us-news/2016/nov/16/facebook-bias-bubble-us-election-conservative-liberal-news-feed)
+ and, most importantly, [give access of the wealth of data Facebook has made available](http://venturebeat.com/2014/05/15/how-the-nsa-fbi-made-facebook-the-perfect-mass-surveillance-tool/) to government bodies like the NSA
	
has made me not only strongly oppose working there but has made me strongly evaluate my usage of Facebook, because I never know how every keystroke I enter into the system will be used. 
	
If Facebook as a company is committed to changing direction and 
	
+ using data to fight some of these issues
+ actively working on ways to [delete unnecessary data*](http://idlewords.com/talks/haunted_by_data.htm)
+ actively working on private, secure communication that is not party to government interference
+ and actively working on ways to prevent private customer data from being shared to unnecessary third parties
	
I would love to know. 
	
Sincerely,
	
Vicki


We are social animals, and we are wired to want to connect, want approval, want to share, and want to organize on the platform where everyone else is, and this, for now, is in Facebook's advantage. Additionally, it's hard to say that Facebook is all bad: it does connect people, it has helped organize meetups and events, and it does make the world more interconnected. 

But, as Facebook's users, we and our data are its product.  And, as we understand more about how this data is being used, we can still play on Facebook's playground, by its rules, but be a little smarter about it. 





[Hacker News Discussion here](https://news.ycombinator.com/item?id=13559498). 
