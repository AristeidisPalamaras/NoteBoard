GENERAL INFORMATION

NoteBoard is a message board web application, with the flavor of a Post-it-like note-taking app. It allows end-users to 
create and manage groups, and to post or delete brief messages (up to 240 characters) to their groups.

This project has been developed for Coding Factory 6, Athens University of Business and Economics.


TECHNOLOGY STACK

NoteBoard has been developed with a RESTFul architecture.

The back-end of the application has been built with Java, SpringBoot, Gradle and MySQL.
The front-end of the application has been built with JavaScript/TypeScript and Angular.


BUILD & DEPLOYMENT

1. Create a database Schema with a name of your choice, or the default name "noteboardDB". Save the name of the schema 
   for later.

2. Create a database User with a name of your choice, or the default name "noteboardAdmin". Save the name and the 
   password of the user for later.

3. Grant the new user privileges (all privileges) for the new Schema.

4. If you have named the database Schema with the default name "noteboardDB", skip to the next step.
   Otherwise, create an Environment Variable "MYSQL_DB" with the value "yourDatabaseSchema".

5. If you have named the database User with the default name "noteboardAdmin", skip to the next step.
   Otherwise, create an Environment Variable "MYSQL_USER" with the value "yourDatabaseUser".

6. Create an Environment Variable "MYSQL_PASSWORD" with the value "yourDatabasePassword".

7. Generate a 48 bytes key with base64URL enconding and store it at the Environment Variable "SECRET_KEY".
   (FOR TESTING ONLY. For convenience, copy the following key to the Environment Variable "SECRET_KEY": 
   "5ce98d378ec88ea09ba8bcd511ef23645f04cc8e70b9134b98723a53c275bbc5")

8. Build/run the application.

   Linux/macOS (Bash)

   Build with:
   $ ./gradlew clean build

   Run with:
   $ java -jar build/libs/NoteBoard-0.0.1-SNAPSHOT.jar

   ALTERNATIVE
   Build and run with:
   $ ./gradlew bootRun

   Windows (PowerShell)

   Build with:
   $ gradlew clean build

   Run with:
   $ java -jar build/libs/NoteBoard-0.0.1-SNAPSHOT.jar

   ALTERNATIVE
   Build and run with:
   $ gradlew bootRun

9. After step 8, the database should have been set up. However, you should also set up database indexes as follows:
   a. Navigate to src/main/java/resources/
   b. Edit the file application-stage.properties.
   c. Un-comment the following code (lines 17-18):
           # spring.sql.init.mode=always
           # spring.sql.init.data-locations=classpath:sql/index.sql
   d. Build and run the application again (i.e. repeat step 8).
   e. Go back to the file application-stage.properties and re-comment the code from step 9c.

10. OPTIONAL. After step 9, the database and indexes should have been fully set-up. You can additionally add some 
    pre-constructed data to the database for testing/demonstration purposes as follows:
    
    a. Navigate to src/main/java/resources/
    b. Edit the file application-stage.properties.
    c. Un-comment the following code (lines 26-27):
            # spring.sql.init.mode=always
            # spring.sql.init.data-locations=classpath:sql/demo/users.sql,classpath:sql/demo/groups.sql,classpath:sql/demo/members.sql,classpath:sql/demo/messages.sql
    d. Build and run the application again (i.e. repeat step 8).
    e. Go back to the file application-stage.properties and re-comment the code from step 10c.

    ALTERNATIVE
    a. Navigate to src/main/java/resources/sql/demo/
    b. Open the file all.sql. This file contains all the demo data for the various tables of the database in the correct 
       order (INSERT INTO users > INSERT INTO groups > INSERT INTO group_user > INSERT INTO messages)
    c. If you have named your database Schema with a name of your choice, replace "noteboardDB" with "yourDatabaseSchema" 
       at the first line of all.sql:
            USE noteboardDB;
    d. Copy and execute the sql script from all.sql in your GUI or CLI database manager.

    NOTE
    Step 10 inserts into the database 5 users with usernames "user1@example.com", ..., "user5@example.com". All users 
    have the password "@Password123". These credentials can be used to log-in the application (but don't be surprised if 
    you log in as user5@example.com and not find any data - this user has intentionally been left as a blank state).

11. Make sure that both
            # spring.sql.init.mode=always
            # spring.sql.init.data-locations=classpath:sql/index.sql
    and
            # spring.sql.init.mode=always
            # spring.sql.init.data-locations=classpath:sql/demo/users.sql,classpath:sql/demo/groups.sql,classpath:sql/demo/members.sql,classpath:sql/demo/messages.sql
    in application-stage.properties are commented out.

    Build and run the application one last time (i.e. repeat step 8).

12. All done! Visit the application at:
            http://localhost:8080/

    Visit the REST API documentation at:
            http://localhost:8080/swagger-ui/index.html#/


The above steps deploy the back-end of the application with an integrated front-end. For separate deployment of the 
back- and front-end follow these steps:

a. Delete the contents of src/main/resources/static/
b. Download the front-end from:
            https://github.com/AristeidisPalamaras/NoteBoardFrontEnd
c. Build and deploy the back-end following the same instructions as above.
d. Build the front-end with:
            $ ng serve
e. Visit the application at:
            http://localhost:4200/


TODO

- The back-end of the application implements functionality for posting reactions to messages, but this functionality has 
  not been implemented in the front-end.
- Currently, the application does not implement functionality to allow users to manage their status as group members 
  (e.g. to accept/decline invitations to join groups, to leave groups, etc.)

