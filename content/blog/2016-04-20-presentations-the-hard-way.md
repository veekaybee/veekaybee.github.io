+++
date = '2016-04-20T00:00:00Z'
title = 'Building presentations the hard way with reveal.js and Jupyter'

+++

<a data-flickr-embed="true" data-header="true" data-footer="true"  href="https://www.flickr.com/photos/fblanc35/13195059744/in/photolist-m718y5-dsNSRN-at2M8M-3kKGmL-paasgL-8nb9gW-4CCPqR-8Zv4Qq-rmaUL8-aUpP5F-aX3vQe-4a47Uc-bC8buA-oLKijv-4jfTnt-8HLXb3-qM8aTS-stKFLH-rxQTtd-brnt3K-7SBTED-dHYhX4-4FNyvi-5WvCsq-ewYaSq-dsNT6s-aypaJM-oDjE1K-os1mVX-72iASJ-d78TBd-eL7o1P-7QQjTV-2XNWVh-dXqtzj-qpo9St-6J2NsH-ewUZvB-c4yKE-azz2iy-SBgqj-fJ7wpT-j4pSQK-6Eiyfz-cr5Nds-4kaDvB-ewUZXK-4FSLam-ewUZrT-4CCQ98" title="machines"><img src="https://farm3.staticflickr.com/2146/13195059744_59f18e7489.jpg" width="500" height="333" alt="machines"></a><script async src="//embedr.flickr.com/assets/client-code.js" charset="utf-8"></script>

I've been working for a presentation on a talk I'm giving in a couple weeks, and the audience is fairly technical. All the cool kids are using reveal.js for technical presentations [for the past couple years](https://opensource.com/education/13/10/teaching-with-revealjs). 

I'm thinking it's for a couple reasons: 

+ clonable as a [GitHub repo](https://github.com/hakimel/reveal.js/)
+ beautiful [themes and typefaces](http://lab.hakim.se/reveal-js/#/themes)
+ capitalizing on increase in static pages/ Javascript trend
+ free and available on every machine that's connected to the internet (Powerpoint is not)

Although I was won over by how nice it looked, I wasn't too familiar with reveal and didn't feel like going through the documentation.  But, in playing around with the settings on Jupyter notebooks, which I use a lot for work every day, I found that they can render cells as reveal slides. It's now a built-in option in the settings. 

![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/jupyter.png)

Creating the slides was a breeze. Each cell becomes a slide that you can write in markdown and run in Jupyter, or then compile into the HTML slideshow and serve locally via tornado. 


![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/coexist.png)

`jupyter nbconvert techtalk.ipynb --to slides --post serve`

	mbp-vboykis:techtalk vboykis$ jupyter nbconvert techtalk.ipynb --to slides --post serve
	[NbConvertApp] Converting notebook techtalk.ipynb to slides
	[NbConvertApp] Writing 202825 bytes to techtalk.slides.html
	[NbConvertApp] Redirecting reveal.js requests to https://	cdnjs.cloudflare.com/ajax/libs/reveal.js/3.1.0
	Serving your slides at http://127.0.0.1:8000/techtalk.slides.html
	Use Control-C to stop this server
	WARNING:tornado.access:404 GET /custom.css (127.0.0.1) 1.74ms
	WARNING:tornado.access:404 GET /favicon.ico (127.0.0.1) 0.57ms

This is really great for [serving locally](https://www.reddit.com/r/IPython/comments/37yvvd/using_ipython_notebook_for_creating_slides/). 

However, when I tried to [upload my compiled slides](https://www.chenhuijing.com/blog/revealjs-and-github-pages/)  both the HTML and the notebook, to GitPages through the [gh-pages branch](https://help.github.com/articles/creating-project-pages-manually/), I realized that Jupyter [renders Reveal through a CDN](https://github.com/jupyter/nbconvert/blob/9f1795f67b9e85fa3f86c43d1e469fdde2121048/nbconvert/postprocessors/serve.py) by default. 

This means, if you don't already have reveal installed, Jupyter's version of the files it gets from CDN through nbconvert is different than what's offered on GitHub, both visually and in the code. This is probably because Jupyter is using a version that is several versions [behind the latest on GitHub](https://github.com/hakimel/reveal.js/releases). 

Here is the same exact slide, rendered locally:


![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/locally.png)

and on GitPages (looks much nicer here, right?)

![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/gitpages.png)

What I ended up having to do was to fork the latest copy of  reveal.js and put all my slides into the template from the nbconvert-rendered HTML file, which involved a lot of manual copying and pasting. 

Check out the formatting of a single slide in the GitPages v.s. how nbconvert renders the HTML. In the screenshot below, I left out a lot of the additional code for each Jupyter notebook cell as well, which made things extra-tricky. 

![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/sidebyside.png)

So it essentially took me three times as long to figure out how to use reveal than my old standby, Powerpoint slides as PDFs, which would have taken me an hour to make if I had all of my talk notes outlined. And by the way, we're now at a point where it's possible to make [really nice-looking Powerpoint slides](http://gazit.me/2012/12/05/designing-presentations.html). 

Powerpoint isn't considered sexy anymore, but there's no messing around with code, everything looks exactly how you want it, you don't need to build a separate library of pictures, and you can easily convert to PDF or other formats (something I still haven't figured out with Jupyter/reveal because of an El Capitan upgrade error that [still has me scratching my head](https://github.com/ipython/ipython/issues/8935))

It's definitely a nice-to-know for next time, and I'm glad I gave myself over a month to get this working, but it's a nice reminder of two things: 

+ taking shortcuts (in this case, not reading the Reveal documentation to begin with) will always result in more work in the end
+ That sometimes just because a tool is shiny and "hot" doesn't mean it's the easiest to use
