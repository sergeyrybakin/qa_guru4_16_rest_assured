package tests;

import api.Auth;
import io.restassured.response.Response;

import java.util.Map;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends TestBase {
    @Test
    void searchGoodsWithCookieTest()
    {
        Map<String, String> cookies = new Auth().login("rserge65@bk.ru", "Passw0rd_zxc");

        //search?Q=Laptop&As=false&Cid=0&Isc=false&Mid=0&Pf=&Pt=&Sid=false
        //Q=Laptop
        //As=false
        //Cid=0
        //Isc=false
        //Mid=0
        //Pf=
        //Pt=
        //Sid=false

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookies(cookies)
                .when()
                .get("/search?Q=Laptop&As=false&Cid=0&Isc=false&Mid=0&Pf=&Pt=&Sid=false")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        System.out.println(response);
        String stringResponse = response.asString();
        assertTrue(stringResponse.contains("14.1-inch Laptop"));
    }
}
