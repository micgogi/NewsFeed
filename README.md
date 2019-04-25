# Introduction
This is a boiler plate project for FSD Certification Practice Check. Admin can search and blacklist a news analyst. A logged in user can search for news. For each user, the system maintains the historical list of keywords searched. In the home screen the news related to the search keywords are displayed under relevant headings. Users can view the topics searched and can delete the searched topics. Fork this project and create the below specified issues in the forked project.

| **Issue Title** | **Issue Description** |
|-----------|-------------------|
| 1. Implement the functionality of the application | Create a website for users to signup and search new articles in a lively manner. Refer detailed description below this table. |
| 2. Analysis and Design | Define Screen Layout, ER Diagram, Classes and Method signatures. Include the documentation in README.md section of the project. |
| 3. Implement Repository and Service Layer | Create database and implement Service Layer using Hibernate. Unit Testing of the service method should be done using Mockito. Document the steps to build, unit test and deploy in Wiki. |
| 4. Implement Rest Controller | Create the Restful Web Service Controller using Spring MVC and create end to end tests using MockMvc library available in Spring. Document the steps to build, unit test and deploy. |
| 5. Implement Authentication Service | Modify the test cases based on inclusion of Authentication. |
| 6. Implement CI/CD | Automate the deployment of WAR using Jenkins. |
| 7. Implement Front End and consume Rest Services | Implement front end using Angular with responsive web design. Implement Unit Testing using Karma. Implement end to end testing with Protractor. |
| 8. Document the steps for build and deployment | Create a subheading for this in README.md and include the steps to deploy. |
| 9. Create docker compose for this application | |

# Application Functionality in detail (Include this in issue description of forked project)

## Role: News Analyst
1. Signup
2. Login
3. Search news articles after login (use https://newsapi.org to retrieve live new data.)
4. View the news related to the topics in search topic
5. List the searched topics
6. Remove topics from the search list
7. Logout

## Role: Admin
1. Login
2. Search News Analyst
3. Blacklist News Analyst
4. Logout


## Software Required
1. jdk.1.8+
2. Tomcat Server v8+
3. Git
4. MySql 5+

## Get The Project From GitLab
1. Create a folder in your pc/laptop/mac.
2. Go inside the folder and right click inside the folder.
3. Select "Git Bash".
4. Run following commands one by one:
        `git config --global user.name "[FIRST NAME] [LAST NAME]"
        git config --global user.email "[COGNIZANT EMAIL ID]"
        git clone https://code.cognizant.com/730058/newsfeed-search-app.git`.
       

## To Start Database
1. In the cloned project open database folder.
2. Execute the script file in  signup.sql.
3. You will get the database;

## To start the Service
1. Open the folder service and run terminal with command "`mvn clean package`".
2. Then copy the .war file and paste into tomcat/webapps directory.

## To start the Angular
1. Open the folder angular and go into the project.
2. Open terminal and run the command "`npm install, ng build --prod --base-href=`"project name.

## To run test cases
1. For Karma Test run with command in terminal
      `"ng test"`
2. To run Protractor run with command in terminal
      `"ng e2e"`
3. To run MockMvc and Mokito 
        right click on the test cases and run as Junit test

## Start the Tomcat server
1. Admin login => username=admin123
                 password=admin12345678
2. User login =>username=rahul password=123456789
