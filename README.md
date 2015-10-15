# SparkyJava

This is using Java 1.7 and SparkJava 1.1.1

This is a demo for simple Web RESTful service.

/helloworld

/helloworld/{input name}

/createAPost

/listAllPosts

/* How to Launch the WebService? */
~$ mvn clean install
~$ mvn exec:java -Dexec.mainClass="com.myang.App"

It will launch Jetty with port 4567  
