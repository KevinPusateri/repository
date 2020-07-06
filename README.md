# PSMSProject


1. Create a maven web project
2. Integrate within the project, with appropriate maven plugins for builds, a JSP Web application for the front-end
3.  Prepare unit tests with Junit
4 Run the tests, debug the tests, positioning the breakpoint on the assertion instruction
5 Run the test via maven 3.5+ both via IDE and via command line

#### Prerequisites
Architettura |
------------ |
Oracle JDK 8 |
Eclipse IDE |
Apache Tomcat 9.0 |
Oracle Express 11g |
Oracle SQL Developer |


#### Installing
dependency maven |
------------ |
junit 4 |
javax.servlet |
javax.servlet.jsp |
org.mockito |
org.apache.tomcat |
jstl |
taglibs |
com.oracle.database.jdbc |
net.sourceforge.jwebunit |
org.slf4j |
log4j |

#### Ruoli
Kevin | Jeffrey
------------ | -----------
Creazione classi java per gli utenti e la servlet per la loro gestione  | Si occuperà di oracle Sql sulla creazione delle tabelle e query


#### Running the tests
Test the web project via Jwebunit the servlet and CRUD applications with asserts.
Run the test via maven 3.5+ via command line: mvn clean install.

#### Built With
* Maven - Dependency Management
