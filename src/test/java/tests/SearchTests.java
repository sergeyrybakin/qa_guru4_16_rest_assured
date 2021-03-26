package tests;

import api.Auth;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SearchTests extends TestBase {

    String expectedText = "title=\"Show details for 14.1-inch Laptop\"";

    @Test
    void searchGoodsWithCookieTest()
    {
        Map<String, String> cookies = new Auth().login( config.login(), config.password() );

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookies(cookies)
                .when()
                .get("/search?Q=Laptop&As=false&Cid=0&Isc=false&Mid=0&Pf=&Pt=&Sid=false")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        String stringResponse = response.asString();
        assertTrue(stringResponse.contains(expectedText), "###ERROR: Response should contain text: " + expectedText + "!");
    }

    @Test
    void searchGoodsWithCookieNegativeTest()
    {
        Map<String, String> cookies = new Auth().login( config.login(), config.password() );

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookies(cookies)
                .when()
                .get("/search?Q=Book&As=false&Cid=0&Isc=false&Mid=0&Pf=&Pt=&Sid=false")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        String stringResponse = response.asString();
        assertFalse(stringResponse.contains(expectedText), "###ERROR: Response should not contain text: " + expectedText + "!");
    }
}
