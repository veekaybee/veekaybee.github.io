+++
date = '2015-08-26T00:00:00Z'
title = 'How non-technical teams work together'

+++

<a data-flickr-embed="true"  href="https://www.flickr.com/photos/uw_digital_images/4670205658/in/photolist-87G2rd-nxDbAc-Fk7SA-j7W8Fs-nAbb65-4jyiS6-nfqZmM-m47Jk6-s8YWAM-4jyiHr-pKfTZE-nRF7UP-bzMsVi-bmDkbj-q42AoA-qgoLmq-pXsfe1-i9pFhn-q2MbC7-4jyiLr-bzNSx2-sSd555-ndojN9-acvTQR-7XQhX2-4jCmKf-9e565s-hLztNx-7ZJAhA-bmDk5h-88u6P9-ndoiCJ-9e21tk-pCK3Yp-pAvnht-bg9GG2-pvXHbs-pHdrHE-9c3FZA-aDSk8D-t3WEVZ-7ZFqbP-rw6TxR-ndohL2-qb8ms-egpM3S-agKkeD-rW93n5-nbWNPA-cj2EGy" title="Packing Carsten&#x27;s weiner sausages on an assembly line, Tacoma, Washington"><img src="https://farm2.staticflickr.com/1298/4670205658_2b4d9b3d55.jpg" width="500" height="397" alt="Packing Carsten&#x27;s weiner sausages on an assembly line, Tacoma, Washington"></a><script async src="//embedr.flickr.com/assets/client-code.js" charset="utf-8"></script>

I work as a data scientist. I'm also in my last semester as an MBA student. One of the most important things I've learned over the course of the program is how differently technical and business people think. 

