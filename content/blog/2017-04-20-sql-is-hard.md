+++
date = '2017-04-20T00:00:00Z'
redirect_from = ['/sql-is-hard/']
title = 'Setting up SQL for beginners is hard'

+++
![alt text](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/munch.jpg)

Edvard Munch, Building The Winter Studio. Ekely

Although there has been a huge backlash against traditional programming paradigms, particularly relational databases, [over the past several years](http://veekaybee.github.io/strata/), SQL is [still one of the most popular programming languages](https://stackoverflow.com/insights/survey/2017#technology-programming-languages).

First, it's proven (working in production settings for over forty years). Second, its human-language-like syntax and declarative nature make it a perfect stepping stone for people who have zero programming knowledge and get overwhelmed by having to start learning by understanding data types and loops. When I started working in analytics, it was the first language I learned. I love teaching it to people new to/outside of technology and seeing them understand the power of being able to manipulate a data programmatically without *shudder* Excel spreadsheets.

With imperative programming languages, you can get started pretty quickly. You might need to install JDK, download an IDE, and update a couple of dependencies, but as soon as you have your environment set up, you're writing code. (For beginners, [it's not always as easy as that](http://blog.vickiboykis.com/2013/07/cooking-100-stir-frys/)), but usually half an hour of poking around or so should get you going.

And, if you don't want to, you don't even have to deal with any of that. You can just go to Code Academy, CodingBat, or, more recently, sites like [Glitch](https://glitch.com/), where the entire environment is already rendered in the browser for you, and start practicing.

The point is, because most modern programming languages are  set up to move data rather than analyze it, they have a much lower overhead than SQL.  The problem with SQL is that you can't just get started. You need data, for that data to ideally be in several tables that are related to one another, loaded into a database, and made available in a way that you can access that database on your laptop. None of this is something beginners can easily do.

Interestingly enough, even though SQL is so well-established and has so many users, there simply aren't a lot of environments already set up for practicing it. I encountered this interesting quirk of the programing community when I first learned it, and unfortunately, not much has changed.

Several years ago, I was asked by [Girl Develop It](https://www.girldevelopit.com/chapters/philadelphia) to teach SQL to others, which I thought was a fantastic idea because of what I've already written about accessibility for beginners.  The first time I taught it in 2013, there was nothing already set up out of the box for me to use.

My criteria included:

1. Has to have an extremely easy to use UI for people who have never written code (so, no command line)
2. Has to be cross-system compatible since students have a range of machines
3. Accessible on any browser
4. Involves no pre-installation before starting the class (so, no Docker containers)
5. Is mostly open-source

My solution:

Running PHPMyAdmin on an AWS instance.

People really love to hate on PHP and the LAMP stack in general (I know I do,) but when something works, it just works.

Here's what I did to set up a MySQL PHPMyAdmin database for my students to practice on:

1. Start up an AWS medium Ubuntu instance.
2. Install the [LAMP stack](http://howtoubuntu.org/how-to-install-lamp-on-ubuntu)
3. Install [PHP 7](http://askubuntu.com/questions/705880/how-to-install-php-7)
4. Install Python so I could run [create users scripts](https://github.com/veekaybee/intro-to-sql/blob/master/python/addusers.py) and a couple of other clean-ups for the environment:
    
    A. Install easyinstall
  
    B. Easy intall [pip](http://www.saltycrane.com/blog/2010/02/how-install-pip-ubuntu/_)
  
    C. `sudo apt-get install python-setuptools python-dev build-essential`
  
    D. Install a [MySQL client for Python](https://github.com/PyMySQL/mysqlclient-python)
  
4. Install the database: `sudo service mysql start`
  
5. Find a [sample dataset to load into the DB](https://github.com/datacharmer/test_db) and follow instructions on the GitHub page to get it up and running.

And, phew. You're ready to go.

I just taught the SQL class again several weekends ago, and the set-up worked really well for 20-ish students, except for a moment when a cartesian join query made a couple of the instances hang, but I plan to refactor that in the next go-around.

After I presented, I wanted to see if anything new had come onto the market.  The only promising new product was [Mode Analytics](https://modeanalytics.com/) (h/t [Sara](http://www.saracanfield.com), which requires a sign-up and also is oriented towards Jupyter notebooks as opposed to pure SQL programming.

Here's the class I [ended up teaching](https://veekaybee.github.io/intro-to-sql/#/1). Hope this helps in case you're thinking about teaching SQL, as well.
