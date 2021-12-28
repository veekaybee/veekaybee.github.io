+++
date = '2018-02-19T00:00:00Z'
title = 'Building a Twitter art bot with Python, AWS, and socialist realism art'

+++

<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@vboykis">
<meta name="twitter:creator" content="@vboykis">
<meta name="twitter:title" content="Building a Twitter art bot with Python, AWS, and Soviet art ">
<meta name="twitter:description" content="I built a Twitter bot that tweets socialist realism paintings every 6 hours using Python, AWS Lambdas, and Travis CI. ">
<meta name="twitter:image" content="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/weaving.jpg">

<blockquote class="twitter-tweet" data-lang="en"><p lang="ht" dir="ltr">&quot;Young Naturalists&quot;<br>Sergiy Grigoriev, 1948 <a href="https://t.co/8KdwFS6PjU">pic.twitter.com/8KdwFS6PjU</a></p>&mdash; SovietArtBot (@SovietArtBot) <a href="https://twitter.com/SovietArtBot/status/964096054089134081?ref_src=twsrc%5Etfw">February 15, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


**TLDR:** I built a Twitter bot that tweets paintings from the WikiArt socialist realism category every 6 hours using Python and AWS Lambdas. 

The post outlines why I decided to do that, architecture decisions I made, technical details on how the bot works, and my next steps for the bot.  

<a class="twitter-follow-button"
  href="https://twitter.com/SovietArtBot"
  data-size="large">
Follow @SovietArtBot Here</a>

