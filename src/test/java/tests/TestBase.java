package tests;

import io.restassured.RestAssured;
import utils.CredentialsConfig;
import utils.SearchDataConfig;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;


public class TestBase {

    public static CredentialsConfig config;
    public static SearchDataConfig searchDataConfig;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        Configuration.baseUrl = "http://demowebshop.tricentis.com";

         config = ConfigFactory
                .create(CredentialsConfig.class,System.getProperties());

         searchDataConfig = ConfigFactory
                 .create(SearchDataConfig.class, System.getProperties());
    }
}
