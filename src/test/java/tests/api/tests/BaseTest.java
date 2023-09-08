package tests.api.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

import static helpers.CustomApiListener.withCustomTemplates;

public class BaseTest {
    public static final String BASE_API_URL = "https://web-gate.chitai-gorod.ru/api/v1";
    public static final String CART_PATH = "cart";
    public static final String CART_PRODUCT_PATH = "product";
    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = BASE_API_URL;
        RestAssured.filters(withCustomTemplates(),new ResponseLoggingFilter(),new RequestLoggingFilter());
    }
}
