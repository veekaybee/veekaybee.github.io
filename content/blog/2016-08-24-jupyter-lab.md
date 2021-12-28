+++
date = '2016-08-24T00:00:00Z'
title = 'Walkthrough of Jupyter Lab Alpha'

+++
![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/moving.jpg)

I love [Jupyter notebooks](http://jupyter.org/) as a replacement for REPL work, quick data manipulation with pandas, and visualization with matplotlib. I've also had great experiences sharing them with executives, who need to look at multiple pieces of data quickly to make decisions. 

For all their Swiss army knife capabilities though, notebooks are terrible at  [code continuity](https://www.reddit.com/r/datascience/comments/4lncvw/people_who_use_jupyter_as_their_main_ide_how_do/). 

For example, it's [a pain to](https://www.reddit.com/r/IPython/comments/3na2ud/how_to_use_git_with_ipython_notebooks/) get [version control set up.] (https://www.r-bloggers.com/why-i-dont-like-jupyter-fka-ipython-notebook/)

Because cells are really easy to move up and down, because code can change within cells, and because closing unsaved notebooks in the browser is easy, it's very hard to track the lineage (aka what's going on, when) of a specific notebook.  

# Why Jupyter is annoying to use

I'm introducing more rigor in data sharing and data analysis in my organization, and one of my efforts at version controlling data science work was to share a Jupyter notebook in Gitlab.  

It was such a mess with people making changes and deletions and very little annotation, as well as my own local version that had to be cleaned up and annotated significantly every time after I played around with even a few cells, that I'm now strongly against using Jupyter for permanent code storage. I develop my code in Jupyter and then write to Python modules if it needs to be part of any other programs. 

The second problem with notebooks is that they take up quite a bit of screen real estate. Once you have one notebook running, you need at least one command line window, and at least one separate browser window because you ultimately end up getting 8-9 different tabs going, and you're constantly tabbing between your open working document, the file browser, and 5 million other notebooks.   If you're like me, you also have several command line windows running jupyter processes going. All of this really takes off the edge what a great environment Jupyter is. 

So I was really excited to hear about[Jupyter Lab](http://blog.jupyter.org/2016/07/14/jupyter-lab-alpha/) development a couple months ago.  

# Why Jupyter Lab could help

Some of the features [they're promising](https://www.youtube.com/watch?list=PLYx7XA2nY5Gf37zYZMw6OqGFRPjB1jCy6&v=Ejh0ftSjk6g) include debugging/profiling, tighter integration with version control, and the ability to have all of the features of Jupyter in a single browser window. With the introduction of this, Jupyter is moving from an interactive to integrated development environment. 

Right now Lab is in alpha, which means several months before there's a stable release version, but the demo looked pretty cool, so I decided to check it out. 

Getting started with the alpha release is relatively easy, but requires some extra kinks if you have Anaconda set up as your Python environment. 

# Installing Jupyter Lab

Generally, you can install with the instructions on [GitHub](https://github.com/jupyter/jupyterlab) 

I already have Anaconda, [the preferred way of accessing default Jupyter](http://jupyter.readthedocs.io/en/latest/install.html), installed, so I had to do a couple steps to upgrade to Jupyter Notebook 4.2, the recommended environment for testing Lab (I had to do the install and uninstall because the override didn't work.)

`mbp-vboykis:~ vboykis$ conda update conda`

`mbp-vboykis:~ vboykis$ conda uninstall jupyter`

`mbp-vboykis:~ vboykis$ conda install jupyter`

`mbp-vboykis:~ vboykis$ conda install jupyter notebook`

`mbp-vboykis:~ vboykis$ jupyter notebook --version`

`mbp-vboykis:~ vboykis$ 4.2.2`


Once I did that and tried to run lab, the jupyter and Notebook config files showed a conflict. This is a [known issue](https://github.com/jupyter/notebook/issues/1508).  There is a default ~/.jupyter settings directory, where the jupyter_notebook_config.py file is located, and there is a similar jupyter_notebook_config.json file that overrides settings specifically for different environemnts within Jupyter, like Lab and Notebook.  Here's more on [Jupyter internals.](http://jupyterlab-tutorial.readthedocs.io/en/latest/repo.html) 

	mbp-vboykis:~ vboykis$ jupyter lab
	[W 14:37:24.455 LabApp] Unrecognized JSON config file version, assuming version 1
	[W 14:37:24.457 LabApp] Collisions detected in jupyter_notebook_config.py and jupyter_notebook_config.json config files. jupyter_notebook_config.json has higher priority: {
      "NotebookApp": {
        "nbserver_extensions": "{u'jupyterlab': True} ignored, using {u'jupyter_nbextensions_configurator': True}"
      }
    }

I tried a number of different things but what worked for me was removing the default .jupyter file. **Don't do this** if you have important settings, or version control your previous settings. Here are some examples of [things that could potentially be in those settings](http://jupyter-notebook.readthedocs.io/en/latest/config.html#config). 

	mbp-vboykis:~ vboykis$ rm -rf ~/.jupyter
	mbp-vboykis:~ vboykis$ jupyter lab
	[W 10:59:33.093 LabApp] Unrecognized JSON config file version, 	assuming version 1
	[I 10:59:33.135 LabApp] The port 8888 is already in use, trying 	another port.
	[I 10:59:33.139 LabApp] JupyterLab alpha preview extension loaded from /Users/vboykis/anaconda/lib/python2.7/site-packages/jupyterlab
	[I 10:59:33.143 LabApp] Serving notebooks from local directory: /Users/vboykis
	[I 10:59:33.143 LabApp] 0 active kernels 
	[I 10:59:33.143 LabApp] The Jupyter Notebook is running at: http://localhost:8889/
	

# Using Jupyter Lab 
Once you actually load up the Lab, it looks pretty neat: 

![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/jupyter2.png)

The best feature out of the gate is the file navigation on the left-hand side. This makes an amazing difference in quality of life inside of Jupyter, instead of having to open multiple tabs and navigate away. I usually have 3 or 4 different notebooks in a single directory that I need to cross-reference. This makes it crazy simple. 

Second, the launcher allows you to have all of your notebooks, editors, and terminals in one browser window.  This is crazy-convenient for both the aforementioned multiple notebooks, and for the command line. And, wait for it, YOU CAN DRAG THE COMMAND LINE SO THAT IT'S VISIBLE AT THE SAME TIME AS YOUR NOTEBOOKS. Want to look at two notebooks at the same time? Do it!

It just feels more productive: 

![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/jupyter3.png)

So far, so good. I can have 50 million .ipynb open without overloading Chrome. I can also have the terminal, markdown files, and browse my files, all in a single place. 

How about some actual in-notebook features? The one I use the most in Notebook is the arrow that moves cells up and down. This unfortunately doesn't exist in Labs. You can look in the shortcut menu and see that you can use A and B once the cells are selected blue (for movement of cells as opposed to manipulating code or text within them). I guess this is something to get used to: 


![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/jupyter4.png)


And how about version control? The way people have addressed this so far is with post-commit hooks by [converting all .ipynb artifacts](http://www.svds.com/jupyter-notebook-best-practices-for-data-science/) to plain Python through _nbconvert_.  There doesn't seem to be anything in Jupyter Lab (yet) that automates this process or has closer links to git, which is disappointing, because it does take a while to set up this kind of workflow, especially if you're working in a team where many are familiar with Jupyter but not necessarily shell scripts or version control. 

And here, I think, is the crux of the problem. Jupyter is incredibly easy to use as is. That's the genius of it, that some of the hard parts of programming are abstracted away so you can focus on working with data. And that's the tradeoff: either easier user experience or the ability to customize. 

So far, it seems like Jupyter Lab is continuing to head in the direction that Jupyter started: to provide an easy interface to numerical programming. It will be interesting to see how it continues to evolve. 





