package tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;
import pages.SearchResultPage;
import pages.ShoppingCartPage;

import java.util.List;

import static io.qameta.allure.Allure.step;

@Epic("WEB")
@Feature("Main Page")
@DisplayName("Главная страница")
public class MainPageTests extends BaseTest {
    private final List<String> NAVBAR_ITEMS = List.of("Акции", "Распродажа", "Школа-2023", "Подборки", "Читай-журнал", "Книжные циклы");
    private final String SHOPPING_CART_TITLE = "КОРЗИНА";

    MainPage mainPage = new MainPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    SearchResultPage searchResultPage = new SearchResultPage();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Tag("ui")
    @DisplayName("Проверка открытия главной страницы")
    public void successfullyOpenMainPage() {
        step("Открытие главной страницы", () -> mainPage.openPage());
        step("Проверка успешной загрузки страницы", () -> mainPage.checkHeaderLogo());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag("ui")
    @DisplayName("Проверка названий ссылок в навбаре")
    public void navbarItemsShouldHaveCorrectItemsNames() {
        step("Открытие главной страницы", () -> mainPage.openPage());
        step("Проверяем названия ссылок навбара", () -> mainPage.checkNavbarTexts(NAVBAR_ITEMS));

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ui")
    @DisplayName("Проверка перехода в корзину с главной страницы")
    public void cartPageShouldBeOpenedFromMainPage() {
        step("Открытие главной страницы", () -> mainPage.openPage());
        step("Клик по иконке корзины", () -> mainPage.clickOnShoppingCart());
        step("Проверка что осуществлен переход в корзину", () -> shoppingCartPage.checkTitle(SHOPPING_CART_TITLE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"пенал", "карандаш"})
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ui")
    @DisplayName("Переход на страницу результатов поиска с главной страницы")
    public void searchResultShouldContainCorrectProducts(String product) {
        step("Открытие главной страницы", () -> mainPage.openPage());
        step("Поиск товара", () -> mainPage.searchProduct(product));
        step("Поиск результатов поиска", () -> searchResultPage.checkSearchResult(product));
    }

}
