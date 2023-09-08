package tests.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.nullValue;

public class ResponseSpecs {
    public static ResponseSpecification addProductToCartResponse200Spec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification getCartResponse200Spec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/cart.json"))
            .build();

    public static ResponseSpecification getCartResponse401Spec = new ResponseSpecBuilder()
            .expectStatusCode(401)
            .expectBody(matchesJsonSchemaInClasspath("schemas/error.json"))
            .build();

    public static ResponseSpecification deleteProductFromCartResponse204Spec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification deleteProductFromCartResponse404Spec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectBody(matchesJsonSchemaInClasspath("schemas/error.json"))
            .build();
}
