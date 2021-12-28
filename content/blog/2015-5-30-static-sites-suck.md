+++
date = '2015-05-30T00:00:00Z'
title = 'Man, do static sites suck.'

+++

{{< figure src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/static/images/ducreux.png" width="600px">}}

Whenever I click on HackerNews links these days, I see a TON of statically-generated sites either through Jekyll or Octopress or (in the Python community) Pelican. This trend has been particularly strong over the past three years or so. These themes always look really clean and, for some reason, the essays/thoughts come through more well-organized and cleaner than on Wordpress, which has been my blogging platform of choice [for over 6 years] (http://blog.vickiboykis.com). 

I like clean stuff and I like tech blogs. So, I decided to give Jekyll, the most popular of the static sites, a try. At the same time, I've been revamping my [main website](http://www.vickiboykis.com) to include some other Wordpress blogs, so I decided to see how long it would take to install Wordpress on Bluehost versus Jekyll on Gitpages.  

The extra-super bonus challenge was working around a four-month-old's schedule. 

## Here are the steps to set up a Wordpress blog:


1. Set up domain name + hosting. <br>
2.Baby's eating 
3.Baby's napping!
4. Install Wordpress on the [domain.](https://codex.wordpress.org/Installing_WordPress#Famous_5-Minute_Install)
5. 20 minutes of puttering around with config files, setting up MySQL, logging into the site, especially if your provider doesn't support phpMyAdmin or Cpanel.  <br>
6. You’re blogging. 
7. Oh wait, you want to change themes and include a picture with your post? No worries. Do that through the backend admin UI. 



## Here's how to set up a static blog with Jekyll: 

Here are the steps I started following three days ago for Jekyll. I started pretty much from scratch:  Googling how to set up Jekyll on GitPages. The Jekyll Bootstrap tutorial was the top hit, so I started [follwing it](http://jekyllbootstrap.com/usage/jekyll-quick-start.html). 

1. Clone the repository. Hold up what. Cloning.  Ah, Git. it’s been a WHILE (I use Hg at work.)  Time to refresh my memory, just enough to get going with this. <br>
**10 minutes later**
2. `git push origin master.` Except when I tried to do that after I set up my initial repo, I got a conflict error because when I created the repo server-side on GitHub, I created a readme.md file ) which GitHub suggests doing if this is a new repo.) Time to bring in reinforcements (aka husband, who works in Git). 
3. 20 minutes of puttering around, figuring out how to resolve merge conflicts with the readme.md. Once this is resolved, I'm still getting an error on my published page? Why? Github now publishes blogs to the .io domain instead of .com. (the Bootstrap instructions are out of date)  At this point,I'm sweating because it sounds like the baby might be waking up and I'm not even close to anything.   Finally! The blog is up. But the instructions say that you also have to build and test Jekyll bootstrap locally, so I'm not done yet.  I think I’ll pick this up tomorrow morning.  <br>
 **The next day** 
4. I haven't used Ruby ever on my home Mac, so I'm out of date.   `gem install jekyll.`  But that doesn’t work because I don’t have gem. Brew gem. Nope, Brew broke since I upgraded to Yosemite, which 20 more minutes of digging around reveals. Uninstall Brew. Install new Brew.  Install gems. Finally install jekyll. I'm running locally now!
5. Create a test post. Load `localhost:4000`
6. Push it up to Gitpages. Champagne bubbles. 
7. But wait, this theme is kind of not as minimalist as I was looking for, and one of the main reasons I chose Jekyll was because of the themes. So I want to change the theme to something snappier, like all the cool kids have. [Go here](http://sparanoid.com/lab/amsf/getting-started.html). Follow  the instructions. Except  `bundle install && npm install` doesn’t work. 
5. I need to install some more Ruby updates. Finally!  `bundle install && npm install`
`Could not locate Gemfile or .bundle/ directory .`  Dang it. I would look into this, but the baby sounds like she could be awake. . .So let’s see if I can type up this post and just publish it quick. But wait! My theme still has all the defaults for user profile, etc.  in it. How do I change that?<br>
**20 minutes later** I’m ready to blog!<br>
7. The baby is awake and ready to play 
**4 hours later**
8.  Alright! Wrote my first post on Evernote...now to translate it into Markdown and push up!
8a. Reading up on Markdown. 
9. Wait, it won’t let me push to Github because I downloaded a theme I was interested in and now I just got an error email from Github 
>The submodule `almace-scaffolding` was not properly initialized with a `.gitmodules` file. For more information, [see here](https://help.github.com/articles/page-build-failed-missing-submodule.)
10. Weep.  <br>
**Next day.**
12. Realize Jekyll Bootstrap is different and worse than vanilla Jekyll, for two reasons: 
    + Plain Jekyll themes don't work with Jekyll Bootstrap
    + and that by cloning Jekyll Bootstrap, you are making commits to the master Jekyll Boostrap branch.
12a. Panic. 
13. I uninstall Jekyll Bootstrap, try to install Vanilla Jekyll. 
14. I curse humanity and question life choices. 
15. I contemplate starting from scratch in Pelican. 
15. I find [this link] (http://www.smashingmagazine.com/2014/08/01/build-blog-jekyll-github-pages/) , which is supposed to make it super-easy to fork a copy of reasonably working Jekyll and not have to run local dependencies.  <br>
**baby is down for nap**
16. That was kind of easy to deploy! but I realize that the front page doesn't display posts. I try to figure out what in Jekyll displays posts.  
17. I Google how to show posts on main page with Liquid. 
18. I have some ice cream. 
19. I play with the baby. 
20. Baby is down for nap (this is probably the 10th time in the three days after I started project).
21. I semi-figure out Liquid templates. 
22. I get the main page to show posts with this [snippet] (http://stackoverflow.com/questions/9794699/listing-all-the-blog-posts-with-content-with-jekyll)
22. I publish this post. 
23. I spent 20 more minutes figuring out how to include a picture with a post, including how GitPages shows assets and the Liquid formatting. 
23. I go to look for the wine.  

## To sum up

### Things you need to have a working knowledge of to blog on Wordpress: 
    + English 
    + some HTML 
    + some CSS
    + some SQL
    + some command line 
    + some server stuff <br>
### Things you need to have a working knowledge of to blog on Jekyll: 
    + English
    + HTML
    + Ruby
    + Liquid
    + Markdown
    + Git (including private and public keys)
    + Github
    + command line
    + YAML
    + CSS
    + [10 years' experience in Rails] (https://gist.github.com/dhh/1285068). 
