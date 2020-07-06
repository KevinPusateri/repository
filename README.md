# PSMSProject

Abbiamo deciso di utilizzare il progetto PSMS web convertito in maven.

#### Prerequisiti
Architettura |
------------ |
Oracle JDK 8 |
Eclipse IDE |
Apache Tomcat 9.0 |
Oracle Express 11g |
Oracle SQL Developer |

#### Installazioni
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
Creazione e sviluppo di classi java per gli utenti e la servlet per la loro gestione  | Creazione delle tabelle, query e del DB di test
Documentazione delle scelte decise insieme e dei passi svolti | Creazione e sviluppo del progetto lato front-end(html, jsp): form e list.

#### Modalità di meeting
Ci si incontra ogni 1 ora su skype per verificare i passi svolti e adottare decisioni o correzioni di eventuali problemi 

#### Passi svolti
- Kevin:
  - Aggiunto classe User.java
  - Aggiunto Servlet UserController.java per la gestione dell'utente
  - Aggiunto DaoUser per salvare, eliminare, aggiornare e trovare l'utente
- Jeffrey
  - Aggiunto tabella User su SqlServer e i suoi campi
  - Aggiunto userForm.jsp per registrare l'utente

#### Running the tests
Testare il progetto Web tramite Jwebunit, la servlet e le applicazioni CRUD con l'utilizzo degli assert.
Eseguire il test tramite maven 3.5+ tramite riga di comando: mvn clean install.

#### Built With
* Maven - Dependency Management
