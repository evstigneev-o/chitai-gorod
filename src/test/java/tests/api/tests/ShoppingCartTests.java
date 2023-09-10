package tests.api.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.models.AdDataModel;
import tests.api.models.AddToCardRequestModel;
import tests.api.models.CartResponseModel;
import tests.api.models.ErrorModel;

import static helpers.ApiAuthorization.getAuthToken;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static tests.api.specs.RequestSpecs.cartRequestSpec;
import static tests.api.specs.ResponseSpecs.*;

@Epic("API")
@Feature("Shopping Cart")
@DisplayName("Корзина")
public class ShoppingCartTests extends BaseTest {
    private static final Integer PRODUCT_ID = 2638805;
    private static final String ITEM_LIST_NAME = "index";
    private static final String PRODUCT_SHELF = "Лучшие из лучших";
    private static final String PRODUCT_NOT_FOUND = "товар в корзине не найден";
    private static final String AUTH_REQUIRED = "Authorization обязательное поле";

    @Test
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Добавление товара в корзину только с обязательными параметрами")
    public void addProductToCardWithRequiredParamsShouldReturn200() {
        AddToCardRequestModel requestBody = AddToCardRequestModel.builder()
                .id(PRODUCT_ID)
                .build();
        String authToken = step("Получение авторизационного токена", () -> getAuthToken());
        step("Добавление товара в корзину", () ->
                given(cartRequestSpec)
                        .header("authorization", authToken)
                        .when()
                        .body(requestBody)
                        .post(CART_PRODUCT_PATH)
                        .then()
                        .spec(addProductToCartResponse200Spec)
        );
    }

    @Test
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Добавление товара в корзину со всеми параметрами")
    public void addProductToCardWithAllParamsShouldReturn200() {
        AddToCardRequestModel requestBody = AddToCardRequestModel.builder()
                .id(PRODUCT_ID)
                .adData(AdDataModel.builder()
                        .itemListName(ITEM_LIST_NAME)
                        .productShelf(PRODUCT_SHELF)
                        .build())
                .build();
        String authToken = step("Получение авторизационного токена", () -> getAuthToken());
        step("Добавление товара в корзину", () ->
                given(cartRequestSpec)
                        .header("authorization", authToken)
                        .when()
                        .body(requestBody)
                        .post(CART_PRODUCT_PATH)
                        .then()
                        .spec(addProductToCartResponse200Spec)
        );
    }

    @Test
    @Tag("api")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Запрос корзины нового пользователя")
    public void emptyCartShouldReturnEmptyProductList() {
        String authToken = step("Получение авторизационного токена", () -> getAuthToken());
        CartResponseModel response = step("Запрос корзины", () ->
                given(cartRequestSpec)
                        .header("authorization", authToken)
                        .when()
                        .get()
                        .then()
                        .spec(getCartResponse200Spec)
                        .extract().as(CartResponseModel.class)
        );
        step("Валидация ответа", () ->
                assertThat(response.getProducts().size()).withFailMessage("В корзине не должно быть товаров").isEqualTo(0)
        );
    }

    @Test
    @Tag("api")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Запрос корзины без авторизационного токена")
    public void getCartWithoutAuthShouldReturn401() {
        ErrorModel response = step("Запрос корзины", () ->
                given(cartRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(getCartResponse401Spec)
                        .extract().as(ErrorModel.class)
        );
        step("Валидация ответа", () ->
                assertThat(response.getMessage()).withFailMessage("Неверное сообщение об ошибке").isEqualTo(AUTH_REQUIRED)
        );
    }

    @Test
    @Tag("api")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление товара из корзины")
    public void deleteProductFromCartShouldReturn204() {
        AddToCardRequestModel requestBody = AddToCardRequestModel.builder()
                .id(PRODUCT_ID)
                .build();
        String authToken = step("Получение авторизационного токена", () -> getAuthToken());
        step("Добавление товара в корзину", () ->
                given(cartRequestSpec)
                        .header("authorization", authToken)
                        .when()
                        .body(requestBody)
                        .post(CART_PRODUCT_PATH)
                        .then()
                        .spec(addProductToCartResponse200Spec)
        );
        int productId = step("Запрос корзины и получение идентификатора товара", () ->
                given(cartRequestSpec)
                        .header("authorization", authToken)
                        .when()
                        .get()
                        .then()
                        .spec(getCartResponse200Spec)
                        .extract().jsonPath().getInt("products[0].id")
        );
        step("Удаление товара из корзины", () ->
                given(cartRequestSpec)
                        .header("authorization", authToken)
                        .when()
                        .delete(CART_PRODUCT_PATH + "/" + productId)
                        .then()
                        .spec(deleteProductFromCartResponse204Spec)
        );
    }

    @Test
    @Tag("api")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление товара из корзины отсутствующего в ней товара")
    public void deleteNotAddedProductFromCartShouldReturn404() {
        String authToken = step("Получение авторизационного токена", () -> getAuthToken());
        ErrorModel response = step("Удаление товара из корзины", () ->
                given(cartRequestSpec)
                        .header("authorization", authToken)
                        .when()
                        .delete(CART_PRODUCT_PATH + "/" + PRODUCT_ID)
                        .then()
                        .spec(deleteProductFromCartResponse404Spec)
                        .extract().as(ErrorModel.class)
        );
        step("Валидация ответа", () ->
                assertAll(
                        () -> assertThat(response.getMessage()).withFailMessage("Неверное сообщение об ошибке").isEqualTo(PRODUCT_NOT_FOUND),
                        () -> assertThat(response.getRequestId()).withFailMessage("requestId не должен быть пустым").isNotNull()
                )
        );
    }
}
