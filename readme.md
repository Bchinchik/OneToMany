## Using MySQL in Spring Boot via Spring Data JPA and Hibernate

See here for more informations:
http://blog.main.com/2014/10/27/using-mysql-in-spring-boot-via-spring-data-jpa-and-hibernate/

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations.

#### Prerequisites

- Java 8
- Maven > 3.0

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.


### Usage

- Run the application and go on http://localhost:8080/
- Use the following urls to invoke controllers methods and see the interactions
  with the database:
    * `/create?name=[name]&pass=[pass]`: create a new user with an auto-generated id and name and pass as passed values.
    * `/delete?id=[id]`: delete the user with the passed id.
    * `/get-by-name?name=[name]`: retrieve the id for the user with the passed name address.
    * `/update?id=[id]&name=[name]&pass=[pass]`: update the name and the pass for the user indentified by the passed id.
