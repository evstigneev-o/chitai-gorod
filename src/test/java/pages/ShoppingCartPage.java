package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ShoppingCartPage {
    private final SelenideElement
            cartTitle = $(".app-title"),
            emptyCart = $(".empty-content"),
            cleanUpButton = $(".delete-many"),
            cleanUpInformation = $(".cart-multiple-delete");
    private final ElementsCollection
            productItems = $$(".cart-item");

    public void openPage() {
        open("cart");
    }

    public void checkTitle(String title) {
        cartTitle.shouldHave(Condition.text(title));
    }

    public void checkEmptyCart() {
        emptyCart.shouldBe(Condition.visible);
    }

    public int getCartProductsItemsSize() {
        Selenide.Wait().until(visibilityOf(productItems.get(0)));
        return productItems.size();
    }

    public void cleanUpCart() {
        cleanUpButton.click();
    }

    public void checkCleanUp() {
        cleanUpInformation.shouldBe(Condition.visible);
    }
}
