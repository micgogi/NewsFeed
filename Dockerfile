FROM tomcat:8-jre8-slim
COPY services/target/NewsFeedSearch.war /usr/local/tomcat/webapps
COPY angular/dist/NewsSearch/. /usr/local/tomcat/webapps/NewsSearch
