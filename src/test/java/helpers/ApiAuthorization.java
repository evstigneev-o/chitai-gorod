package helpers;

import config.ApiConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class ApiAuthorization {
    private static final ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());
    public static String getAuthToken(){
        return given()
                .noFilters()
                .get(config.baseUrl())
                .then()
                .extract().cookie("access-token").replace("%20", " ");
    }
}
