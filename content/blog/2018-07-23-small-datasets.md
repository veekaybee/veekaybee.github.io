+++
date = '2018-07-23T00:00:00Z'
title = 'Good small datasets'

+++


<meta name="twitter:card" content="summary">
<meta name="twitter:site" content="@vboykis">
<meta name="twitter:creator" content="@vboykis">
<meta name="twitter:title" content="Good, small datasets">
<meta name="twitter:description" content="I asked Twitter for help finding datasets that weren't iris.">
<meta name="twitter:image" content="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/chessplayers.png">


<img src="https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/chessplayers.png" alt="drawing" width="300px"/>
John Lavery, The Chess Players (1929)<br><br>


I've been working on a project that, like most projects, requires testing with a dataset. 

My personal criteria are: 

+ Relatively small size (Less than 100 KB, or 100ish rows)
+ At least 5-6 features (columns)
+ Should have both numerical and text-based features
+ Ideally a range of different kinds of numbers
+ Has good documentation
+ Is open and available to the public
 + Relatively available for both R and as individual CSV files or Python imports (APIs and download portals count-ish)
+ Isn't overly morbid (i.e not related to cancer, mortality, or murder, etc.) 

Normally, I'd use mtcars or iris, but I've been a bit tired of both lately, so I asked Twitter for suggestions. 

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">Stats/data people: Tired of iris and mtcars? Tell me about your favorite heterogenous, small dataset! (I.e. has both numerical and text-value columns), is ideally smaller than 500 rows or so, is interesting to work with.</p>&mdash; Vicki Boykis (@vboykis) <a href="https://twitter.com/vboykis/status/1021191402787692545?ref_src=twsrc%5Etfw">July 23, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

I got a lot of good answers, so I thought I'd share them here for anyone else looking for datasets. 

Quick note: 
Attributes = features or columns
Observations = Rows 

+ [Forest Fires](https://archive.ics.uci.edu/ml/datasets/Forest+Fires) - A dataset examining factors that lead to forest fires in Northeast Portugal. Includes weather factors and categorical variables like days of the week. 13 attributes, 517 observations. 
+ [Emergency Room Visits](https://www.cpsc.gov/Research--Statistics/NEISS-Injury-Data/) - 20 years of select emergency room visit data, by sex and age. Has a lot of data that you can limit your query to with a SQL-like query builder, but can be cumbersome to build a query. Available via [R package here.](https://github.com/hadley/neiss) 
+ [Pigeon Racing](https://pigeon-ndb.com/) - If you've ever wanted to know how fast pigeons are, you're in luck. The national Pigeon Racing Database is here for you. Data from 2010-2018, includes the pigeon classifying number (?), the owner, and several more attributes. It can be hard to get this data out because there's no direct CSV export. 
+ [Street Price of Marijuana](https://github.com/frankbi/price-of-weed) A lot of CSV files of marijuana street prices over time. 
+ [RDataSets](https://vincentarelbundock.github.io/Rdatasets/datasets.html) - An enormous compendium of datasets that shows both their R package and has a correpsonding CSV file. The site also shows whether the datasets have numberic, binary, or character inputs. Includes datasets like population of US cities, Car Speeding and Warning Signs, Weight Data for Domestic Cats, Canadian Women's Labour-Force Participation, and Egyptian Skulls. 
+ Star Wars Characters Database - [As an API](https://swapi.co/) and [as an R package](https://dplyr.tidyverse.org/reference/starwars.html) - Includes height, weight, birth date, and several other attributes for characters from the movies. Good for text analysis.  13 features,  87 observations
+ [120 years of Olympic History](https://www.kaggle.com/heesoo37/120-years-of-olympic-history-athletes-and-results) - Athletes and results. Mostly text-based, with some numerial columns, available as a CSV file.  15 attributes, 271116 rows - Can be made smaller through Kaggle. 
+ [US Census at School](http://ww2.amstat.org/censusatschool/Randomsampleconditions.cfm?CFID=55372463&CFTOKEN=8a531ab99cd68228-E155338C-155D-0261-04DF0BCA4BC7D12E&jsessionid=8430d6242925ef932b4f546b7d3b4135241d) - Random sample of anonymized students and teachers in American schools based on selection by state, years from 2010-2018, selectable by sample size of 50-1000. Has features like height, language spoken, travel time to school, and text messages received. 
+ [Spotify Music Classification Dataset]( https://www.kaggle.com/geomack/spotifyclassification) - A dataset built for a personal project based on 2016 and 2017 songs with attributes from Spotify's API. 16 attributes, ~1000 rows.

Other resources: 
+ A whole [newsletter of datasets](https://tinyletter.com/data-is-plural), including ones like Wikipedia edits, most popular government webpages, and a database of glaciers. 


Suggestions/Comments either on Twitter or as a [pull request](https://github.com/veekaybee/veekaybee.github.io) are welcome!


