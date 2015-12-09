#!/bin/bash
shutdown.sh
mvn clean package
rm -rf /home/eros/dev/apache-tomcat-7.0.42/webapps/scaffold*
cp /home/eros/workspace/scaffold/target/scaffold.war /home/eros/dev/apache-tomcat-7.0.42/webapps
startup.sh
