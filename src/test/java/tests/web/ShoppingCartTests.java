package tests.web;

import helpers.ApiAuthorization;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ShoppingCartPage;
import tests.api.models.AddToCardRequestModel;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static tests.api.specs.RequestSpecs.cartRequestSpec;
import static tests.api.specs.ResponseSpecs.addProductToCartResponse200Spec;
import static tests.api.tests.BaseTest.BASE_API_URL;
import static tests.api.tests.BaseTest.CART_PRODUCT_PATH;

@Epic("WEB")
@Feature("ShoppingCart")
@DisplayName("Корзина")
public class ShoppingCartTests extends BaseTest {
    private static final Integer PRODUCT_ID = 2794526;
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ui")
    @DisplayName("Открытие пустой корзины")
    public void shoppingCartShouldBeEmpty() {
        step("Открытие корзины", () -> shoppingCartPage.openPage());
        step("Проверка что корзина пустая", () -> shoppingCartPage.checkEmptyCart());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ui")
    @DisplayName("Открытие корзины c товарами")
    public void shoppingCartShouldHaveProducts() {
        String authCookieKey = "access-token";
        AddToCardRequestModel requestBody = AddToCardRequestModel.builder()
                .id(PRODUCT_ID)
                .build();
        String authToken = step("Получение авторизационного токена", ApiAuthorization::getAuthToken);
        step("Добавление товара в корзину", () ->
                given(cartRequestSpec)
                        .baseUri(BASE_API_URL)
                        .log().all()
                        .header("authorization", authToken)
                        .when()
                        .body(requestBody)
                        .post(CART_PRODUCT_PATH)
                        .then()
                        .log().all()
                        .spec(addProductToCartResponse200Spec)
        );
        step("Добавление cookie в браузер", () -> {
            Cookie authCookie = new Cookie(authCookieKey, authToken);
            open("favicon.ico");
            getWebDriver().manage().addCookie(authCookie);
        });
        step("Открытие корзины", () -> shoppingCartPage.openPage());
        step("Проверка количества товаров в корзине", () -> assertThat(shoppingCartPage.getCartProductsItemsSize()).withFailMessage("Неверное количество товаров в корзине").isEqualTo(1));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ui")
    @DisplayName("Очистка корзины")
    public void cartShouldBeEmptyAfterClearing() {
        String authCookieKey = "access-token";
        AddToCardRequestModel requestBody = AddToCardRequestModel.builder()
                .id(PRODUCT_ID)
                .build();
        String authToken = step("Получение авторизационного токена", ApiAuthorization::getAuthToken);
        step("Добавление товара в корзину", () ->
                given(cartRequestSpec)
                        .baseUri(BASE_API_URL)
                        .log().all()
                        .header("authorization", authToken)
                        .when()
                        .body(requestBody)
                        .post(CART_PRODUCT_PATH)
                        .then()
                        .log().all()
                        .spec(addProductToCartResponse200Spec)
        );
        step("Добавление cookie в браузер", () -> {
            Cookie authCookie = new Cookie(authCookieKey, authToken);
            open("favicon.ico");
            getWebDriver().manage().addCookie(authCookie);
        });
        step("Открытие корзины", () -> shoppingCartPage.openPage());
        step("Очистка корзины", () -> shoppingCartPage.cleanUpCart());
        step("Проверка очистки корзины", () -> shoppingCartPage.checkCleanUp());
    }
}
