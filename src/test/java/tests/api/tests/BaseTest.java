package tests.api.tests;

import config.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import static helpers.CustomApiListener.withCustomTemplates;

public class BaseTest {
    static ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());
    public static final String BASE_API_URL = config.baseApiUrl();
    public static final String CART_PATH = "cart";
    public static final String CART_PRODUCT_PATH = "product";

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = BASE_API_URL;
        RestAssured.filters(withCustomTemplates(), new ResponseLoggingFilter(), new RequestLoggingFilter());
    }
}
