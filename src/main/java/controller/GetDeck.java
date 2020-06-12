package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import modules.CustomResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetDeck {


    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("base-uri");
        RestAssured.basePath = ConfigurationReader.getProperty("base-path");
    }

    @Test
    public void postJoker() {

      Response  response = when()
                .post("new?jokers_enabled=true");
        Assert.assertEquals(response.statusCode(), 201);



    }

    @Test
    public void getDeck() throws JsonProcessingException {

        String url = "https://deckofcardsapi.com/api/deck/new?jokers_enabled=true";
        CustomResponse resp = getCustomResponse(url);

        Assert.assertNotNull(resp.deck_id);
        System.out.println(resp.deck_id);

        resp = getCustomResponse("https://deckofcardsapi.com/api/deck/"+resp.deck_id+"/draw/");

        for (int i=0;i<resp.cards.length; i++){
            Assert.assertNotNull(resp.cards[i].images.png);
            Assert.assertNotNull(resp.cards[i].image);
            Assert.assertNotNull(resp.cards[i].code);
            Assert.assertNotNull(resp.cards[i].suit);
            Assert.assertNotNull(resp.cards[i].value);
        }

    }
    private CustomResponse getCustomResponse(String url ) {
        Response response = when()
                .get(url);
        ResponseBody requestBody = response.getBody();
        System.out.println(requestBody.asString());
        Gson gsonSerializer = new Gson();
        CustomResponse resp = gsonSerializer.fromJson(requestBody.asString(), CustomResponse.class);
        return resp;
    }
}
