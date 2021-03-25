package tests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class LoginTests extends TestBase {

    String email = "rserge65@bk.ru";
    String password = "Passw0rd_zxc";

    @Test
    void loginWithUITest() {
        //authorize
        Selenide.open("/login");
        $("#Email").val(email);
        $("#Password").val(password).pressEnter();
        //verify successful authorization
        $(".account").shouldHave(text(email));
    }

    @Test
    void loginWithCookieTest() {

        Map<String, String> cookiesFromAPI = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                //                .contentType(ContentType.URLENC)
                .formParam("Email", email)
                .formParam("Password", password)
                .formParam("RememberMe", "false")
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .log().body()
                .extract().cookies();

        //To avoid loading of all page resources, load small icon
        Selenide.open("/Themes/DefaultClean/Content/images/logo.png");
        //Get Response Cookie from Fiddler login
        //Cookie: ARRAffinity=1fa9158750fcf7cee1728ac683a12594fe016bf3b1c0544237f51a4ffe2ef5ea;
        //Nop.customer=9ec12887-ab6a-405b-bc48-7bb8dc223227; Expires=Fri, 25-Mar-2022 14:40:32 GMT; Path=/; HttpOnly
        //NOPCOMMERCE.AUTH=6E709CB1F137F00771758B42B24BF0D145F8C24EFD121AFF30ADE3E1C1CC760A596C13976227FF814B1F3B6E5F332919521181BDFEBC9ED3D033D15A3DE3658AD647CF07A99621A2B5E8C417A328FA5EE36BA8B4293CF72CBD9365E1A3E9AB3E7F52DA4ED9A12070894E4DB285C6DFE63F857DDA93D5AFA87690B289D58BC8A50B85520DEC6EDD685982D1E109D49862; Path=/; HttpOnly
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookiesFromAPI.get("Nop.customer")));
        getWebDriver().manage().addCookie(new Cookie("ARRAffinity",cookiesFromAPI.get("ARRAffinity")));
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH",cookiesFromAPI.get("NOPCOMMERCE.AUTH")));

        Selenide.open("");
        //verify successful authorization
        $(".account").shouldHave(text(email));
    }

}
