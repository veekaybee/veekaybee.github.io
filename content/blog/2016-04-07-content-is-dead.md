+++
date = '2016-04-07T00:00:00Z'
post = 'Content is dead. Long live filler!'
redirect_from = ['/content-is-dead/']

+++

It's 2016. I shouldn't have to write  a scraping script to extract the information I need from the webpage of a magazine article. But that's exactly what I had to do in February when I needed information on switching between Android and iOS. 

I was wondering what the transfer process was like (for phone numbers, specifically) from Android to iPhone, so I went to the Googles and searched for ""Android to iOS" or something similar. The third or fourth result (now fifth or sixth based on whatever magic Google constantly performs on search results - completely opaque at this point by personalizes search, cookies, and PageRank refactoring) was this Wired article: 

![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/androidtoios.png)

I haven't intentionally gone to Wired in a while, since the quality of their journalism has gone down significantly in the years since they published [the story](http://www.newyorker.com/culture/culture-desk/the-wired-origins-of-argo) "Argo", in 2007. 

In the [post-Anderson years](http://mediadecoder.blogs.nytimes.com/2012/11/16/former-creative-director-at-wired-is-named-its-new-editor/), Wired has dabbled in and eventually succumbed to the technology it once skewered and analyzed.  Its latest efforts inevitably include pandering to the [Instagram crowd](https://contently.com/strategist/2015/11/17/can-wired-make-instagram-journalism-mainstream/). 

However, nostalgia means I still have a semi-favorable opinion of them as a reliable arbiter of technical advice and opinoin, so I clicked on the link. But I'd forgotten that, like many other sites, Wired has resorted to begging for pageviews. 

![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/adblocker.jpg)

Normally, I'd just close the tab and move on. But I've gotten so many of these paywall guilt-trip messages lately from a digital media that is terrified it is falling off a cliff, that I got angry. Here's one from Forbes: 

![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/forbes-ad-blocker.jpg)

So I did what any semi-literate tech person would do:  I decided to cURL the article. 

Unfortunately, what you get when you curl any page containing even the tiniest amount of reasonable content these days is crap. 

In my case, I got over [1800 lines of crap](https://github.com/veekaybee/wired/blob/master/original_html.txt), starting with external vendor stuff: 

	<head itemscope itemtype="http://schema.org/WebSite" profile="http://gmpg.org/xfn/11">

	<link rel="dns-prefetch" href="//use.typekit.net">
	<link rel="dns-prefetch" href="//assets.adobedtm.com">
	<link rel="dns-prefetch" href="//scontent.cdninstagram.com">
	<link rel="dns-prefetch" href="//segment-data.zqtk.net">
	<link rel="dns-prefetch" href="//b.scorecardresearch.com">
	<link rel="dns-prefetch" href="//www.googletagservices.com">
	<link rel="dns-prefetch" href="//subscribe.wired.com">
	<link rel="dns-prefetch" href="//tpc.googlesyndication.com">
	<link rel="dns-prefetch" href="//stats.wired.com">
	<link rel="dns-prefetch" href="//wired.disqus.com">
	<link rel="dns-prefetch" href="//www.wired.com">
	<link rel="dns-prefetch" href="//condenast.demdex.net">
	<link rel="dns-prefetch" href="//p.typekit.net">
	<link rel="dns-prefetch" href="//cdn.tt.omtrdc.net">
	<link rel="dns-prefetch" href="//cdn.optimizely.com">
	<link rel="dns-prefetch" href="//a.disquscdn.com">

So I had to write a Python script to parse out the text I actually needed, about the Android to iOS migration: 

```python
# -*- coding: utf-8 -*-
	import urllib2
	from bs4 import BeautifulSoup
	import re

	address = "http://www.wired.com/2015/10/how-to-switch-android-ios/"

	response = urllib2.urlopen(address)
	soup = BeautifulSoup(response,"lxml")
	paragraphs = soup.find('article').find_all('p')

	for paragraph in paragraphs:
		print paragraph.text
```

The [resulting text](https://github.com/veekaybee/wired/blob/master/article.txt) was 21 lines of actual content, just under 1,000 words, wrapped in hundreds of lines of ad tech and unecessary Javascript.  It took me about half an hour of work to get at what I needed, which was a 5-minute read of bullet points I needed to consider when migrating phones. 

You could argue that web scraping is a ridiculous and unfair thing to do to the writer who spent time putting together the content and the publication that acts as a central repository for trustworthy content.   But if I stop using AdBlock on Wired, what are the tradeoffs in response for five minutes' worth of information that I could potentially get at another site? 

1) [Adtech collects my information](http://idlewords.com/2015/11/the_advertising_bubble.htm) and I get put in a table (or RDD, if it's a really hip startup) of 25-34 females and the next time I sign into Facebook, I get ads for fertility clinics and custom bachelorette party t-shirts. 
2) If I decide not to enable ads, but instead to pay $52 for weekly access to Wired, there is absolutely no guarantee that I will want to read anything else Wired puts out there. 

The argument here is that I should pay for good quality content. But if I pay for Wired, I'm not guaranteed that all the articles will be as good as this one. Right now, this is the Wired front page: 

<center><img src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/wiredtoday.png" alt="Drawing" style="width: 500px;"/></center>

A bunch of election news I can get somewhere else, a phone review, a review of Moleskine notebooks, and a tutorial on how to use Snapchat. Where's the content? 

And, if I pay for Wired, I then also have to pay for all the other sites that now have ad-free versions. So I wind up with a credit card bill that is bigger than what I actually do spend for The Economist, The New Yorker, and the New York Times on a monthly basis. 

I am not alone. Right now on Reddit, there is a whole thread of people trading tricks about how to [block content anti-patterns](https://www.reddit.com/r/LifeProTips/comments/4dmj6v/lpt_if_youre_trying_to_read_an_online_article_and/).

## Where do we go from here? 

The common answer to this problem seems to be micropayments. There have been some [promising moves in this space](https://medium.com/on-blendle/blendle-a-radical-experiment-with-micropayments-in-journalism-365-days-later-f3b799022edc#.wmfzhedan). But, as another post points out, articles, in a world where another article is just a click away,  are not worth 27 cents, or whatever micropayment is decided, especially given consumer expectations. 

The only answer, as I see it, is to cut back on the junk and start writing good content again, and to charge in full for it. In a world full of Buzzfeeds, Wireds, and Voxes, there are only a handful of Aeons, Economists, [New Yorkers] whose strategy is not reliant on ad networks, but on a reputation. 

>The challenge, however, for The New Yorker, has been balancing that higher metabolism of the Web with its strong tradition of reporting, editing and rigorous fact checking. This is, unapologetically, still a magazine, without pretensions of being a “digital news brand.” This is why the NewYorker.com has a dedicated fact checker, who helps make sure every post the website produces is accurate before writers publish them. It’s a rare position online today. “We built up this brand over 90 years. It’s really important that we hold ourselves up to the standard of the brand,” Thompson said.

Speaking of The Economist, they don't expect advertising to last. 

> “The Economist has taken the view that advertising is nice, and we’ll certainly take money where we can get it, but we’re pretty much expecting it to go away. I have nothing against advertising as a source of revenue as part of the mix, but I’m kind of amazed that people are trying to do that.”

This is a lot like saying that the only way to lose weight is to eat less and exercise more. Completely boring. And really, very hard. 

So hard that most companies will wave their hand at this. There's no way Wired will just waive its paywall and include the results of what I scraped in a post that would genuinely help people, draw more loyal readers to its site, and allow it to charge full-price for a tech site that is genuinely interesting, timely, and full of content whose audience is people instead of SEO robots. That's too simple. And also excruciatingly hard.  

But a digital content property today can't expect to put out mostly junk filler, wrap it in ad tech, and beg for money from the very people it is exploiting as the product. 

And if it does, it shouldn't be surprised that sometimes the product gets tired of it. 

[Hacker News discussion](https://news.ycombinator.com/item?id=11446289)
