+++
date = '2020-02-18T00:00:00Z'
title = 'Lauching a Jupyter notebook from within a virtual environment'

+++

<meta name="twitter:card" content="summary">
<meta name="twitter:site" content="@vboykis">
<meta name="twitter:creator" content="@vboykis">
<meta name="twitter:title" content="Launching a Jupyter notebook from within a venv">
<meta name="twitter:description" content="Notes for myself.">


When I'm working with Jupyter notebooks, I often want to work with them from within a virtual environment. The general best practice is that you should always use either virtual environments or Docker containers for working with Python, [for reasons outlined in this post](https://snarky.ca/a-quick-and-dirty-guide-on-how-to-install-packages-for-python/), or you're gonna have a bad time. I know I have. 

<blockquote class="twitter-tweet"><p lang="en" dir="ltr">&quot;All of my local Python environments&quot;<br>M.C. Escher, 1969 <a href="https://twitter.com/hashtag/devart?src=hash&amp;ref_src=twsrc%5Etfw">#devart</a> <a href="https://t.co/iosQJnXJXj">pic.twitter.com/iosQJnXJXj</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/925467002642354176?ref_src=twsrc%5Etfw">October 31, 2017</a></blockquote> <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script> 


The workflow is a little long, so I thought I'd document it for future me here. 

1. Go to your working repo. 
2. [Activate your environment](https://venv.netlify.com/) - Thanks to [Tim](https://twitter.com/tdhopper/) for creating the shortlink to my Netlify site, and for introducing me to Netlify.
3. [Open a Jupyter notebook within a virtual environment](https://anbasile.github.io/posts/2017-06-25-jupyter-venv/) by installing ipykernel in that virtual environment and creating an ipython kernel with your wanted environment name. Make sure to use python -m, per this: 

<blockquote class="twitter-tweet"><p lang="en" dir="ltr">Read a really helpful post on &#39;python -m&#39; from <a href="https://twitter.com/brettsky?ref_src=twsrc%5Etfw">@brettsky</a> that also reminds me (as if I&#39;ve forgotten 🙃) how much time and effort we as Python developers spend on isolating environments. <a href="https://t.co/7FvTHsugJh">https://t.co/7FvTHsugJh</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/1229453187893153792?ref_src=twsrc%5Etfw">February 17, 2020</a></blockquote> <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script> 

4. Launch Jupyter Lab (or notebook) and create a new environment with your notebook. 


Bash code: 

```bash
shell > mkdir envtests && cd envtests
shell > virtualenv -p python3.7 myproject
shell > source myproject/bin/activate 
(myproject) shell > python -m pip install ipykernel
(myproject) shell > ipython kernel install --user --name=myproject
(myproject) shell > jupyter lab
```

You can see in the image that we're using myproject as the Python version. To test the version within the kernel: 
```python
import sys
sys.version
```

![](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/myvenv2.png)

Editing to add the Twitter thread, where people added great comments: 

<blockquote class="twitter-tweet"><p lang="en" dir="ltr">Got tired of Googling this, so here&#39;s how to open a Jupyter notebook from within a virtual environment: <a href="https://t.co/1y2Jdu8TrN">https://t.co/1y2Jdu8TrN</a></p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/1229813718776786944?ref_src=twsrc%5Etfw">February 18, 2020</a></blockquote> <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script> 



*To edit this post, [open a pull request](https://github.com/veekaybee/veekaybee.github.io).* 
