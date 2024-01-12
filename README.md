# Spring MySQL API

## Projektöversikt

### Sammanfattning
Det här projektet är en Spring Boot-baserad webbapplikation som hanterar information om länder, städer och väder. länder och städer

### Mål med projektet
1. **Hantera länder och städer:** Ge användare möjlighet att skapa, hämta, uppdatera och ta bort information om länder och städer.
2. **Väderinformation:** Integrera väderinformation för städer.
3. **Användarvänlighet:** Utveckla tjänster för att förenkla datahantering.

## Designbeslut

1. **Mappar och struktur:** Jag har valt att ha Weather och ApiResponse i Utilitys mappen då de inte är Entitys som ska in i databasen. Jag har valt att ha en separat mapp för utilitysTest
för att ha bättre översikt och struktur vilket kan vara bra om man skulle ha många fler klasser men vill utveckla någon mer Utility så vet man var testerna finns instället för att leta fram de bland de andra.


2. **Spring Boot:** bra för att snabbt och enkelt utveckla Spring-baserade applikationer.

- **Spring Boot DevTools:** Utvecklarverktyg som ger en förbättrad utvecklingsupplevelse.
- **Spring Web (Spring MVC):** Används för att bygga webbapplikationer
- **Spring Data JPA:** Används för att hantera data i SQL-databaser med hjälp av Java Persistence via Spring Data och Hibernate.
- **MySQL Driver:** JDBC-drivrutin för MySQL-databasanslutning.
- **Spring REST Docs:** Används för att dokumentera RESTful-tjänster.


2. **MySQL-databas:** MySQL databas som passar bra med Spring och Hibernate.

3. **Maven:** Maven används för att hantera projektets beroenden och byggprocess.


## UML & Modeller

**Klassdiagram - IntelliJ :** Jag har valt att testa göra ett klassdiagram i IntelliJ det verkar vara ett bra verktyg för att förstå strukturen.

![IntelliJ](https://github.com/Distansakademin/spring-weather-api-Arinsz/blob/development/Spring-weather-api-IntelliJClassdiagram-UML.jpg)

___ 

- **UML-EdrawMax:**  Jag har även gjort flera olika UML diagram  

- **Package diagram:** visar de olika paketen (packages) i mitt projekt och hur de är organiserade.
- **Use Case diagram:** beskriver de olika användningsfallen eller scenarierna i min applikation,  Det visar hur olika aktörer (användare eller andra system) interagerar med systemet för att uppnå specifika mål
- **Class diagram:** Klassdiagrammet visar de olika klasserna och deras relationer.

![EdrawMax](https://github.com/Distansakademin/spring-weather-api-Arinsz/blob/development/Spring-weather-api-Edraw-UML.jpg)

___


## Problem och Lösningar

### Problem
1. **Docker:** Docker var svårt då jag försökte ha igång det samtidigt som jag var ansluten till MySql, jag lyckades få till det med dockercompose docker build --network men var osäker på om det faktiskt fungerade eller om det endast var nätverket som visades i docker appen. 
2. **Undantagshantering:** Behovet av att hantera olika typer av undantag och kommunicera tydliga felmeddelanden till användare. Jag skapade en ApiResponse för detta och det var ganska utmanande då den behövdes i många sammanhang. 
3. **Warnings:** fick en del warnings och fel som jag hade svårt att förstå då informationen inte är lika specific i vart felet ligger som i vanliga TDD tester. 
4. **UML-Diagram:** Har googlat en del och kollat youtube videos, har fortfarande lite svårt att avgöra vilket håll pilarna ska riktas åt baserat på vad, men antar att det är en sak man lär sig av erfarenhet ganska snabbt.  
5. **Cucumber:** Jag försökte få cucumber att fungera med mitt projekt men fick null värden, jag tror det beror på att den inte vet att Databasen finns.
6. **ManyToOne&OneToMany:** Detta var lite svårt att få till i början, när man väl hittas lösningen så är det ganska självklart. Men dessa passade bra i City och Country med tanke på deras relation. 

### Lösningar
1. **Docker:** host.docker.internal:3306 när jag vill bygga och ansluta docker container. localhost:3306/spring_mysql_docker när jag vill bygga och ansluta via MySql.
2. **Undantagshantering:** Det släppte efter ett tag och det blev enklare att förstå logiken.
3. **Warnings:** Jag hade en bra inställning till det och tänkte att detta är bra att vänja sig vid och att utmanande att lösa själv.
4. **UML-Diagram:** Tog hjälp av IntelliJ class diagram för att förstå strukturen och vilket håll pilarna ska åt osv. Mer djupgående sökning av olika termer inom UML diagram. 
5. **Cucumber:** Jag gjorde ett exempel som inte hade med mitt projekt att göra. Jag försökte ett antal timmar att få det att fungera med mitt projekt utan framsteg. 
6. **ManyToOne&OneToMany:** Googlade och sökte mer för att förstå mer djupgående vad de gör och i vilka fall det är bra att använda de.

**Erfarenhet:** För mig är erfarenheten jag får när jag stöter på problem och hinder något jag värdesätter och ofta tänker på medans jag kodar. Jag känner att den inställningen har hjälpt mig mycket i detta projekt. 

## Förbättringspotential

1. **Säkerhet:**.
2. **Logging:**
3. **Frontend-Applikation:** koppla den med min Spring Boot-applikation via de API-endpoints jag skapat. Alternativt skapa något som liknar en miniräknare men där det står t.ex Create city på en knapp och att resultaten visas en ruta. 








---



# Spring Boot REST API - Countries and Cities

## Base URL

- http://localhost:8080/

## Countries Resource

- **Create Country:** `POST` - http://localhost:8080/api/countries/create
- **Read (one Country):** `GET` - http://localhost:8080/api/countries/{id}
- **Read (all Countries):** `GET` - http://localhost:8080/api/countries/getAll
- **Update Country:** `PUT` - http://localhost:8080/api/countries/{id}
- **Delete Country:** `DELETE` - http://localhost:8080/api/countries/{id}
- **Get All Cities in Country:** GET - http://localhost:8080/api/{country}/cities

## Cities Resource

- **Create City:** `POST` - http://localhost:8080/api/cities/create/{CountryId}
- **Read (one City):** `GET` - http://localhost:8080/api/cities/{id}
- **Read (all Cities):** `GET` - http://localhost:8080/api/cities/getAll
- **Update City:** `PUT` - http://localhost:8080/api/cities/{id}
- **Delete City:** `DELETE` - http://localhost:8080/api/cities/{id}

## Weather Resource

- **Get Weather by City ID:** `GET` - http://localhost:8080/api/weather/{city_id}

## Database Configuration

- Database URL: jdbc:mysql://localhost:3306/spring_mysql_docker
- Username: root
- Password: root
- Driver Class: com.mysql.cj.jdbc.Driver


### How to Test API Endpoints

- Use tools like Postman to send HTTP requests to the provided endpoints.
- Refer to the API documentation for each endpoint for request and response details.
