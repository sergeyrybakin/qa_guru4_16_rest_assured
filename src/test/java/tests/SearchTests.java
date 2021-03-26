package tests;

import api.Auth;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SearchTests extends TestBase {

    String positiveSearch = searchDataConfig.positiveSearch();
    String expectedText = searchDataConfig.expectedText(); //"title=\"Show details for 14.1-inch Laptop\"";
    String negativeSearch = searchDataConfig.negativeSearch();
    String expectedNegativeText = searchDataConfig.expectedNegativeText(); //"No products were found that matched your criteria.";

    @Test
    void searchGoodsWithCookieTest()
    {
        Map<String, String> cookies = new Auth().login( config.login(), config.password() );

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookies(cookies)
                .when()
                .get("/search?Q=" + positiveSearch +"&As=false&Cid=0&Isc=false&Mid=0&Pf=&Pt=&Sid=false")
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
                .get("/search?Q=" + negativeSearch + "&As=false&Cid=0&Isc=false&Mid=0&Pf=&Pt=&Sid=false")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        String stringResponse = response.asString();
        assertTrue(stringResponse.contains(expectedNegativeText), "###ERROR: Response should contain text: " + expectedNegativeText + "!");
    }
}
