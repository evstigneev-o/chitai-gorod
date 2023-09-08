package tests.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static tests.api.tests.BaseTest.CART_PATH;

public class RequestSpecs {

    public static RequestSpecification cartRequestSpec = new RequestSpecBuilder()
            .setBasePath(CART_PATH)
            .setContentType(ContentType.JSON)
            .build();

}
