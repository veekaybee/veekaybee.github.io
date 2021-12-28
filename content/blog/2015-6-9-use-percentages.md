+++
Title = 'Really easy data concepts that get abused all the time'
date = '2015-06-09T00:00:00Z'

+++

The more we make things easier for the human brain to understand, the easier it is to make data-driven decisions because we don't have to spend time understanding the data. 

Two particular things that are abused a lot are:

+ Not including percentages for comparison
+ Comparing two completely different numbers

Amazon was guilty of this until very recently. So, I was pleasantly surprised when I logged into Amazon this morning. It looks like they've switched from indicating the total number of customers who liked or didn't like something, to percent. 

Here's how it used to look (screenshot not mine)

![Percent](http://netdna.webdesignerdepot.com/uploads/amazon//compare-reviews.jpg)

And here's how it looks now in my browser (screenshot mine):

![New Percent](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/amazon.png)

The difference is small but actually pretty amazing. Humans, especially busy humans who are trying to buy a trash can as quickly as possible,  are not made to compare raw numbers very well, mainly because we are better at recognizing groups of things than [counting them](http://www.quora.com/Why-is-human-brain-bad-at-raw-arithmetic-involving-large-numbers-even-though-its-good-at-tasks-such-as-object-recognition). 

This is why it's always a good idea to include percentages as a point of comparison in presentation format, rather than the raw numbers themselves. Because our brains are doing less work trying to understand the difference between 20 people liking something and 10 people liking it, we can now focus on the real argument at hand: Is the trash can any good? Looks like it is, since 75% of reveiwers gave it 5 stars. 

It's important to have the raw numbers available for reference as well though. Amazon includes that too, by showing how many total reviews there are (556.) This adds insight, because we can see that 75% of over 500 people like the trash can rather than 75% of, say, 10 people, which is not very helpful. 

Another concept of this type of relativity that makes data easier to understand for people is dividing out to get a common denominator. For example, we know that the GDP of the United States is $16.7 trillion dollars. The UK's is $2.6 trillion. That means the UK is doing much worse, right? Not necessarily. If we divide out by the population, we see that GDP per capita (in current USD) is roughly $53k for the US and $41k for the UK. Much closer than the initial large number would have us believe. This way, we can now compare things equally. 

