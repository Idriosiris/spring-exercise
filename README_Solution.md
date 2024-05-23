# Notes on testing it out
- Project is using Java 18 and springboot 3.2.5 and was run using Intellij IDEA
- For automated tests the main suite is in `src/test/java/com/example/companylookup/CompanySearchApplicationTest.java`
- For manual testing run the app and go to `http://localhost:8080/swagger-ui.html`
- First time you call the search endpoint it will take a while to respond because it's caching the response
- To see the response being cached go to `http://localhost:8080/h2-console` and use the following settings:
  - JDBC URL: `jdbc:h2:mem:testdb`
  - User Name: `sa`
  - Password: `password`
- The database can also persist between application runs 
  - What you need to do is to change the `spring.datasource.url` property in `application.properties` to the value 
    in the comment
- If you click the table COMPANY_SEARCH_ENTITY it should automatically build a query as 
  - `SELECT * FROM COMPANY_SEARCH_ENTITY` and you can see the cached response being populated

- Feel free to roll back to any previous commit and see how the code looked throughout the exercise
  - Tests should be passing at any commit going back to the first one -- at least in theory :) 

Following company number is a good one to test with as it returns a consistent batch of companies so you can see the 
cache in action
```json
{
  "companyNumber": "1390"
}
```

# TODO
1. Adding integration tests for the database as we don't do anything with that yet
2. Any other way to improve the tests?
   1. Builder for the JSON wiremock stubs 
   2. Use custom hamcrest matchers 
3. Use transformer annotations between different layers and not plain methods
   1. Although debatable as they sometimes become more convoluted 
4. Add more unit tests 
   1. At the moment the testing pyramid is turned and the units we test are very big 
   2. Surely certain aspects of the smaller classes can be tested individually 
5. Exception handling could be improved 
6. Logging could be improved 
7. Better documentation of resource for swagger

# Done
1. Initialize app with spring initializer
2. Create .gitignore file
   1. Need to ignore Intellij IDEA specific files and gradle ones
3. Make sure semantic and JDK setup is correct in Intellij IDEA
4. Write most trivial test for the lookup resource and see it fail
   1. Must be the simplest version of the problem that is deliverable
   2. Response to the initial resource can even be hardcoded in the initial faze
5. Add the two post query params to the search resource
6. Add the API credentials to be passed and expected in wiremock
7. Add swagger to project 
8. Test application manually
9. Refactor existing code as we can add some extra abstractions
10. Add logic that dictates how the company name and number are used
11. Add test for pulling in the response the Officer information 
12. Add test for edge cases such as empty response list from the 3rd party api and/or 404, 400, 500 etc 
    1. What happens when officers array is null? 
13. Add an extra service for the logic putting together the companies and Officers
14. Double-checked the default, it's 10s 
15. Fix all typos True -> Tru
16. Seems like there is an instance where the officers items array comes as null from the api
    1. For now, I am treating it as an empty array so my code works, and it's easier to demo
17. Use 2 spaces tabs across project 
18. Double-check existing functional requirements
19. Cleanup directories, naming and grouping of classes
20. Set jackson mapper camelcase to underscore on our resource 
21. What database should I use for the bonus part of the project? 
    1. The readme seems to suggest we need to use a database 
    2. If we need a database I'd go with something in-memory like H2 
    3. Not SQLite because H2 is more compatible with springboot so for this exercise I've got best chances to not encounter problems 
    4. This can also help me show how Hibernate works 
    5. And help me emulate other things like PostgreSQL while being embedded 
    6. It's also embedded in the app so minimal setup is needed to run it and play around the H2 ui to see how data is actually populated, helps with demoing 
    7. The use case though seems more like something that would benefit from an in-memory cache like guava, memcached etc or even a distributed cache like Redis 
22. Setup H2 database and cache response when called via company number 
23. Storage can also be persistent via file storage
24. Write readme on how to go through the code and changes 
25. Extract factory methods for the assert part of the tests