package tests.mobile;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Epic("MOBILE")
@Feature("ShoppingCart")
@Tag("android")
@DisplayName("Корзина")
public class ShoppingCartTests extends BaseTest {
    private final String EMPTY_CART_DESCRIPTION = "Ваша корзина сейчас пуста. Перейдите в каталог, чтобы найти интересные книги.";

    @Test
    @DisplayName("Открытие пустой корзины")
    @Severity(SeverityLevel.CRITICAL)
    public void shoppingCartShouldBeEmpty() {
        step("Переход в корзину", () -> $(AppiumBy.id("ru.chitaigorod.mobile:id/cartFragment")).click());
        step("Проверка что корзина пуста", () -> {
            $(AppiumBy.id("ru.chitaigorod.mobile:id/imageEmpty")).shouldBe(Condition.visible);
            $(AppiumBy.id("ru.chitaigorod.mobile:id/textEmptyDescription")).shouldHave(Condition.text(EMPTY_CART_DESCRIPTION));
        });
    }
}
