# TODO
1. Add logic that dictates how the company name and number are used 
2. Add test for pulling in the response the Officer information 
3. Add test for edge cases such as empty response list from the 3rd party api and/or 404, 400, 500 etc 
4. Make sure the timeout to downstream services is sensible 
5. Set jackson mapper camelcase to underscore on our resource 
6. Set mapper of incoming json to DTO 
7. Use transformers between different layers

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