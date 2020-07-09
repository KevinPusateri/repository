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
org.skyscreamer | per l'utilizzo del Json

#### Ruoli
Kevin | Jeffrey
------------ | -----------
Creazione e sviluppo di classi java per gli utenti e la servlet per la loro gestione  | Creazione delle tabelle, query e del DB di test
Documentazione delle scelte decise insieme e dei passi svolti | Creazione e sviluppo del progetto lato front-end(html, jsp): form e list.
Predisporre un test Junit per il GetJson dell'utente | Predisporre test Junit per la UserServletTest con l'utilizzo di Mockito

#### Modalità di meeting
Ci si incontra ogni 1 ora su skype per verificare i passi svolti e adottare decisioni o correzioni di eventuali problemi 

#### Passi svolti
- Kevin
  #### 6/07/2020
  - Aggiunto classe User.java
  - Aggiunto Servlet UserController.java per la gestione dell'utente
  - Aggiunto DaoUser per salvare, eliminare, aggiornare e trovare l'utente
  #### 7/7/2020
  - Run/Debug DaoUser CRUD:  insert, delete, list degli utenti completati
  - Correzione classe User TimeStamp
  - Correzione campo date userForm.jsp
  #### 8/7/2020
  - Creazione e sviluppo JsonConverteUser.java
  - Aggiunto metodo getJson() nella Servlet per ottenere oggetto json dell'utente
  - Aggiunto metodo findUser() su DaoUser per trovare utente specifico da controllare
  - Cambiato campo Date in birthDate
  #### 9/7/2020
  - Corretto select option di userForm per fare Edit dell'utente
  - Aggiunto test che verifichi la presenza della tabella nel database
  - Aggiunto test che verifichi la tabella non sia vuota
  - Aggiunto metodo getJsonArray nel controller per avere tutti gli utenti nel json
  - Documentazione su PrimeUI
- Jeffrey
  #### 6/07/2020
  - Aggiunto tabella User su SqlServer e i suoi campi
  - Aggiunto userForm.jsp per registrare l'utente
  #### 7/7/2020
  - Aggiunto userList.jsp
  - Modifica del menu aggiungendo tab per registrare utente e visualizzare la lista
  - Inizio documentazione su Json in rete
  #### 8/7/2020
  - Controllo su userForm l'option select per l' update utente
  - Aggiunto libreria org.skyscreamer per utilizzare il Json
  - Creazione file login.jsp
  - Creazione UserServletTest per testare su Junit lo UserController.java
  - Completato UserServletTest per testare in Junit
  #### 9/7/2020
  - Completato style Form Login.jsp
  - Creazione tabelal Login con i seguenti campi: id, username, password, idUser
  - Inizio gestione del login dallo UserController
#### Running the tests
Testare il progetto Web tramite Jwebunit, la servlet e le applicazioni CRUD con l'utilizzo degli assert.
Eseguire il test tramite maven 3.5+ tramite riga di comando: mvn clean install.

#### Built With
* Maven - Dependency Management
