---
title: Scraping the Surface of Airtable (Part 1)
date: "2018-09-08T00:00:00.000Z"
description: "Recently the company I’m working in started using and tinkering with this google sheets like relational database service. After tinkering around with it I’m wondering if I can just scrape data from airtable without the use of API and just the feature of view sharing"
featuredImage: './0_sky.jpg'
tags: ["Learning", "Scraping", "Airtable"]
---

Recently the company I’m working in started using and tinkering with this google sheets like relational database service (to be honest I think this description may be misleading, it will do you better to just checkout the product here. After tinkering around with it I’m wondering if I can just scrape data from airtable without the use of API and just the feature of view sharing.

But, before I continue, **Disclaimer :**

1. **this post is not sponsored by airtable or me promoting it. I’m just another user who is excited with certain product, and interested in make the most out of it.**

2. **It is not a Tutorial, it is a journey of how I explore web scraping. So it may not be a good reference on how to do things.**

## Introduction

First let me introduce you to Airtable. In Airtable you can make a *base *which analogues to *worksheet file* in Google Sheet. And fortunately Airtable offers lots of template for us to start with.

![A lot of template for your personal use to professional project management at work](https://cdn-images-1.medium.com/max/3796/1*xM6U652388rN1gjZCxwZiQ.png)*A lot of template for your personal use to professional project management at work*

For now let us pick a simple book tracker just for example.

![](https://cdn-images-1.medium.com/max/3802/1*8LwXykHIhe23_z8MIeCy2A.gif)

After you open the template on your workspace, you will be greeted with the view inside your *base *which consists of two *tables* in this case (two tabs). i.e. table for Books and for Authors. In each table there are *records *(row) and for each records you can have one *primary field* (the leftmost column) and multiple *fields* (the rest of the column)

For example in our Books Table there are 5 records with primary field of book names, and each has 6 fields (excluding the primary field).Okay now We’ve got some basic covered, I believe it is better for you to try it out yourself since this post is not for introducing how to work with airtable, but how I want to scrape data from it and my progress on it.

## What I Want

Actually what I want to do is pretty simple. We have multiple team, and each has a base with the same format and template. And every now and then I need to keep track of what’s happened in just one table of each base that every team has. And also I need to make a summary from them. And to be honest checking each of them is such a pain.

So I think of something, how about I just aggregate the data from each base? There must be an [API](https://airtable.com/api) for it.

Well, guess what? yes there’s an API for it and there are library for that API whether in [Javascript ](https://github.com/airtable/airtable.js/)or for[ Python](https://github.com/nicocanali/airtable-python). But if I want to use the API I need to be invited to the base and from the number of team that I monitor it may use up all my free plan quota (CMIIW, haven’t really try it, to be honest I just need an excuse to try web scraping). So since I am a cheapskate and I want to learn scraping anyway, I’ll just make it an exploration and a chance for me to learn.

Btw I get my motivation from a google sheet extension called [airtable importer](https://chrome.google.com/webstore/detail/airtable-importer-by-rail/kngidnifbonbaembhpnjlpeefhmjpegb), since it has been done before, I should be able to do it right? (well at least I hope so)

## Let’s Start!

### 0. My Plan

Well in short I want to be able to copy the shared view link for a table and have a program to extract the data I want and make an aggregate of the data in a spreadsheet so that I won’t be bothered by the free plan limitation of airtable.

![This is what I want to achieve](https://cdn-images-1.medium.com/max/2000/1*A0v4dy-02yBQghZCDUIOZA.png)*This is what I want to achieve*

It should be easy, right?

### 1. Searching the data

This is the view of what I want to scrape, you also can access it [here](https://airtable.com/shrCTg33zQeyuv2Rs)

![The shared view in chrome](https://cdn-images-1.medium.com/max/3838/1*z5iM-nOmLUnQmp8gNeybUg.png)*The shared view in chrome*

Since it is on the web, and I could access it with browser, I could just fetch the data from the HTML right?

Turns out I am to naive for this, using chrome dev tools, I found out that:

![the complex structure of HTML](https://cdn-images-1.medium.com/max/3840/1*1L5h3ILGyg3f4b_V0LV6EA.png)*the complex structure of HTML*

The data exists! That means the data is there in the HTML. So I can scrape the data directly following the structure in the HTML. But After giving it more thoughts I assume that the data must be sent from the back-end and there’s a javascript there that select the div and populate it with table data. Maybe I could get the data with better format if I can get the data before it populate the table.

Here I’ve got two choices:

1. Scrape the data from HTML

1. Try to get the data from HTTP request

For now let’s try the second option first, since it sounds cooler

### 2. Finding the Request

After looking closely on how the page load, I guess I could find out how the data is loaded into the table. So I used Network tab in Chrome DevTools and… There!

![There’s time when the content suddenly get populated](https://cdn-images-1.medium.com/max/3802/1*jNHQIOh8oknVM6Q0LXKQvg.gif)*There’s time when the content suddenly get populated*

If you look closely there’s this request that’s happened right before the content is loaded.

![THE DATA](https://cdn-images-1.medium.com/max/3802/1*iW5kYej1SFYKyO8SmcRB_g.gif)*THE DATA*

And there it is the data I need!

### 3. Pulling the Request ?

So how should I get it, I could just copy pasting it to postman and it will works right?

![](https://cdn-images-1.medium.com/max/3802/1*h_xQc26LZBOAM5wq9RTzow.gif)

Turns out no. After reloading the page several times I found out that the RequestId part of the URL is changing each time. After a quick search on the matter I found out that RequestID is a truly unique code generated by the service (CMIIW). And for now I hit a wall here. Sooo I will step back and try the second method, by scraping the html.

Since the post got quite long in the progress, I will see you in part 2 on how I will try to scrape the data from HTML.
