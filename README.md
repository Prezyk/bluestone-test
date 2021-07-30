# bluestone-test
## Project build:
  Go to project root
  
  Run:
```
mvn install
```

## Project run:
  From project root catalog run:
```
mvn org.springframework.boot:spring-boot-maven-plugin:2.5.3:run
```
## API Key configuration:
To configure static API Key value change value for property "holiday.security.apiKey.value" in application.properties.

## Project description
Project consists of simple single-enpoint controller (GET /api/holidays) which accepts three params: date (yyyy-mm-dd), countryCode1 and countryCode2 and returns response in form of:
```
{
  "date" : "2018-02-01",
  "name1" : "National Freedom Day",
  "name2" : "First Day of Black History Month"
}
```
Holiday names are fetched from https://calendarific.com by a specified remote Repository class which sends two requests with asynchrounous Callable implementation and ExecutorService and process response (with help of a mapper class) to a inner data classes.
Duplicated holiday names are not deleted from the list (that case was not mentioned in requirements). Application is secured with a simple filter and static API Key configured in application.properties.
