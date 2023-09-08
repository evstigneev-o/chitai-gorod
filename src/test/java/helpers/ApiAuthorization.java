package helpers;

import static io.restassured.RestAssured.given;

public class ApiAuthorization {
    public static String getAuthToken(){
        return given()
                .noFilters()
                .get("https://www.chitai-gorod.ru/")
                .then()
                .extract().cookie("access-token").replace("%20", " ");
    }
}
