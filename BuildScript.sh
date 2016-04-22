#!/bin/bash

#Install Java 8
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer

#Configure Java Environment
sudo apt-get install oracle-java8-set-default

#Install Tomcat 8
wget http://mirrors.gigenet.com/apache/tomcat/tomcat-8/v8.0.33/bin/apache-tomcat-8.0.33.zip
unzip apache-tomcat-8.0.33.zip
sudo mv apache-tomcat-8.0.33 /opt/tomcat
cd /opt/tomcat/bin
chmod 744 *sh

#Ensure Project445 folder is in HOME/UserName of the test environment.
#Change directory to Project445
cd ~/Project445

cp *.csv /opt/tomcat/bin

mkdir WEB-INF/classes

#Compile source files
javac -cp "WEB-INF/lib/*" -d WEB-INF/classes *.java

#Generate executable
jar cvf ROOT.war WEB-INF

#Unit test coverage information
java -cp WEB-INF/lib/emma.jar emma instr -m overwrite -cp WEB-INF/classes/. -ix -REST_Controller

java -cp "WEB-INF/lib/*":WEB-INF/classes -noverify org.junit.runner.JUnitCore AllTests

java -cp WEB-INF/lib/emma.jar emma report -r txt -in coverage.em,coverage.ec

less coverage.txt 

#Deploy executable
webapps_dir=/opt/tomcat/webapps

rm -rf $webapps_dir/ROOT

cp ROOT.war $webapps_dir

cd /opt/tomcat/bin

./startup.sh


