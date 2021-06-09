# The Transaction Analyser

To build it, you will need to download and unpack the latest (or recent) version of Maven
and put the mvn command on your path. Then, you will need to install a Java 1.8 (or higher) JDK (not JRE!),
and make sure you can run java from the command line. Now you can run mvn clean install
and Maven will compile your project, an put the results it in a jar file in the target directory.

How you run this code is up to you, but usually you would start by using an IDE like NetBeans, Intellij IDEA, or Eclipse.

# Maven commands

Once you have configured project in IDE you can build it from there.
However if you prefer you can use maven from the command line.
In that case you could be interested in this short list of commands:

* `mvn test`: it will compile the code of application and tests. It will then run tests and let you know if some fails
* `mvn install`: it will do everything `mvn test` does and then if everything looks file it will install the application into your local maven repository (typically under /.m2). In this way you could use this library from other projects you want to build on the same machine