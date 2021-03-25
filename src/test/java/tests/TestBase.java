package tests;

import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeAll;

import com.codeborne.selenide.Configuration;

public class TestBase {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        Configuration.baseUrl = "http://demowebshop.tricentis.com";
    }
}
