+++
date = '2016-07-07T00:00:00Z'
title = "Why doesn't MySQL have window functions?"

+++

![image](https://cdn.meme.am/instances/500x/52119287.jpg)

*Updated with comments from MySQL at end of post. *

I've worked with a lot of the different flavors of SQL, including Oracle, SQL Server, Postgres, and SQLite, so I thought I knew all nuances of different SQL syntaxes. But I've recently been working with a project that's using a  LAMP stack, so I've gotten a lot more understanding of the MySQL's caveats.  

I'm working on some data manipulation that requires window functions in MySQL, and I learned the hard way that [MySQL doesn't support them](http://www.xaprb.com/blog/2013/09/10/features-id-like-in-mysql-windowing-functions/).

Window functions are essential to most types of data manipulation because they allow for calculation across similar groups of rows without having to actually group the rows together and meld the data. 

This kind of aggregation comes up all the time in business analysis and data science, for example, if you're trying to sum the behavior of a single individual in a table that contains millions of them, like if you're trying to analyze pageview behavior,  or to create running totals or cumulative metrics over groups of rows.  Here's a [lot more](https://www.periscopedata.com/blog/window-functions-by-example.html) you can do with them. 

Let's say you want to sum total pageviews for a given user over the span of all of their activity and compare that to how many pageviews they have per minute. 

[Window functions](https://www.postgresql.org/docs/9.1/static/tutorial-window.html) to the rescue!

| user_name | pageview | time  |
|-----------|----------|-------|
| PiedPiper | 1        | 17:01 |
| PiedPiper | 1        | 17:01 |
| PiedPiper | 1        | 17:04 |
| PiedPiper | 1        | 17:04 |
| PiedPiper | 1        | 17:04 |
| PiedPiper | 1        | 17:04 |
| PiedPiper | 1        | 17:05 |
| Dinesh    | 1        | 17:01 |
| Dinesh    | 1        | 17:01 |
| Dinesh    | 1        | 17:01 |
| Dinesh    | 1        | 17:01 |
| Dinesh    | 1        | 17:01 |
| Dinesh    | 1        | 17:07 |
| Dinesh    | 1        | 17:07 |
| Dinesh    | 1        | 17:07 |
| Guilfoyle | 1        | 17:02 |
| Jared     | 1        | 17:01 |
| Jared     | 1        | 17:03 |


		SELECT user_name,
       			time,
       			SUM(pageview) OVER
         (PARTITION BY user_name) AS running_total_pageviews, SUM(pageview) OVER
         (PARTITION BY user_name order by time) AS pageviews_per_min
         FROM piedpiper.pageviews
         
| user_name | pageview | time  | pageviews_per_minute | runing_total_pageviews |
|-----------|----------|-------|----------------------|------------------------|
| PiedPiper | 1        | 17:01 | 2                    | 7                      |
| PiedPiper | 1        | 17:01 | 2                    | 7                      |
| PiedPiper | 1        | 17:04 | 4                    | 7                      |
| PiedPiper | 1        | 17:04 | 4                    | 7                      |
| PiedPiper | 1        | 17:04 | 4                    | 7                      |
| PiedPiper | 1        | 17:04 | 4                    | 7                      |
| PiedPiper | 1        | 17:05 | 1                    | 7                      |
| Dinesh    | 1        | 17:01 | 5                    | 6                      |
| Dinesh    | 1        | 17:01 | 5                    | 6                      |
| Dinesh    | 1        | 17:01 | 5                    | 6                      |
| Dinesh    | 1        | 17:01 | 5                    | 6                      |
| Dinesh    | 1        | 17:01 | 5                    | 6                      |
| Dinesh    | 1        | 17:07 | 3                    | 6                      |
| Dinesh    | 1        | 17:07 | 3                    | 6                      |
| Dinesh    | 1        | 17:07 | 3                    | 6                      |
| Guilfoyle | 1        | 17:02 | 1                    | 1                      |
| Jared     | 1        | 17:01 | 1                    | 2                      |
| Jared     | 1        | 17:03 | 1                    | 2                      |

They are tremendously helpful in analyzing and grouping data quickly for further analysis and modeling. 

So when I found out MySQL doesn't support window functions, I couldn't believe it, because to me, they are an essential part of the ANSI/ISO 2003 standard. Even Microsoft, who is usually behind, added them to SQL server [in 2012](http://sqlmag.com/sql-server-2012/how-use-microsoft-sql-server-2012s-window-functions-part-1).  [Here's an example](https://www.percona.com/live/mysql-conference-2014/sites/default/files/slides/Percona-Windowing-Functions-InfiniDB-PDF.pdf) of how painful SQL queries can get without windowing.  

When I tried to do these aggregations in MySQL, I got back [pages of StackOverflow results](http://dba.stackexchange.com/questions/40130/mysql-and-window-functions) of people who tried to do the same thing and failed. There is an elaborate workaround that includes [keeping track of counts of user variables](http://stackoverflow.com/a/20432371). 

But I was more interested in *why* window functions don't exist, and more importantly, if Oracle plans to make my life easier in the near future. 

There wasn't anything in a quick Google search, so I went to the next logical place: the MySQL bug tracker. Judging from the bug history, windowing functions (and CTEs) have been[ a requested feature](https://bugs.mysql.com/bug.php?id=35893) since [at least as far back as 2007](https://bugs.mysql.com/bug.php?id=28957).  After an extremely well-reported issue, the answer from the devs was, "Thank you for the bug report feature request," and not another answer, even when a user in 2014 asked when the full breadth of SQL:2003 would start being supported. 

I began wondring if it was usual for MySQL developers to be unresponsive, or minimally responsive to feature requests, so I looked around, and found this page that [catalogs bug tracking](https://bugs.mysql.com/tide.php) on a month-over-month basis. 

With some quick DataScience (TM), aka an Excel chart, it was easy to see that development activity really kicked into gear in 2010, and then drastically dropped off, leaving me to believe this won't be fixed anytime soon.  

![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/bugs.png)

Given these trends and a quick scan of the trade press, it looks like the issue really is two-fold. 

First, MySQL was purchased by Oracle in 2008. Any time a product is acquired by a company, there is a concern about change in priorities, development staff, and general direction of the technology. When  MySQL was acquired, its original developers, [concerned about this very issue](http://www.computerworld.com.au/article/457551/dead_database_walking_mysql_creator_why_future_belongs_mariadb/),  forked the product to an open-source version, MariaDB, which has been in active development ever since. It's worth noting that[ MariaDB has](https://mariadb.com/kb/en/mariadb/window-functions/) window functions.  

Given Oracle's [general hostility to open-source contributions](https://techcrunch.com/2012/08/18/oracle-makes-more-moves-to-kill-open-source-mysql/), it's not surprising that MySQL has suffered simply by being associate with the company. Although Oracle claims that it has [doubled the size of the MySQL team](http://www.theregister.co.uk/2014/09/30/mysql_openworld_2014_session/), the number of open-source shops using it has shrunk, and it's no longer standard on many Linux distributions. 

In addition to Oracle's contribution to shrinking the user base, the NoSQL trend really hit big starting in 2006, leaving some of the best talent to work on distributed systems as opposed to shoring up traditional relational databases.  It also has meant that developers are using stores like MongoDB in new,[ MEAN web stacks](http://www.infoworld.com/article/2937159/application-development/mean-vs-lamp-your-next-programming-project.html), contributing to MySQL's decline even further. 

LAMP AND MEAN: Guess which is which. 
![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/lampmean.png)

All of this makes me wonder about the stability of web app infrastructure going forward. The LAMP stack has been quietly and steadily powering Wordpress, 37Signals, and hundreds of thousands other websites for years. It's a known element, a vanilla default so easy to set up and run. But at the same time, all of its elements are atrophying. PHP is no longer a favored scripting language, and MySQL, it looks like, is quietly looking to stop active development. 

At the same time, while MEAN is new and shiny and scales, it is [not yet at a point of maturity](https://rclayton.silvrback.com/means-great-but-then-you-grow-up) that [has been proven](https://www.linkedin.com/pulse/lamp-vs-mean-deciding-right-stack-your-startup-robert-roose) in thousands of use cases over years and years of hardware and software changes. 

Where does this leave us? Somewhere between the old (Web 2.0?) and the new (MEANTensorFlowMicroservices.io (R) And where does this leave me? Still, very unfortunately, without window functions. 

EDITED TO ADD : [Morgan Tocker from MySQL](https://twitter.com/morgo) sent me an email with some clarifications to the data analysis I did on my post:  

>1)      The data science here only includes bugs from bugs.mysql.com

>We also have bugs reported from customers in the Oracle bugs system (that applies to all Oracle products). See for example >in the [release notes:](http://dev.mysql.com/doc/relnotes/mysql/5.7/en/news-5-7-13.html)There is a short bug number format >and a long one.
 
>2)  The front ~ 12 months of the data science is not quite accurate – because there are some bugs fixed waiting in feature >branches which will not yet be merged. [For example](http://labs.mysql.com/), what we have in labs and in development in >8.0: (The clone for 5.7 RC was over 12 months ago.  The new data dictionary, which is considerable work does not appear in >these stats.  Bugs are only closed when they go out in a release.)

>3)Sun acquired MySQL in 2008 (not Oracle).  Oracle acquired Sun in 2010.

>If I can encourage you to look at commits (and contributors), you will see that the team size has indeed doubled.. and the >quality of even patches has gone up.  An individual evaluation of code showed that there was more new changes in 5.6 [than >any release prior](https://www.flamingspork.com/blog/2013/03/05/mysql-code-size/):
 
>And in 5.7, I built a list of new features: http://www.thecompletelistoffeatures.com/

He added in a message, "Thank you, feedback on Windowing functions heard loud and clear."


