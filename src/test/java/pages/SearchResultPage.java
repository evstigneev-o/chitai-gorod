package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SearchResultPage {
    private final SelenideElement
            successSearchMessage = $(".search-page__found-message");

    public void checkSearchResult(String product){
        Selenide.Wait().until(visibilityOf(successSearchMessage));
        successSearchMessage.shouldHave(Condition.text(String.format("Показываем результаты по запросу «%s»",product)));
    }

}
