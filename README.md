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
dependency maven | motivazione
------------ | -------------
junit 4 | testare servlet
javax.servlet | utilizzo della servlet
javax.servlet.jsp | utilizzo dei file jsp
org.mockito | testare servlet con l'utilizzo del mock
org.apache.tomcat | connessione al server per le pagine web
jstl | utilizzo di una raccolta di tag JSP
taglibs | per identificare i tag personalizzati nella pagina JSP
com.oracle.database.jdbc | per la connessione a oracle sqlServer
net.sourceforge.jwebunit | per testare in junit le pagine jsp
org.slf4j | libreria per interfacciarsi ai sistemi di logging Java
log4j | per l'utilizzio di Logger

#### Ruoli
Kevin | Jeffrey
------------ | -----------
Creazione e sviluppo di classi java per gli utenti e la servlet per la loro gestione  | Si occuperà di oracle Sql sulla creazione delle tabelle e query e del DB di test
Documentazione delle scelte decise insieme e dei passi svolti | Creazione e sviluppo di | Creazione e sviluppo del progetto front-end: form e liste.

Passi svolti
- Kevin:
  - Aggiunto classe User.java
  - Aggiunto Servlet UserController.java per l'utente
  - Aggiunto DaoUser per salvare, eliminare, aggiornare e trovare l'utente
- Jeffrey
  - Aggiunto tabella User su SqlServer e i suoi campi
  - Aggiunto userForm.jsp per registrare l'utente

#### Running the tests
Test the web project via Jwebunit the servlet and CRUD applications with asserts.
Run the test via maven 3.5+ via command line: mvn clean install.

#### Built With
* Maven - Dependency Management
