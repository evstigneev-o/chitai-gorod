package pages;

import com.codeborne.selenide.*;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement
            logoIcon = $(".header-logo__icon"),
            shoppingCart = $("[href=\"/cart\"]"),
            searchInput = $(".header-search__input");
    ElementsCollection
            navBarItems = $$(".header-bottom__link"),
            searchResults = $$(".product-card");

    public void openPage() {
        open("");
    }

    public void checkHeaderLogo() {
        logoIcon.shouldBe(Condition.visible);
    }

    public void checkNavbarTexts(List<String> itemNames) {
        navBarItems.shouldHave(texts(itemNames));
    }

    public void clickOnShoppingCart() {
        shoppingCart.hover().click();
    }

    public void searchProduct(String value) {
        searchInput.setValue(value).pressEnter();
    }

}
