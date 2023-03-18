---
title: Searching for Indonesia Population
date: "2018-09-09T00:00:00.000Z"
description: "Recently the company I’m working in started using and tinkering with this google sheets like relational database service. After tinkering around with it I’m wondering if I can just scrape data from airtable without the use of API and just the feature of view sharing"
tags: ["Learning", "JavaScript", "Data"]
---

When talking about finding information online, I have quite a confidence in me. But today I learned that confidence is not enough, there is something more important. That is for the information itself to be available online.

The story goes like this,

### Innocent Learning

Today I’ve decided to continue my long delayed learning on [Wes Bos’s JS30](https://javascript30.com/). The content of the exercise today (Day 6) is to create a type ahead for data on population in United States Cities. So as what I usually do, I watch the video until the end, and planning on how I will modify the problem to be a bit different just to have fun.

By the end of the video I have an idea, that is to use my country’s population data instead of using the data provided by the exercise. Soooo Original.And since population data can be said as original, it must be easily available, right?

### Let’s Ask Our Best Friend, Google

**Me **: Google, Can I have Indonesian Population Data?
Google : Sure, here is the [review](http://worldpopulationreview.com/countries/indonesia-population/) for Indonesian Population in 2018 with several pretty infographics
**Me **: well, thank you 
(Now that I think about it, I need each city’s population data to imitate the course)

**Me **: Actually, can I also get Indonesian Cities Population Data?
Google : Easy Peasy,[ here](https://en.wikipedia.org/wiki/List_of_Indonesian_cities_by_population) you go
**Me **: I think I need one that include smaller cities (we call it *kabupaten*, I guess district is the english word for it but my guts says it’s different)
Google : Hmmm, How about the review that I gave you earlier?
**Me** : Well.. I guess It’s a good idea to just use Indonesian Language to search for Indonesian data. Okay Google, give me *“Data populasi kota dan kabupaten Indonesia”
*Google : Let me see..

![well data, but not one I need](https://cdn-images-1.medium.com/max/2000/1*ucps73zIMG1C2KNhcThNPw.png)*well data, but not one I need*

**Me **: (Hmmm.. I expected to get CSV or JSON data, but let me just open the wikipedia first)

![Lots of table](https://cdn-images-1.medium.com/max/3802/1*sDIVt6StDuUIGF4-ClTYBg.gif)*Lots of table*

Yup, these are the data I need, but I couldn’t find any CSV or Excel or any raw data on the page. Wait, it refers to *Badan Pusat Statistik* (BPS, Indonesian Center of Statistics) of course they will have the data, why didn’t I think about it sooner.

(opening BPS Website) Well now, what should I do with this

![Too much information, too little understanding (on my side)](https://cdn-images-1.medium.com/max/3036/1*vaSyOfXbeD5lMqzDXzMBNg.png)*Too much information, too little understanding (on my side)*

(Close BPS Website, Opening Google) 
**Me **: Google, tell me *Data populasi indonesia BPS *please!
Google : Okey-Dokey~ Here is a 700 pages pdf report on Indonesian Population and other things
**Me **: Damn, forget it. Wait, I think I know a website for open data in Indonesia, let’s try it.
(opening data.go.id and typing *populasi *in the search bar)

![All Jakarta (Indonesia’s Capital City)](https://cdn-images-1.medium.com/max/2378/1*NEkMlCcE2bnvN8tCsdfb0w.png)*All Jakarta (Indonesia’s Capital City)*

Okay…

Well, I guess I will just go back into that wikipedia article and clean it up a bit… Thank you google my friend.

### Conclusion

Yep, I’ve gone quite a long way to finish the JS30 exercise. But from this experience I learned how to be both confident and humble. Because overconfidence is not a good personality, and being humble is good.

Joking aside, searching for data that I need in the format that I wanted is actually **hard**. I spend almost one hour to search for the data I wanted, and when I yield to the condition and decide to use the wikipedia article, it took me at least 30 minutes to clean, format, and put the data on gist. Later I found out that BPS offer a service to search for their data (free session) but I need to pay if I want their data (which in this case not an option since I’m cheap).

If you are like me and searching for Indonesian Population data, I’ve put it up on my gist [here](https://gist.github.com/banditelol/6c22a6c99da976f272ec4f24e947400e) as a json file. And in case you are insterested, you can find my version of the assignment [here](http://indo-populasi.surge.sh). And if you know where or how to search for data please reach out to me in the comments below. Thank You.
