+++
card = 'summary'
creator = '@vboykis'
date = '2021-01-09'
site = '@vboykis'
title = "Git, SQL, CLI"
description = 'The three MVP tools you need to know to do data work'
twitterImage = 'https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/git-sql-cli.jpeg.png'
+++

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/git-sql-cli.jpeg" width="600px">}}

{{<tweet 1451985733867216898>}}


I've now written data-centric code across 6 different companies (12 if you count the different companies I consulted for when I was in consulting). I've so far worked in Python, Scala, Java, SAS, Eviews, R, and, for brief time in undergrad econometrics,Stata. I've had Macbooks where I could do whatever I wanted and completely locked-down Windows environments where I had to open a ticket with Ops to get access to [Java 8.](https://vickiboykis.com/2019/05/10/it-runs-on-java-8/) I've used SVN and Git, Postgres and Redshift and Hive, [Google Sheets](https://vicki.substack.com/p/google-drive-is-production) and Excel, [Spark and Bash.](https://vickiboykis.com/2017/03/20/should-you-replace-hadoop-with-your-laptop/) 

It's a little bit of everything, so I've had a broad exposure to both tech companies and Fortune 500 companies. As a result,  I've been thinking about what I would tell someone new to data work what they should learn keeping in mind that all advice is a form of nostalgia)? 

I've narrowed it down to three basic tools. Here are my hand-wavey criteria for essential tools: 

+ Tools that have been around for a while (for some definition of while)
+ Tools that are mostly environment-agnostic (i.e. you will encounter a variation of these tools in every place you work as long as you work with tech) and
+ Tools that are foundational i.e. if you know them, you can start to understand other tools

In other words, if you know nothing else, you'll be able to start to get to productivity almost immediately using these tools. If you don't know these tools, you will be hindered from data work. If you don't have these tools available to you, you're not in an environment that's conducive to data work. 

## SQL

Learn SQL first. You absolutely cannot do any data work until you are able to get data from a source. Regardless of how many database solutions, key/value stores, distributed data lakes there are today, [they all eventually converge to SQL](https://erikbern.com/2018/08/30/i-dont-want-to-learn-your-garbage-query-language.html). Hive is SQL on the JVM. Kafka [has SQL now](https://www.confluent.io/online-talks/intro-to-ksql-streaming-sql-for-apache-kafka/). Almost every single tool that has users that need to analyze data will eventually introduce SQL, and once you know how to get to it, you can get to the data. 

Let's forget about the fancy stuff: 80-90% of the tech industry runs on traditional relational databases: Postgres, Oracle, MySQL. Once you know how to speak SQL, you can speak to any of them. 

SQL is also hands-down the easiest language for beginners to learn, because it is declarative. The other side effect that comes from knowing SQL is that once you have a good baseline understanding of how to write SQL queries, you can start to understand how databases work by [learning how to optimize queries](https://use-the-index-luke.com/), and now you're entering the world of computer systems and algorithms. 

Also, once you start understanding how data sources work together, you start slowly building an intuition for system architectures, and now you're off to the races.  

## Command Line

As a data developer, you will spend most of your time SSHing into servers, looking at stuff, and running code. This is especially true for companies that have moved [to the cloud](https://vickiboykis.com/2018/01/28/working-with-aws/), but the pattern of, "your code lives on some remote production server and you need to get to it" is universally true. Command line is your best friend here. 

If you cannot navigate the command line, you will lose a lot of productive time. Note here that I'm not suggesting everyone become a bash scripting genius or that you need to know every single vi command or emacs. But you need to know the basics: how to navigate a file system, how to shell into stuff, how to write simple bash scripts, how to look at files, etc. 

Everything we do as developers builds on this knowledge: Kubernetes, compiling code, sending code to other servers, looking at what's going on with servers, committing code, looking at ports, adding files, etc, etc. Having the command line open is a constant throughout my day. 


## Git

If we want to say "version control" more generally, that's fine too, but most version control these days is git. If you don't know how to use git effectively, at least in the cycle that you need to commit/pull/review code, you will also be hindered in productivity in any org you go to. 

Again, this doesn't mean being a super-genius with git, but [understanding enough](https://ohshitgit.com/) to walk back changes. Not being afraid of committing changes you're introducing will do a lot for developer confidence.  One of the only ways to get good at this is to continuously use Git/version control on a daily basis, and to create test projects where you mess up a lot and try things out. 



All of the usual caveats here apply: these are based strictly on my personal experience, working primarily on data-centric projects, over the past 15 years. But I've now done enough work where I can confidently say I wouldn't be able to get by without these tools. 



