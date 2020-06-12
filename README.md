#Deck of Cards Project

The project build tool is Maven. For now, I can use `pom.xml` file to import 
desired tools and libraries such as :
* RestAssured library
* Hamcrest library
* TestNG
* Selenium WebDriver
* BoniGarcia for WebDriverManager

## Packages and Classes
* Modules - This package stores objects of Cards, Images and Deck;
* controller- This package will run our API calls;
* utilities - This package stores our often used methods;

---
## How it works?

All objects are instantiated in 'module' package as Cards class, CustomImages and CustomResponse classes.

On GetDeck class, we call for API using [Link](https://deckofcardsapi.com/api/deck/new?jokers_enabled=true)

Once I got the response, I check with Assertion that deck_id is not Null. 

The next step is to draw cards:
With CustomResponse method we call for the following URI:

 getCustomResponse("https://deckofcardsapi.com/api/deck/"+resp.deck_id+"/draw/");

As a result, we get all drawn cards.

## Testing
I used for loop and Asserted each field as Assert.assertNotNull method, assuring that the fields are not empty.
__Test__ package has one test case which ensures status code and get correct success result.
 I used Hamcrest library in the last statement in `TestResponses class` in method `VerifyDeckofCardsStatusCode()`. 
 It helped me to assert within the script.
##Additionals

In addition to these works, I would run Selenium Webdriver to test out the UI. However, this is purely related to API, 
I can do it if needed.  
BaseURI and Path are being read in Configuration.Properties file to avoid hard coding.

To run the test, I used `TestNG` tool.


