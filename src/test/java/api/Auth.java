package api;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Auth {
    public Map<String, String> login(String login, String password) {
        return given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                //                .contentType(ContentType.URLENC)
                .formParam("Email", login)
                .formParam("Password", password)
                .formParam("RememberMe", "false")
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .log().body()
                .extract().cookies();
    }
}
