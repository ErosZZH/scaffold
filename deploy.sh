#!/bin/bash
shutdown.sh
mvn package
rm -rf /home/eros/dev/apache-tomcat-7.0.42/webapps/scaffold
\cp -r /home/eros/workspace/scaffold/target/scaffold /home/eros/dev/apache-tomcat-7.0.42/webapps
startup.sh
