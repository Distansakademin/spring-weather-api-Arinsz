# Spring MySQL API



## Projektöversikt

### Sammanfattning

Det här projektet är en Spring Boot-baserad webbapplikation som hanterar information om länder, städer och väder. länder och städer.
En viktig aspekt av projektet är användningen av Docker för att underlätta hanteringen av både applikations- och databasmiljön.
Genom att containerisera applikationen med Docker kan man köra applikationen utan att oroa sig för kompatibilitetsproblem.

### Mål med projektet

1. **Hantera länder och städer:** Ge användare möjlighet att skapa, hämta, uppdatera och ta bort information om länder och städer.


2. **Väderinformation:** Integrera väderinformation för städer.


3. **Användarvänlighet:** Utveckla tjänster för att förenkla datahantering.


4. **Continuous Integration:** Använda Github Actions för att automatiskt köra tester.


5. **Docker:** för att containerisera applikationen.


6. **UML:** skapa olika UML diagram för att modellera applikationen.


7. **BDD, TDD:** användarberättelser och BDD-testscenarion. TDD för att skapa JUnit-testfall.


8. **Exception handling** felhantering av kod (Apiresponse)


9. **MySql** använda MySQL som databas för att möjliggöra interaktion mellan Restcontrollers och lagrad data.

## Designbeslut

1. **Mappar och struktur:** För att strukturera koden och öka översikten har jag valt att placera klasser som inte är direkta entiteter i databasen, såsom Weather och ApiResponse, i mappen "Utilitys". Det underlättar för utvecklare att hitta och hantera icke-entitetsklasser. Dessutom har jag skapat en separat mapp, "utilitysTest", för testklasser relaterade till utility-klassen. För att underlätta framtida utveckling och underhåll, särskilt om fler utility-klasser läggs till i projektet.



2. **ApiResponse:** ApiResponse-klassen används för felhantering där man kan inkludera specifika felmeddelanden vid kommunikation med databasen. Det gör det lättare att skicka information tillbaka till klienten och underlättar felsökning.


3. **MySQL-databas:** MySQL databas som passar fungerar bra med Spring och Hibernate.


4. **Maven:** Maven används för att hantera projektets beroenden och byggprocess.


5. **Spring Boot:** bra för att snabbt och enkelt utveckla Spring-baserade applikationer.

- **Spring Boot DevTools:** Utvecklarverktyg som ger en förbättrad utvecklingsupplevelse.
- **Spring Web (Spring MVC):** Används för att bygga webbapplikationer
- **Spring Data JPA:** Används för att hantera data i SQL-databaser med hjälp av Java Persistence via Spring Data och Hibernate.
- **MySQL Driver:** JDBC-drivrutin för MySQL-databasanslutning.
- **Spring REST Docs:** Används för att dokumentera RESTful-tjänster.

---

## Databasstruktur

### Country
- `id` (Long): Unik identifierare för landet.
- `countryName` (String): Namnet på landet.
- `cities` (List<City>): En lista med städer som tillhör landet.

### City
- `id` (Long): Unik identifierare för staden.
- `cityName` (String): Namnet på staden.
- `country` (Country): Referens till landet som staden tillhör.

### Weather
- `condition` (String): Väderförhållandet (t.ex., Sunny, Cloudy, Rainy).
- `temperature` (int): Temperaturen för vädret.
- `city` (City): Referens till staden för vilken vädret gäller.

## Databaskonfiguration

- Databas-URL: jdbc:mysql://localhost:3306/spring_mysql_docker
- Användarnamn: root
- Lösenord: root
- Drivrutinklass: com.mysql.cj.jdbc.Driver


---

## UML & Modeller

**Klassdiagram - IntelliJ :** Jag har valt att göra ett klassdiagram i IntelliJ. Det är ett bra verktyg för att förstå strukturen i sin kod.

