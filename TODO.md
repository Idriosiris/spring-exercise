# TODO
1. Make tests more readable
2. Cleanup directories, naming and grouping of classes
2. Set jackson mapper camelcase to underscore on our resource 
3. Set mapper of incoming json to DTO 
4. Use transformers between different layers 
5. Add more unit tests 
6. Write readme on how to go through the code and changes 

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