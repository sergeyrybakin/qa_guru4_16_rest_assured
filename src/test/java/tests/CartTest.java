package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class CartTest  extends TestBase {

    @Test
    void successLoginWithDataInFileTest() {

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=569c9860-d072-4852-8ced-22e7b8c3fa7b; ARRAffinity=1fa9158750fcf7cee1728ac683a12594fe016bf3b1c0544237f51a4ffe2ef5ea; __utmc=78382081; __utmz=78382081.1616667127.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=78382081.1121149722.1616667127.1616667127.1616679478.2; __utmt=1; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=74; __atuvc=1%7C12; __atuvs=605c924af205d49b000; __utmb=78382081.2.10.1616679478")
                .body("product_attribute_74_5_26=81&product_attribute_74_6_27=83&product_attribute_74_3_28=86&addtocart_74.EnteredQuantity=1")
                .when()
                .post("/addproducttocart/details/74/1")
                .then()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .extract().response();
        System.out.println(response);
    }
}