There is a [huge push](https://code.org/) today for society to become technically literate, particularly given that the Robots are Coming. But the opposite is true as well. It is for technical people to understand the non-technical mindset, particularly given that most people, even if they work in the tech industry, report to executives who are well-versed in their industries and the nuances of corporate life, but are not necessarily technically savvy, at least in the way developers define it.  

For example, many of the classes in my program so far have focused on group work of up to 6 people in a team, with the final grade being based on a submitted group paper or presentation.  In theory, these classes sound a lot like engineering teams writing code: each person writes their own section and corrects any mistakes they make along the way. Then all of the changes get merged together in a single paper which is "pushed to prod," aka submitted to the professor by a single person. 
 
It's apparent to anyone in the software industry that there is a specific set of tools for this workflow:  version control, preferably Git (on GitHub or Stash), Evernote to save research, Sublime Text/vi/emacs to write, and the command line to glue it all together.

But this kind of workflow has an extremely steep learning curve that is not accessible to the majority of people.  Why? Let's start by looking at version control, for example.  Learning version control is so far out of the scope of someone who's worked in Word and Excel for their entire office life that it is not only never mentioned, but simply not even feasible to implement in classrooms teaching business students how to collaborate.   

Let's take a look at this very beginner Git question on Stack Overflow: [How to revert a commit](http://stackoverflow.com/questions/927358/how-do-you-undo-the-last-commit), aka "how to undo the last change you personally made to the shared document" and let's list out all the concepts someone needs to be familiar with: 

* What a commit is
* What the right terminology for undoing something in Git is
* What the different states between your personal repo and the master repo is
* Branches 
* How to add files to a Git repo
* How to create files...can I just move files over from other folders? What is vim? 
* Why you need to add files to a Git repo if they're already in a directory
* Where to even type the commands into. What's the command line? How do I get to it? How does Unix work? What if I don't have Unix? What if I have Windows? Why is this different than the Mac command line? 
* What is "HEAD"?
* Why are there two different places where there's a latest copy of my document (origin and master?) Why doesn't it just all update simultaneously at once? What's a file state? 
* Why can't I physically delete the file from the shared repository? 
* Why can't I just push if someone else makes a change? All I'm doing is making small edits. It shouldn't be a big deal to fix a couple typos. 

That's just off the top of my head. And this doesn't even get into the culture and stigma of not knowing enough about tech. Most non-technical people are too embarrassed to ask anyone technical at the office about how technical stuff works, for the same reason anyone anywhere is afraid to look ignorant. I know this was absolutely the case when I started out as an intern and continued for some time into my career. 

 *I'm really intimidated by tech people. What if they think I'm stupid? They always seem to be in another universe, working on black screens. And,if I learn version control, who can I use it with? My colleagues are too busy. We will send files to each other via email, because it's easy. Why do I need this?*

For someone coming from the point of view of the GUI, this is an incredibly complex ecosystem to learn, probably as complicated as learning the rules of double-entry accounting for a non-accountant for the first time. (how is the balance sheet related to the income statement? why are these the primary ways of looking at money? what's the difference between revenue and net income?[ whoa! cash flows!](http://www.investopedia.com/university/financialstatements/financialstatements2.asp)), with just as little benefit as learning it (how many engineers need to do the accounting for their organization?)  

With classes lasting fifteen weeks (much less for weekend-only classes or half-semester classes,) there is not a lot of time to get into the subtleties of rebase. The work just needs to get done, as is the case in the real world.) I once was a TA for an intro to command line class and it took 2 hours to learn about creating folders, directories, and files, not accounting for the time it took to set environments before class. We never even got into pipes. 

On top of everything, I'm in a part-time program, which means everyone is also juggling full-time jobs and families. There is no time to fiddle with [ANY of this](http://apple.stackexchange.com/questions/18470/why-is-git-not-found-after-installing-os-x-lion). 

##So how *do* MBA teams collaborate in 2015?

The business world doesn't collaborate the way the tech world does, but there is a suite of tools I've heavily leaned on over the course of my program.  

* **Google Drive** - This has been the workhorse of most of my MBA team projects until very recently. Someone is designated the "manager" of the shared Google Drive folder. One person who leads the project prompts people to upload files, and one person synthesizes all of those files into one document. Everyone makes changes and suggested changes to the document until the editor goes over everything one last time before submitting. All final changes, which could be floating around on Google drive, in email, and verbalized in person, go through a single person.  Everyone needs to see what's happening real time, so Google Docs is the best text editor out there. Business teams quickly organically designate a single person as a the point of contact for all editing, acting as a filter between all the editing and the final product. 
* **Google Hangouts, or whatever Google + is known as now.** This is for team meetings. Nothing beats face-to-face, but a lot of times, even if we go to the same university, we work in completely different parts of the city and have no way to meet after work. Hangouts is super-efficient because it lets you be on Google Docs and Calendar at the same time as you're talking and integrates the experience.  A lot of people have Skype, but if we're already in a joint Google Drive document with Google emails IDs, it's just easier to use Hangouts to plan meetings on our joint calendars. Also, as far as I know, Skype still does not have 3-way calling. One person up the meetings and everyone joins. The video quality, as of 2015, is still not great, with constant dropped calls and blurriness, but it's still good enough for five people living in different locations to get hour-long meetings done. 
* **Email** Still used extremely heavily for everything from emailing professors to arranging meeting times to sending files. Everyone in the group is usually included on these emails. The catch is that we have university Gmail accounts and personal ones. Most people filter all university mail to go to their personal account. Some don't. So an email to a group of 5 people might have 8 or 9 different email addresses on it at any given time.  
* **Excel Spreadsheets** - Used for anything and everything from tracking hours spent on a project to homework problems for accounting and finance, to generating the [business model canvas](http://www.businessmodelgeneration.com/canvas/bmc).   
* **Prezi** has become huge in MBA circles, but there is still the issue of getting it to work outside of Prezi when professors mostly want hard copies of presentations, or WiFi in presentation rooms doesn't cooperate (often). PowerPoint is still the old standby. 
* Most business students and professors use **Windows**, particularly since most of the computers students bring to class are from work, and most organizations where business students work ( a lot of pharma, finance, and healthcare in Philadelphia, where I go to school) are Windows environments. Some students have Macs. I'm starting to see the number of Macs increase on campus, but no more than maybe 30% in any given class. Most of these Macs are personal computers, not work machines. Mac students usually are fine with basic applications like PowerPoint, but have a hard time replicating spreadsheets or getting things that are not in PDF format to render correctly (i.e. Word documents with illustrations done a certain way.) Most universities are still not super Mac or Linux friendly. 
* **Webex** - Our university has a contract with them so we can somehow use free licenses, and also it's the screensharing software most business students are already familiar with, having used it at work. 
* ** Blackboard** -The educational content management system of choice at most universities, including mine. Extremely clunky interface even though they supposedly [had a major UI update recently](https://www.insidehighered.com/quicktakes/2014/07/17/blackboard-unveils-public-cloud-version-ui-redesign-learn), but most of the files the professor posts for reference are there, so we often dip into Blackboard to look up grades, and to pull out files, then upload them elsewhere where they can be more easily accessed. 

Tools that are not used: 

* **Cell Phones** - No one has each other's numbers, usually, unless it is an extreme emergency. Most communication is asynchronous through email. 
* **GChat** - Takes too long to set up between two different email accounts (personal and school-mandated one) and not easy to group chat with more than one person.
* Any **project planning software** (Trello, Asana, especially not Jira, etc.) - Takes too long to enter stuff in and has too many details, even Trello. And most people don't work with and are not familiar with agile flows. It's also hard when more than one person is working on the same thing to move cards in the right direction. 
* **Skype** - Very hard to do group chats. 
* **Goto meeting or any other third-party screen sharing software** - Too long to set up (you have to download stuff, then create a dial-in, etc.)

##Why analyze the tools business students use? 

First, many MBAs will go on to be tomorrow's executives — people who approve budgets, set ROI rates, and listen to you make the case about why your department needs Redshift or needs to send people to Strata.  Making sure they understand what you're doing is key to the success of your department, even if your department is just the CTO, talking to the CEO, who is your cofounder. The tools they're using today are likely to be their frame of reference and comfort zone well into the future. 

![image](http://assets.amuniversal.com/cb25b540c45a0132d8f0005056a9545d)

Dilbert is an exaggerated but often accurate example of how technical and non-technical people see each other, and it's important to break through that wall and understand how the other person communicates to get any real work done. 

Second, this set of tools is prime for someone creating something more efficient for non-technical teams that is not just project management or communication.   That something needs to make version control more efficient than 5 people editing a Google Docs document at the same time, but much easier than version control the way developers think of it. That something needs to probably be an abstraction of Git, needs to include video capabilities native to whatever it has, as well as the ability to chat and save chats, the ability to create Excel-like documents, to create simple graphics including Venn diagrams and bar charts,  and to be easy enough to access from multiple places (work, home, school, mobile) and save states.  This is extremely hard, which is why it has been tried hundreds of times by millions of companies (Evernote, Atlassian, Asana, Basecamp,and *shudder* Sharepoint.)

That something right now is **Slack**. This semester is the first I was able to use Slack, and it's made running group projects a million times easier. This is not an exaggeration. Being able to see all conversation history in one place, instead of going into each individual Google Chat, being able to add calendar events, automatic reminders of team meetings from a shared Google Calendar, uploading files for reference, being able to @ everyone in a channel and have them receive notifications that they need to do something, setting up separate channels as placeholders for links to Google Drive files - all of this has made running a team project with asynchronous inputs MUCH less of a nightmare. 

There are a couple features Slack is lacking, such as Google Hangouts-like functionality, the ability to screenshare and annotate files, a really solid project management component, and the aforementioned version control.  But Slack is a very young company and has the potential to create many more integrations, which could move it to be the product that covers at least 70% of inefficiencies in the space. Unless there is something new on the horizon. Whatever it is, it better have [Giphy integration](http://giphy.com/posts/slack-adds-giphy-to-every-chatroom-wut).

 