![IntelliJ](https://github.com/Distansakademin/spring-weather-api-Arinsz/blob/development/Spring-weather-api-IntelliJClassdiagram-UML.jpg)



- **UML-EdrawMax:**  Jag har även gjort flera olika UML diagram


- **Package diagram:** visar de olika paketen (packages) i mitt projekt och hur de är organiserade.


- **Use Case diagram:** beskriver de olika användningsfallen eller scenarierna i min applikation, Det visar hur olika aktörer eller andra system interagerar med systemet.


- **Class diagram:** Klassdiagrammet visar de olika klasserna och deras relationer.

![EdrawMax](https://github.com/Distansakademin/spring-weather-api-Arinsz/blob/development/Spring-weather-api-Edraw-UML.jpg)

___


## Problem och Lösningar

### Problem
1. **Docker:** Docker var svårt då jag försökte ha min SQL server och docker anslutna tillsammans, jag lyckades få till det med dockercompose och docker build --network men var osäker på om det faktiskt fungerade eller om det endast var nätverket som visades i docker appen. 
2. **Undantagshantering:** Behovet av att hantera olika typer av undantag och kommunicera tydliga felmeddelanden till användare. Jag skapade en ApiResponse för detta och utmaningen var att den behövdes i många sammanhang. 
3. **Warnings:** fick en del warnings och fel som jag hade svårt att förstå då informationen inte är lika specifik i vart felet ligger som i vanliga TDD tester. 
4. **UML-Diagram:** Har googlat en del och kollat youtube videos, har fortfarande lite svårt att avgöra vilket håll pilarna ska riktas åt baserat på vad, men jag antar att det är en sak man lär sig av erfarenhet.  
5. **Cucumber:** Jag försökte få cucumber att fungera med mitt projekt men fick null värden, jag tror det beror på att den inte vet att Databasen finns.
6. **ManyToOne&OneToMany:** Detta var lite svårt att få till i början, när man väl hittas lösningen så är det ganska självklart. Men dessa passade bra i City och Country med tanke på deras relation. 
7. **/api/{country}/cities:** I CountryController hade jag api/countries som klassens URL path, detta ställde till det för mig då uppgiften var att genom denna url /api/{country}/cities hämta alla städer för ett land och med url path api/countries blev det api/countries/{country]cities.

### Lösningar
1. **Docker:** host.docker.internal:3306 när jag vill bygga och ansluta docker container. localhost:3306/spring_mysql_docker när jag vill bygga och ansluta via MySql.
2. **Undantagshantering:** Det släppte efter ett tag och det blev enklare att förstå logiken.
3. **Warnings:** Jag hade en bra inställning till det och tänkte att detta är bra att vänja sig vid och att utmanande att lösa själv.
4. **UML-Diagram:** Tog hjälp av IntelliJ class diagram för att förstå strukturen och vilket håll pilarna ska åt osv. Mer djupgående sökning av olika termer inom UML diagram. 
5. **Cucumber:** Jag gjorde ett exempel som inte hade med mitt projekt att göra. Jag försökte ett antal timmar att få det att fungera med mitt projekt utan framsteg. 
6. **ManyToOne&OneToMany:** Googlade och sökte mer för att förstå mer djupgående vad de gör och i vilka fall det är bra att använda de.
7. **/api/{country}/cities:** Jag valde att köra /api för alla Controller klasserna och i annotations för mapping ange mer specific URL för att lösa detta enligt uppgiften. 

**Erfarenhet:** För mig är erfarenheten jag får när jag stöter på problem och hinder något jag värdesätter och ofta tänker på medans jag kodar. Jag känner att den inställningen har hjälpt mig mycket i detta projekt. 

### Projektets Framtid och förbättringspotential

1. **Säkerhet:** Implementera strikt inputvalidering för att förhindra SQL-injektioner.
2. **Frontend-Applikation React:**  implementera en användarvänlig frontend med React. Vilket ger användare möjlighet att interagera med mitt API genom en visuellt gränssnitt.
3. **Swagger-dokumentation::**  för att generera automatiserad dokumentation.
4. **Logging och Övervakning:** Förbättra loggingen för att underlätta felsökning och övervakning av systemets prestanda. 







---


## Spring Boot REST API - Countries and Cities

#### URL

- http://localhost:8080/

### Countries 

- **Create Country:** `POST` - http://localhost:8080/api/countries/create
- **Read (one Country):** `GET` - http://localhost:8080/api/countries/{id}
- **Read (all Countries):** `GET` - http://localhost:8080/api/countries/getAll
- **Update Country:** `PUT` - http://localhost:8080/api/countries/{id}
- **Delete Country:** `DELETE` - http://localhost:8080/api/countries/{id}
- **Get All Cities in Country:** GET - http://localhost:8080/api/{country}/cities

### Cities 

- **Create City:** `POST` - http://localhost:8080/api/cities/create/{CountryId}
- **Read (one City):** `GET` - http://localhost:8080/api/cities/{id}
- **Read (all Cities):** `GET` - http://localhost:8080/api/cities/getAll
- **Update City:** `PUT` - http://localhost:8080/api/cities/{id}
- **Delete City:** `DELETE` - http://localhost:8080/api/cities/{id}

### Weather 

- **Get Weather by City ID:** `GET` - http://localhost:8080/api/weather/{city_id}

### Databas Config

- Databas URL: jdbc:mysql://localhost:3306/spring_mysql_docker
- Användarnamn: root
- Lösenord: root
- Drivrutin : com.mysql.cj.jdbc.Driver


#### För Testa API Endpoints

- Använd verktyg som Postman för att skicka HTTP-förfrågningar.
- Använd MySQL för att starta en SQL server.


#### <u>/ Arin Sarafraz</u>



[läs mer om mig ](./about-me.md)