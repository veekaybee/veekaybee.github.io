+++
date = '2016-08-28T00:00:00Z'
title = 'How to parse emails with Python (or, how I liberated my Strata schedule)'

+++

[Strata](http://conferences.oreilly.com/strata/hadoop-big-data-ny), arguably the largest data science conference in the United States, is coming up in September and [my company](http://www.captechconsulting.com/) is sponsoring my trip there this year, so I've started planning out which sessions I'll be attending. 

One of the cool things you can do on the website for the conference is [plan out your schedule beforehand](http://conferences.oreilly.com/strata/hadoop-big-data-ny/public/schedule/grid/public/2016-09-27) by adding sessions to your calendar. 

But I wanted to then share that calendar with a coworker who is also going and compare calendars with him. Sharing .ics files isn't really optimal for me because I didn't necessarily want our calendars overlapping in my work Outlook calendar; I just wanted to do a quick scan of sessions.

 ![image](https://raw.githubusercontent.com/veekaybee/strata_schedule/master/sessionscan.png)

The best-case scenario would be something similar to a physical print-out,  a generated screenshot of the whole page, which you can do with the Full [Page Screen Capture extension in Chrome](https://chrome.google.com/webstore/detail/full-page-screen-capture/fdpohaocaechififmbbbbbknoalclacl/related?hl=en-US). 

But this would be hard to send/read, and to search for keywords. So my idea was to scrape the site for all of my session metadata. 

Unfortunately, the O' Reilly site is very hard to scrape.

![image](https://raw.githubusercontent.com/veekaybee/strata_schedule/master/yuck.png)

And I also didn't really want to [mess with cookies](http://stackoverflow.com/questions/2910221/how-can-i-login-to-a-website-with-python), a prereq for dealing with websites where you have login with a user id and password. 

So I decided to parse the .ics file that gets generated when you download your schedule into human-readable text.  

.ics is a calendar format supported by Google Calendar, Apple Calendar, and partially by Outlook. An ics file is a text file (utf-8) with a special format that has lines of content demarcated by the name, parameters, and values of a given field. 


My Strata ics file looks like this: 

	BEGIN:VCALENDAR
	X-WR-CALNAME:Strata + Hadoop World in New York 2016
	VERSION:2.0
	PRODID:Expectnation
	CALSCALE:GREGORIAN
	BEGIN:VEVENT
	DTEND;TZID=US/Eastern:20160928T120000
	DTSTART;TZID=US/Eastern:20160928T112000
	DTSTAMP:20160727T212110
	LOCATION:Hall 1C
	URL:http://conferences.oreilly.com/strata/hadoop-big-data-ny/public/schedule/detail/51777
	UID:http://conferences.oreilly.com/strata-hadoop-big-data-ny--s2016-09-28-11:20--51777
	SUMMARY:Why should I trust you? Explaining the predictions of machine-learning models
	DESCRIPTION:Presented by Carlos Guestrin (Dato). Despite widespread adoption, machine-learning models remain mostly black boxes, making it very difficult to understand the reasons behind a prediction. Such understanding is fundamentally important to assess trust in a model before we takeactions based on a prediction or choose to deploy a new ML service. Carlos Guestrin offers a general approach for explaining predictions made by any ML model.
	END:VEVENT

As you can see, the `VCALENDAR` is the overarching email enclosure, and each event begins with a `VEVENT` tag. I think of it as similar to HTML, where you have the <html> tags as the outermost level, encolsing <head> and <body> tags. A [LOT more about it in the iCalendar spec](https://tools.ietf.org/html/rfc2445#section-4.6.1), but that's the basic gist of it. 

The parts of the ical file are:

	NAME;paramters:values
	
	Like so: 

	DTEND;TZID=US/Eastern:20160928T120000


Fortunately,  Python has a great library, `icalendar` [already pre-built with classes to parse ical](
http://icalendar.readthedocs.io/en/latest/usage.html) so you don't have to reinvent the wheel and write lots of regular expressions.  So that's what I ended up using: 

```

'''
Parse .ics file into human-readable text format
Original data from 
http://conferences.oreilly.com/strata/hadoop-big-data-ny/public/schedule/personal
Sorry PEP8
'''

from icalendar import Calendar, Event
from datetime import datetime
import glob, os


# date formats for DTSTART and END
start_format = "%a %b %d %H:%M"
end_format = "%H:%M" #only hour needed for end time


def parse_ics(infile):
	
	cal = Calendar.from_ical(infile.read())

	events = []

	for component in cal.walk('vevent'):
		event =   component.get('summary')
		description =  component.get('description')
		location =  component.get('location')
		start = component.get('dtstart')
		end = component.get('dtend')
		total_time =  "%s-%s" % (start.dt.strftime(start_format) , end.dt.strftime(end_format))
		line =  "Summary:%s \nDescription: %s \nLocation: %s \nTime: %s \n------\n " % (event, description, location, total_time)
		events.append(line)

	return events

def ics_to_file(filename, events):
	with open(filename, 'w') as f:
		for e in events:
			f.write(e.encode('utf-8')) #include correct encoding

def convert_file():
	for file in glob.glob("*.ics"):
		outfilename = os.path.splitext(file)[0]
		infile = open(file, 'rb')
		parsed_results = parse_ics(infile)
		ics_to_file('%s.txt' % outfilename, parsed_results) 

if __name__ == '__main__':
	convert_file()
	
```



The code is pretty straight-forward. It takes all input files in the given active directory and generates output files, named with the same prefix as the input file, but outputting as a text file, that strips the .ics formatting into a more human-readable format. 

The way it does this is by reading in a `Calendar()` object. `Calendar()` has a function called `walk` that loops through each event in the 'vevent' sub-category and fetches the ones with the names I'm interested in (like summary, for example). It then appends each compiled line into a list which is then read into a txt file. 

The only tricky part here is the time. If you just do `component.get('dtstart')`, you get back a special object that looks something like this: `<icalendar.prop.vDDDTypes object at 0x1035d5090>`. That's because dates are special objects in ical and need to be [converted to datetimes](http://stackoverflow.com/questions/20937754/parsing-ical-feed-with-python-using-icalendar) with the .dt function.  

So here are the results for that earlier entry: 

```
Summary:Why should I trust you? Explaining the predictions of machine-learning models 
Description: Presented by Carlos Guestrin (Dato). Despite widespread adoption, machine-learning models remain mostly black boxes, making it very difficult to understand the reasons behind a prediction. Such understanding is fundamentally important to assess trust in a model before we take actions based on a prediction or choose to deploy a new ML service. Carlos Guestrin offers a general approach for explaining predictions made by any ML model. 
Location: Hall 1C 
Time: Wed Sep 28 11:20-12:00 
```

And the end result is an entire, readable, usable text file.  It's no fancy social shared calendar or scheduling app, but it does the job in a pinch. 

All of the [code and raw files are here](https://github.com/veekaybee/strata_schedule). 