Check out its [website and code here](https://veekaybee.github.io/soviet-art-bot/). 

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  

- [Why build an art bot?](#why-build-an-art-bot)
  - [Technical Goals](#technical-goals)
  - [Personal Goals](#personal-goals)
  - [Why Socialist Realism](#why-socialist-realism)
- [Breaking a Project into Chunks](#breaking-a-project-into-chunks)
- [Requirements and Design: High-Level Bot Architecture](#requirements-and-design-high-level-bot-architecture)
- [Development: Pulling Paintings from WikiArt](#development-pulling-paintings-from-wikiart)
- [Development: Processing Paintings and Metadata Locally](#development-processing-paintings-and-metadata-locally)
- [Development: Using S3 and Lambdas](#development-using-s3-and-lambdas)
  - [Development: Scheduling the Lambda](#development-scheduling-the-lambda)
- [Deployment: Bot Tweets!](#deployment-bot-tweets)
- [Where to Next?](#where-to-next)
  - [Testing and Maintenance](#testing-and-maintenance)
- [Conclusion](#conclusion)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

<a name="why-build-an-art-bot"></a> 
# Why build an art bot? 

Often when you're starting out as a data scientist or developer, people will give you the well-intentioned advice of "just picking a project and doing it" as a way of learning the skills you need. 

That advice can be hard and vague, particularly when you don't have a lot of experience to draw from to figure out what's even feasible given how much you know, and how that whole process should work.

By writing out my process in detail, I'm hoping it helps more people understand:

1) The steps of a software project from beginning to end. 

2) The process of putting out a mininum viable project that's "good enough" and iterating over your existing code to add features.

3) Picking a project that you're going to enjoy working on.

4) The joy of socialist realism art.

<a name="technical-goals"></a> 
## Technical Goals 

I've been doing more software development as part of my data science workflows lately, and I've found that: 

1) I really enjoy doing both the analytical and development pieces of a data science project.
2) The more development skills a data scientist is familiar with, the more valuable they are because it ultimately means they can prototype production workflows, and push their models into production quicker than having to wait for a data engineer.  

A goal I've had recently is being able to take a full software development project from end-to-end, focusing on understanding modern production best practices, particularly in the cloud. 

![high-level](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/squadgoals.png)

<a name="personal-goals"></a> 
## Personal Goals 

But, a project that's just about "cloud architecture delivery" is really boring. In fact, I fell asleep just reading that last sentence. When I do a project, it has to have an interesting, concrete goal.

To that end, I've been extremely interested in Twitter as a development platform. I wrote recently that one of the most important ways we can fix the internet is to [get off Twitter. ](http://blog.vickiboykis.com/2016/11/20/fix-the-internet/)

Easier said than done, because Twitter is still one of my favorite places on the internet. It's where I get most of my news, where I find out about new blog posts, engage in [discussions about data science](https://twitter.com/vboykis/status/963098810695258117), and a place where [I've made a lot of friends](https://twitter.com/vboykis/status/959493939555422208) that I've met in real life. 

But, Twitter is extremely noisy, lately to the point of being toxic. There are systemic ways that Twitter can take care of this problem, but I decided to try to tackle this problem this on my own by starting [#devart](https://twitter.com/search?q=%23devart&src=typd), a hashtag where people post classical works of art with their own tech-related captions to break up stressful content.

There's something extremely catharctic about being able to state a problem in technology well enough to ascribe a visual metaphor to it, then sharing it with other people who also appreciate that visual metaphor and find it funny and relatable. 

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">&quot;Waiting for the build to finish&quot;<br>Albert Anker, 1867 <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/3tAO3idZq1">pic.twitter.com/3tAO3idZq1</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/946418801293348864?ref_src=twsrc%5Etfw">December 28, 2017</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">Debugging a memory leak. (Vincent van Gogh, 1890) <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/A5eYvK1Zmq">pic.twitter.com/A5eYvK1Zmq</a></p>&mdash; Miria Grunick (@MiriaGrunick) <a href="https://twitter.com/MiriaGrunick/status/939390429551788032?ref_src=twsrc%5Etfw">December 9, 2017</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">&quot;Migration plan from MySQL to Mongo&quot;<br>Alex Colville, 1954 <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/ieCzv7Hfh8">pic.twitter.com/ieCzv7Hfh8</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/940785795769815040?ref_src=twsrc%5Etfw">December 13, 2017</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">&quot;Another day, another AGILE Sprint&quot;<br>~Peter Blume, circa 1944-8<a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/GaiueyZniS">pic.twitter.com/GaiueyZniS</a></p>&mdash; Trevor Grant (@rawkintrevo) <a href="https://twitter.com/rawkintrevo/status/943240523044835330?ref_src=twsrc%5Etfw">December 19, 2017</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">&quot;Machine learning engineers, data scientists, data analysts, research engineers, and statisticians hold forth on what each of their actual titles means in terms of responsibilities. &quot;<br>Raphael, 1510 <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/7uX0ompuAo">pic.twitter.com/7uX0ompuAo</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/952381862298771457?ref_src=twsrc%5Etfw">January 14, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">another Friday night deploy <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/8dSgMbZqwj">pic.twitter.com/8dSgMbZqwj</a></p>&mdash; Dmitri Sotnikov ⚛ (@yogthos) <a href="https://twitter.com/yogthos/status/951452540238954496?ref_src=twsrc%5Etfw">January 11, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">VC runs into the founder of a blockchain AI startup at SoulCycle Palo Alto, 2017 <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/Kgnj4IcOlK">https://t.co/Kgnj4IcOlK</a></p>&mdash; Alex Companioni (@achompas) <a href="https://twitter.com/achompas/status/962080959305846785?ref_src=twsrc%5Etfw">February 9, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


<blockquote class="twitter-tweet" data-lang="en"><p lang="und" dir="ltr">C# Developer Contemplates Switching to Node.js - Laszlo Mednyanszky, 1898 <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/FwyQxmXMt5">pic.twitter.com/FwyQxmXMt5</a></p>&mdash; PoliticalMath (@politicalmath) <a href="https://twitter.com/politicalmath/status/935209079131086848?ref_src=twsrc%5Etfw">November 27, 2017</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

And, sometimes you just want to break up the angry monotony of text with art that moves you. Turns out I'm not the only one. 

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">If you don&#39;t follow <a href="https://twitter.com/vboykis?ref_src=twsrc%5Etfw">@vboykis</a>, she does a wonderful <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> series where she tweets out art w/ hilarious tech-y titles. Chris would LOVE it.</p>&mdash; PoliticalMath (@politicalmath) <a href="https://twitter.com/politicalmath/status/935207210564567040?ref_src=twsrc%5Etfw">November 27, 2017</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">Vicki’s <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> project is my new favorite twitter thing. <a href="https://t.co/3MwTNNKJeB">https://t.co/3MwTNNKJeB</a></p>&mdash; Rian van der Merwe (@RianVDM) <a href="https://twitter.com/RianVDM/status/961987941517049857?ref_src=twsrc%5Etfw">February 9, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

As I posted more #devart, I realized that I enjoyed looking at the source art almost as much as figuring out a caption, and that I enjoyed accounts like [Archillect](https://twitter.com/archillect?lang=en), [Rabih Almeddine's](https://twitter.com/rabihalameddine?lang=en), and [Soviet Visuals](https://twitter.com/sovietvisuals?lang=en), who all tweet a lot of beautiful visual content with at least some level of explanation. 

I decided I wanted to build a bot that tweets out paintings. Particularly, I was interested in socialist realism artworks. 

<a name="why-socialist-realism"></a> 
##  Why Socialist Realism

Socialist realism is an artform that was developed after the Russian Revolution. As the Russian monarchy fell, social boundaries dissovled,and people began experimenting with all kinds of new art forms, including futurism and [abstractionism.](http://www.wassilykandinsky.net/)  I've previously written [about this shift here.](http://blog.vickiboykis.com/2015/06/reddit-was-amazing/) 

As the Bolsheviks consolidated power, they established [Narkompros](https://en.wikipedia.org/wiki/People%27s_Commissariat_for_Education), a body to control the education and cultrual values of what they deemend was acceptable under the new regime, and the government laid out the new criteria for what was accetable Soviet art.

Socialist realism as a genre had four explicit criteria, developed by the highest government officials, including Stalin himself. It was to be: 

    + Proletarian: art relevant to the workers and understandable to them.
    + Typical: scenes of everyday life of the people.
    + Realistic: in the representational sense.
    + Partisan: supportive of the aims of the State and the Party.`

In looking at socialist realism art, it's obvious that the underlying goal is to promote communism. But, just because the works are blatant propaganda doesn't discount what I love about the genre, which is that it is indeed representative of what real people do in real life.  

<blockquote class="twitter-tweet" data-lang="en"><p lang="lt" dir="ltr">&quot;PagerDuty&quot;<br>Vladimir Kutilin <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/onfI7bOxJL">pic.twitter.com/onfI7bOxJL</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/963763026829676544?ref_src=twsrc%5Etfw">February 14, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

These are people working, sleeping, laughing, frowning, arguing, and showing real emotion we don't often see in art. They are relatable and humane, and reflect our humanity back to us. What I also strongly love about this genre of art is that women are depicted doing things other than sitting still to meet the artist's gaze. 

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">&quot;Young, idealistic data scientists harvesting their first models for pickling&quot;<br>Tetyana Yablonska, 1966 <a href="https://t.co/iSlWhTEeED">pic.twitter.com/iSlWhTEeED</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/916092943575986176?ref_src=twsrc%5Etfw">October 6, 2017</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

So, what I decided is that I'd make a Twitter bot that tweets out one socialist realism work every couple of hours. 

Here's the final result: 

<blockquote class="twitter-tweet" data-lang="en"><p lang="ht" dir="ltr">&quot;Young Naturalists&quot;<br>Sergiy Grigoriev, 1948 <a href="https://t.co/8KdwFS6PjU">pic.twitter.com/8KdwFS6PjU</a></p>&mdash; SovietArtBot (@SovietArtBot) <a href="https://twitter.com/SovietArtBot/status/964096054089134081?ref_src=twsrc%5Etfw">February 15, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

There are several steps in traditional software development: 

1. Requirements
2. Design
3. Development
4. Testing
5. Deployment
6. Maintenance

<a name="breaking-a-project-into-chunks"></a> 
# Breaking a Project into Chunks

This is a LOT to take in. When I first started, I made a list of everything that needed to be done: setting up AWS credentials, roles, and permissions, version control, writing the actual code, learning how to download images with requests, how to make the bot tweet on a schedule, and more. 

When you look at it from the top-down, it's overwhelming. But in ["Bird by Bird,"](https://www.brainpickings.org/2013/11/22/bird-by-bird-anne-lamott/) one of my absolute favorite books that's about the writing processs (but really about any creative process) Anne Lamott writes, 

> Thirty years ago my older brother, who was ten years old at the time, was trying to get a report on birds written that he’d had three months to write, which was due the next day. We were out at our family cabin in Bolinas, and he was at the kitchen table close to tears, surrounded by binder paper and pencils and unopened books on birds, immobilized by the hugeness of the task ahead. Then my father sat down beside him, put his arm around my brother’s shoulder, and said, “Bird by bird, buddy. Just take it bird by bird.”

And that's how I view software development, too. One thing at a time, until you finish that, and then move on to the next piece. So, with that in mind, I decided I'd use a mix of the steps above from the traditional waterfall approach and mix them with the agile concept of making a lot of small, quick cycles of those steps to get closer to the end result. 

<a name="requirements-and-design-high-level-bot-architecture"></a> 
# Requirements and Design: High-Level Bot Architecture


I started building the app by  working backwards from what my requirements:

 a bot on Twitter, pulling painting images and metadata from some kind of database, on a timed schedule, either [cron](https://help.ubuntu.com/community/CronHowto) or something similar. 

 This helped me figure out the design. Since I would be posting to Twitter as my last step, it made sense to have the data already some place in the cloud. I also knew I'd eventually want to incorporate AWS because I didn't want the code and data to be dependent on my local machine being on. 

I knew that I'd also need version control and continuous integration to make sure the bot was stable both on my local machine as I was developing it, and on AWS as I pushed my code through, and so I didn't have to manually put to code in the AWS console. 

Finally, I knew I'd be using Python, because [I like Python](http://veekaybee.github.io/2017/09/26/python-packaging/), and also because it has good hooks into Twitter through the Twython API (thanks to Timo for pointing me to [Twython](https://twitter.com/tkoola/status/963480840574590978) over Tweepy, which is deprecated) and AWS through the [Boto library](https://twitter.com/vboykis/status/963224376056434688). 
I'd start by getting the paintings and metadata about the paintings from a website that had a lot of good socialist realism paintings not bound by copyright. Then, I'd do something to those paintings to get both the name, the painter, and title so I could tweet all of that out. Then, I'd do the rest of the work in AWS. 

So my high-level flow went something like this: 

![high-level](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/high-level-flow.png)

Eventually, I'd refactor out the dependency on my local machine entirely and push everything to S3, but I didn't want to spend any money in AWS before I figured out what kind of metadata the JSON returned. 

Beyond that, I didn't have a specific idea of the tools I'd need, and made design and architecture choices as my intermediate goals became clearer to me. 

<a name="development-pulling-paintings-from-wikiart"></a> 
# Development: Pulling Paintings from WikiArt

Now, the development work began. 

[WikiArt](https://www.wikiart.org/) has an amazing, well-catalogued collection of artworks in every genre you can think of. It's so well-done that [some researchers use](http://www.lamsade.dauphine.fr/~bnegrevergne/webpage/documents/2017_rasta.pdf) the catalog [for their papers](http://cs231n.stanford.edu/reports/2017/pdfs/406.pdf) on [deep learning](https://arxiv.org/pdf/1605.09612.pdf), as well.  

Some days, I go just to browse what's new and get lost in some art. (Please [donate](https://www.wikiart.org/en/donate) to them if you enjoy them.)

WikiArt also has two aspects that were important to the project: 

1) They have [an explicit category](https://www.wikiart.org/en/paintings-by-style/socialist-realism?select=featured) for socialist realism art with a good number of works. 500 works in the socialist realism perspective, which was not a large amount (if I wanted to tweet more than one image a day), but good enough to start with. 
 
2) Every work has an image, title, artist, and year,which would be important for properly crediting it on Twitter. 

My first step was to see if there was a way to acces the site through an API, the most common way to pull any kind of content from websites programmatically these days. The problem with WikiArt is that it technically doesn't have a readily-available public API,so people have resorted to [really creative ways](https://github.com/lucasdavid/wikiart) of scraping the site. 

But, I really, really didn't want to scrape, especially because the site has infinite scroll Javascript elements, which are annoying to pick up in [BeautifulSoup](https://www.crummy.com/software/BeautifulSoup/), the tool most people use for scraping in Python.

So I did some sleuthing, and found that [WikiArt does have an API](https://docs.google.com/document/d/1Vxi5lQnMCA21dvNm_7JVd6nQkDS3whV3YjRjbwWPfQU/edit#!), even if it's not official and, at this point, somewhat out of date. 

It had some important information on API rate limits, which tells us how often you can access the API without the site getting angry and kicking out out: 

> API calls: 10 requests per 2.5 seconds

> Images downloading: 20 requests per second

and,even more importantly, on how to access a specific category through JSON-based [query parameters](https://en.wikipedia.org/wiki/Query_string). The documentation they had, though, was mostly at the artist level:

```http://www.wikiart.org/en/salvador-dali/by-style/Neoclassicism&json=2```

so I had to do some trial and error to figure out the correct link I wanted, which was: 

```
https://www.wikiart.org/en/paintings-by-style/socialist-realism?json=2&page=1
```

And with that, I was ready to pull the data.

I started by using the Python [Requests library](http://docs.python-requests.org/en/master/) to connect to the site and pull two things: 

1) A JSON file that has all the metadata 
2) All of the actual paintings as `png/jpg/jpeg` files 

<a name="development-processing-paintings-and-metadata-locally"></a> 
# Development: Processing Paintings and Metadata Locally

The JSON I got back looked like this: 

```json
{
ArtistsHtml: null,
CanLoadMoreArtists: false,
Paintings: [],
Artists: null,
AllArtistsCount: 0,
PaintingsHtml: null,
PaintingsHtmlBeta: null,
AllPaintingsCount: 512,
PageSize: 60,
TimeLog: null
}
```

Within the paintings array, each painting looked like this:

```json
{
	"id": "577271cfedc2cb3880c2de61",
	"title": "Winter in Kursk",
	"year": "1916",
	"width": 634,
	"height": 750,
	"artistName": "Aleksandr Deyneka",
	"image": "https://use2-uploads8.wikiart.org/images/aleksandr-deyneka/winter-in-kursk-1916.jpg",
	"map": "0123**67*",
	"paintingUrl": "/en/aleksandr-deyneka/winter-in-kursk-1916",
	"artistUrl": "/en/aleksandr-deyneka",
	"albums": null,
	"flags": 2,
	"images": null
}
```

I also downloaded all the image files by returning `response.raw` from the JSON and using the `shutil.copyfileobj` method. 

I decided not to do anymore processing locally since my goal was to eventually move everything to the cloud anyway,  but I now had the files available to me for testing so that I didn't need to hit WikiArt and overload the website anymore. 

I then uploaded both the JSON and the image files to the same S3 bucket with the [boto client](https://github.com/boto/boto3), which lets you write : 

```python
def upload_images_to_s3(directory):
    """
    Upload images to S3 bucket if they end with png or jpg
    :param directory:
    :return: null
    """

    for f in directory.iterdir():
        if str(f).endswith(('.png', '.jpg', '.jpeg')):
            full_file_path = str(f.parent) + "/" + str(f.name)
            file_name = str(f.name)
            s3_client.upload_file(full_file_path, settings.BASE_BUCKET, file_name)
            print(f,"put")
```

As an aside, the `.iterdir()` method here is from the pretty great `pathlib` library, new to Python 3, which handles file operations better than os. Check out more about it [here.](https://github.com/arogozhnikov/python3_with_pleasure)  

<a name="development-using-s3-and-lambdas"></a> 
# Development: Using S3 and Lambdas

Now that I had my files in S3, I needed some way for Twitter to read them. To do that at a regular time interval,  I decided on using an AWS Lambda function (not to be confused with Python lambda functions, a completely different animal.) Because I was already familiar with Lambdas and their capabilities  - [see my previous post on AWS](http://veekaybee.github.io/2018/01/28/working-with-aws/) - , they were a tool I could use without a lot of ramp-up time (a key component of architectural decisions.)

Lambdas are snippets of code that you can run without needing to know anything about the machine that runs them. They're triggered by other events firing in the AWS ecosystem. Or, they can be run on a [cron-like schedule](http://www.tothenew.com/blog/schedule-lambda-on-cron-expression-triggers/), which was perfect for what I wanted to do. This was exactly what I needed, since I needed to schedule the bot to post at an interval. 

Lambdas look [like this](https://docs.aws.amazon.com/lambda/latest/dg/python-programming-model-handler-types.html) in Python: 

```
def handler_name(event, context): 
    ...
    return some_value
```

The `event` is what you decide to do to trigger the function and the context [sets up all the runtime information](https://docs.aws.amazon.com/lambda/latest/dg/python-context-object.html) needed to interact with AWS and run the function.

Because I wanted my bot to tweet both the artwork and some context around it, I'd need a way to tweet both the picture and the metadata, by matching the picture with the metadata. 

To do this, I'd need to create [key-value pairs](https://en.wikipedia.org/wiki/Attribute%E2%80%93value_pair), a common programming data model,  where the key was the filename part of the `image` attribute, and the value was the `title`, `year`, and `artistName`, so that I could match the two, like this: 

![high-level](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/file_match.png)

So, all in all,  I wanted my lambda function to do several things. All of that code I wrote for that section is [here.](https://github.com/veekaybee/soviet-art-bot/blob/master/soviet_art_bot/lambda_function.py)  

1) Open the S3 bucket object and inspect the contents of the metadata file 

Opening an S3 bucket within a lambda usually looks something like this: 

```
def handler(event, context):
    for record in event['Records']:
        bucket = record['s3']['bucket']['name']
        key = record['s3']['object']['key'] 
        download_path = '/tmp/{}{}'.format(uuid.uuid4(), key)
        s3_client.download_file(bucket, key, download_path)
```

where the event is the JSON file that gets passed in from Lambda that signifies that a trigger has occurred. Since our trigger is a timed event, our [JSON file](https://github.com/veekaybee/soviet-art-bot/blob/master/soviet_art_bot/event.json) doesn't have any information about that specific event and bucket, and we can exclude the event, in order to create a function that normally opens a given bucket and key. 

```python
try:
        data = s3.get_object(Bucket=bucket_name, Key=metadata)
        json_data = json.loads(data['Body'].read().decode('utf-8'))
    except Exception as e:
        print(e)
        raise e
```


2) Pull out the metadata and pull it into a dictionary with the filename as the key and the metadata as the value. We can pull it into a `defaultdict`, because those are ordered by default (all dictionaries [will be orded](https://mail.python.org/pipermail/python-dev/2016-September/146327.html) as of 3.6, but we're still playing it safe here.)

```python
indexed_json = defaultdict()

    for value in json_data:
        artist = value['artistName']
        title = value['title']
        year = value['year']
        values = [artist, title, year]

        # return only image name at end of URL
        find_index = value['image'].rfind('/')
        img_suffix = value['image'][find_index + 1:]
        img_link = img_suffix

        try:
            indexed_json[img_link].append(values)
        except KeyError:
            indexed_json[img_link] = (values)
```

(By the way, a neat Python string utility that I didn't know before which really helped with the filename parsing was (rsplit)
[http://python-reference.readthedocs.io/en/latest/docs/str/rsplit.html]. )


3) Pick a random filename to tweet  (`single_image_metadata = random.choice(list(indexed_json.items()))`)

4) Tweet the image and associated metadata

There are a couple of Python libraries in use for Twitter. I initially started using Tweepy, but much to my sadness, I found out [it was no longer being maintained.](https://github.com/tweepy/tweepy/issues/803.) (Thanks for the tip, [Timo.](https://twitter.com/tkoola/status/963480840574590978) )

So I switched to [Twython](https://twython.readthedocs.io/en/latest/), which is a tad more convoluted, but is up-to-date. 

The final piece of code that actually ended up sending out the tweet is here: 

```
    twitter = Twython(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_SECRET)

    try:

        tmp_dir = tempfile.gettempdir()
        #clears out lambda dir from previous attempt, in case testing lambdas keeps previous lambda state
        call('rm -rf /tmp/*', shell=True) 
        path = os.path.join(tmp_dir, url)
        print(path)

        s3_resource.Bucket(bucket_name).download_file(url, path)
        print("file moved to /tmp")
        print(os.listdir(tmp_dir))

        with open(path, 'rb') as img:
            print("Path", path)
            twit_resp = twitter.upload_media(media=img)
            twitter.update_status(status="\"%s\"\n%s, %s" % (title, painter, year), media_ids=twit_resp['media_id'])

    except TwythonError as e:
        print(e)
```

What this does is take advantage of a Lambda's temp space: 

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">TIL that AWS Lambda Functions have miniature file systems that you can use as temporary storage (<a href="https://t.co/egCKwu6GJB">https://t.co/egCKwu6GJB</a>).</p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/948661197011914757?ref_src=twsrc%5Etfw">January 3, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

Pulls the file from S3 into the Lambda's `/tmp/` folder, and matches it by filename with the metadata, which at this point is in key-value format. 

The `twitter.upload_media` method uploads the image and gets back a media id that is then passed into the `update_status` method with the `twit_resp['media_id']`. 

And that's it. The image and text are posted. 

<a name="development-scheduling-the-lambda"></a> 
## Development: Scheduling the Lambda

The second part was configuring the function. to run on a schedule. Lambdas can be triggered by two things: 

1. An event occurring
2. A timed schedule. 

Events [can be anything](https://docs.aws.amazon.com/lambda/latest/dg/invoking-lambda-function.html) from a file landing in an S3 bucket, to polling a Kinesis stream.  

Scheduled events can be written either in cron, or at a fixed-rate. I started out writing [cron rules](https://docs.aws.amazon.com/AmazonCloudWatch/latest/events/ScheduledEvents.html), but since my bot didn't have any specific requirements, only that it needed to post every six hours, the fixed rate turned out to be enough for what I needed: 

![high-level](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/cron-rule.png)

Finally, I needed to package the lambda for distribution. Lambdas run on Linux machines which don't have a lot of Python libraries pre-installed (other than boto3, the Amazon Python client library I used previously that connects the Lambda to other parts of the AWS ecosystem, and json. )

In my script, I have a lot of library imports. Of these, Twython is an external library that needs to be [packaged with the lambda and uploaded.](https://docs.aws.amazon.com/lambda/latest/dg/lambda-python-how-to-create-deployment-package.html) 

```
from twython import Twython, TwythonError
```
<a name="deployment-bot-tweets"></a> 
# Deployment: Bot Tweets!

So I packged the Lambda based on those instructions, manually the first time, by uploading a zip file to the Lambda console.  

And, that's it! My two one-off scripts were ready, and my bot was up and running. 

![high-level](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/lambda.png)

![high-level](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/cronedjob.png)

And here's the final flow I ended up with: 
![architecture](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/architecture.png)

<a name="where-to-next"></a> 
# Where to Next? 

There's a lot I still want to get to with Soviet Art Bot. 

The most important first step is tweaking the code so that no painting repeats more than once a week. That seems like the right amount of time for Twitter followers to not get annoyed.

In parallel, I want to focus on testing and maintenance. 

<a name="testing-and-maintenance"></a> 
## Testing and Maintenance

The first time I worked through the entire flow, I started by working in a local Python project I had started in PyCharm and had version-controlled on [GitHub](https://github.com/veekaybee/soviet-art-bot). 

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">Me, trying to explain the cases when I use PyCharm, when I use Sublime Text, and when I use Jupyter Notebooks for development. <a href="https://t.co/CEC0WlymlC">pic.twitter.com/CEC0WlymlC</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/953815826393595904?ref_src=twsrc%5Etfw">January 18, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

So, when I made changes to any part of the process, my execution flow would be: 

1. Run Wikiart download functionality locally 
2. Test the [lambda "locally"](https://medium.com/@bezdelev/how-to-test-a-python-aws-lambda-function-locally-with-pycharm-run-configurations-6de8efc4b206) with `python-lambda-local` 
3. Zip up the lambda and upload to Lambda
4. Make mistakes in the Lambda code
5. Zip up the lambda and run again. 

This was not really an ideal workflow for me, because I didn't want to have to manually re-uploading the lambda every time, so I decided to use Travis CI, which [integrates with GitHub really well.](https://gizmodo.com/the-mess-at-meetup-1822243738). The problem is that there's a lot of setup involved: virtualenvs, syncing to AWS credentials, setting up IAM roles and profiles that allow Travis to access the lambda, setting up a test Twitter and AWS environment to test travis integration, and more. 

For now, the bot is working in production, and while it works, I'm going to continue to automate more and more parts of deployment in my [dev branch](https://github.com/veekaybee/soviet-art-bot/tree/dev). ([This post](https://joarleymoraes.com/hassle-free-python-lambda-deployment/) was particularly helpful in zipping up a lambda, and my [deploy script is here.](https://github.com/veekaybee/soviet-art-bot/blob/dev/lambda_deploy.sh)  

After these two are complete, I want to: 

1) Refactor lambda code to take advantage of `pathlib` instead of OS so my code is standardized (should be a pretty small change)

2) Source more paintings. WikiArt is fantastic, but has only 500ish paintngs available in the socialist realism category. I’d like to find more sources with high-quality metadata and a significant collection of artworks. Then, I'd like to 

3) Create a front-end where anyone can upload a work of socialist realism for the bot to tweet out. This would probably be easier than customizing a scraper and would allow me to crowdsource data. As part of this process, I'd need a way to screen content before it got to my final S3 bucket. 

Which leads to: 


4) Go through current collection and make sure all artwork is relevant and SWF. See if there's a way I can do that programmatically. 

And: 

5) Machine learning and deep learning potential possibilities: Look for a classifier to filter out artworks with nudity/questionable content and figure out how to decide what "questionable" means. Potentially with [AWS Rekognition](https://aws.amazon.com/about-aws/whats-new/2017/04/detect-explicit-or-suggestive-adult-using-amazon-rekognition/), or building my own CNN. 

Other machine learning opportunities: 
+ Mash with #devart to see if the bot can create fun headlines for paintings based on painting content

+ Extract colors from artworks by genre and see how they differ between genres and decades

<a name="conclusion"></a> 
# Conclusion

Software development can be a long, exhausting process with a lot of moving parts and decision-making involved, but it becomes much easier and more interesting if you you break up a project into byte-sized chunks that you can continuously work on to stop yourself from getting overwhelemed with the entire task at hand. The other part, of course, is that it has to be fun and interesting for you so that you make it through all of the craziness with a fun, finished product at the end. 
