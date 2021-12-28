+++
date = '2016-03-29T00:00:00Z'
title = 'Starting to dig inside Spark RDDs'

+++



<center><a data-flickr-embed="true" data-footer="true"  href="https://www.flickr.com/photos/hercules/118434343/in/photolist-bt1pr-89UwDH-9akri9-8YSpJ2-4GTBnP-8BP2mK-9EnHki-6khT2h-3SoPV-Gr8dh-bkxNw-6S7ZU6-aawPj8-9ukeeu-23Sn4V-3tv3K-dCkZHq-jARz6-qrMd4B-3E4S9-c19HXh-nBxQTX-gbHrM-dAi2QK-h4pvt-4kEXKq-bFSQwz-rq6Dmf-5m8DM-dQaj3N-b7kzun-5EGVfU-5KJ8N8-c19HWL-LGyRi-eeeoEa-4B9FH3-c19HVW-aDsteq-pjQpwy-3ihd-avFpRE-bhAPWz-bsXZMy-c19JQL-4CrD7i-a6GeAe-57j8-3K3ydo-3GPbA" title="Mechanic"><img src="https://farm1.staticflickr.com/19/118434343_f439fe9be2_m.jpg" width="240" height="181" alt="Mechanic"></a><script async src="//embedr.flickr.com/assets/client-code.js" charset="utf-8"></script> </center>


A lot of the Spark documentation has examples using either the Scala `spark-shell` or the Python `pyspark` repl. These are really great to test out code fragments, especially for Scala, which needs to be packaged. 

So you get a lot of examples [like this](http://spark.apache.org/docs/latest/quick-start.html): 

```textFile.map(line => line.split(" ").size).reduce((a, b) => if (a > b) a else b)```

What's the next step to writing production-grade, reproducible apps? 

Let's say we have some log data in HDFS already:  Using some fake log data I generated with [this excellent script](https://gist.github.com/gwenshap/11390102):
	
Setting up the file for Spark analysis: 

	hdfs dfs -mkdir /logdata
	hdfs dfs -put access_log_20160329-153657.log /logdata
	hdfs dfs -cat /logdata/access_log_20160329-153657.log | tail -n 1
	
	16.180.70.237 - - [10/Oct/2013:23:25:41 ] "GET /handle-bars HTTP/1.0" 200 2780 "http://www.casualcyclist.com" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1944.0 Safari/537.36"

And let's say we want to count the number of times each IP appears. 

In Python, it's relatively easy to write apps. 

The steps are:

1. Write your .py file. 
2. Run on the command line
3. Spend the rest of your time relaxing on the beach. 

```python

import sys
from pyspark import SparkContext
from pyspark import SparkConf

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print >> sys.stderr, "Usage: IPs <file>"
        exit(-1)

sc = SparkContext()

logs = sys.argv[1]

logs = sc.textFile("/logdata/access_log_20160329-153657.log")
iplines = logs.map(lambda lines: (lines.split(' ')[0],1)).reduceByKey(lambda v1,v2 :v1+v2)

for i in iplines.take(10):
	print "IP:" , i[0], "Count:", i[1]


sc.stop()
```
and then:

```spark-submit --master yarn-client  countIp.py /logdata/*``

In Scala, it's a little more complicated: 

1. Write your Scala code
2. [Build the correct POM](https://sparktutorials.github.io/2015/04/02/setting-up-a-spark-project-with-maven.html) (not going to cover this in this post)
3. Compile with Maven
4. Run compiled job in the command line
5. Weep

OR

2. Build with SBT. 
3. Run compiled job in the command line
4. Weep

The root file: 

``` scala
//whatever you want to run this file as
package name

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object CountIPs {
   def main(args: Array[String]) {
     if (args.length < 1) {
       System.err.println("Usage: CountIPs <file>")
       System.exit(1)
     }
    //Parse log files from HDFS to get IP, count of IPs
	var logs = sc.textFile("/logdata/access_log_20160329-153657.log")
	var iplines = logs.map(lines => (lines.split(' ')(0),1)).reduceByKey((a, b) => a + b)

	//print out to console
	for (pair <- iplines.take(10)) {
   printf("IP: %s, Count: %s\n",pair._1,pair._2)
}



	sc.stop
	}
  }	
  ```

And the cli options: 

`spark-submit \--class name.CountIps \--master yarn-client \ target/countIps-1.0.jar /logdata/*`


While working through the Scala example, I learned something that was interesting to me as someone who is new to Scala, coming from a Python background, related to var/val assignment. 

Scala has several basic language variable [declarations](http://www.scala-lang.org/files/archive/spec/2.11/04-basic-declarations-and-definitions.html): 

	Dcl      ::=  ‘val’ ValDcl
              |  ‘var’ VarDcl
              |  ‘def’ FunDcl
              |  ‘type’ {nl} TypeDcl

several of which are particularly of interest to Spark users: `val` and `var` .

`val` allows for the creation of immutable (i.e. read-only) references and `var` references can be overwritten.  

Spark RDDs by nature are [immutable](https://spark.apache.org/docs/0.8.1/api/core/org/apache/spark/rdd/RDD.html), and documentation and best practices have them assigned to `val` types.

So it surprised me to see some documentation in a recent Spark class I took assigning RDDs to `vars` instead of `vals` within an app that was not reusing RDDs. And inherently, because of the way Spark internals are structured, it doesn't make sense, or at least I haven't yet come across a use case, where you need to change the assignment of one RDD when you can just create a new one through a transformation. 

I looked into why you might ostensibly use var over val within the context of Spark's immutable RDD paragigm, and didn't find anything specific, until I saw this note in Advanced Analytics with Spark: 

 ![image](https://raw.githubusercontent.com/veekaybee/veekaybee.github.io/master/images/varval.png)

So, basically it's ok when you're writing throw-away commands in the REPL where it doesn't make much of a difference. 

Digging through [the Spark documentation](https://community.hortonworks.com/questions/18708/are-spark-rdd-really-mutable.html), as well as  [the Spark mailing list](https://mail-archives.apache.org/mod_mbox/spark-user/201602.mbox/%3CCALte62wXf5jSQUpzsr=zYayw0D-L5+tPVONE7fqsdnC=Ne59cQ@mail.gmail.com%3E) (an excellent resource for learning Spark) has led me to believe that using var in Spark is an anti-pattern in Scala apps. 
	
>Although individual RDDs are immutable, it is possible to implement mutable state by having multiple RDDs to represent multiple versions of a dataset. 






	

