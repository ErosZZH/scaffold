#!/bin/bash
shutdown.sh
mvn package
rm -rf /Users/user/dev/tomcat7/webapps/scaffold*
\cp -r /Users/user/Desktop/lib/scaffold/rz-web/target/scaffold.war /Users/user/dev/tomcat7/webapps
startup.sh
