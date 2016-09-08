FROM tomcat

ADD peers.war /usr/local/tomcat/webapps/peers.war
ADD tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml

