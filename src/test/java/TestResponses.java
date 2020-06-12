import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import modules.CustomResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class TestResponses {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("base-uri");
        RestAssured.basePath = ConfigurationReader.getProperty("base-path");
    }

    @Test
    public void VerifyDeckofCardsStatusCode(){

        CustomResponse cr = new CustomResponse();
        given().accept(ContentType.JSON)
                .when().get("/new?new?jokers_enabled=true")
                .prettyPeek().then()
                .statusCode(200).assertThat().body("success", is(true));


    }
}
