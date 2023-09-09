package tests.mobile;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class ProfileTests extends BaseTest {
    private final List<String> AVAILABLE_COUNTRIES = List.of("Беларусь", "Казахстан", "Россия");

    @Test
    @Tag("android")
    @DisplayName("Проверка списка стран присутствия сети магазинов")
    @Severity(SeverityLevel.NORMAL)
    public void changeLocationShouldHave3Countries() {
        step("Переход в профиль", () -> $(AppiumBy.id("ru.chitaigorod.mobile:id/profileFragment")).click());
        step("Клик на локацию", () -> $(AppiumBy.id("ru.chitaigorod.mobile:id/locationTV")).click());
        step("Переход к списку стран", () -> $(AppiumBy.id("ru.chitaigorod.mobile:id/selectCountyTV")).click());
        step("Проверка списка стран", () -> {
            $$(AppiumBy.id("ru.chitaigorod.mobile:id/textViewGetSubjectTitle")).shouldHave(size(3));
            $$(AppiumBy.id("ru.chitaigorod.mobile:id/textViewGetSubjectTitle")).shouldHave(texts(AVAILABLE_COUNTRIES));
        });
    }

    @ParameterizedTest
    @MethodSource("getCitiesByCountry")
    @Tag("android")
    @DisplayName("Проверка списка городов по странам присутствия")
    @Severity(SeverityLevel.NORMAL)
    public void countriesShouldHaveCorrectCountryList(String country, List<String> cities) {
        step("Переход в профиль", () -> $(AppiumBy.id("ru.chitaigorod.mobile:id/profileFragment")).click());
        step("Клик на локацию", () -> $(AppiumBy.id("ru.chitaigorod.mobile:id/locationTV")).click());
        step("Переход к списку стран", () -> $(AppiumBy.id("ru.chitaigorod.mobile:id/selectCountyTV")).click());
        step("Выбор страны", () -> $(AppiumBy.xpath(".//android.widget.TextView[@text='" + country + "']"))).click();
        step("Проверка списка городов", () -> $$(AppiumBy.id("ru.chitaigorod.mobile:id/textViewGetSubjectTitle")).shouldHave(texts(cities)));
    }

    private static Stream<Arguments> getCitiesByCountry() {
        return Stream.of(
                Arguments.of("Беларусь",
                        List.of("Минск, Район Минский, обл. Минская",
                                "Гомель, Район Гомельский, обл. Гомельская",
                                "Витебск, Район Витебский, обл. Витебская",
                                "Могилев, Район Могилёвский, обл. Могилевская",
                                "Гродно, Район Гродненский, обл. Гродненская",
                                "Брест, Район Брестский, обл. Брестская",
                                "Город Бобруйск, Район Бобруйский, обл. Могилевская",
                                "Город Барановичи, Район Барановичский, обл. Брестская",
                                "Борисов, Район Борисовский, обл. Минская",
                                "Пинск, Район Пинский, обл. Брестская")),
                Arguments.of("Казахстан",
                        List.of("Нур-Султан",
                                "Алматы",
                                "Шымкент",
                                "Актобе, Актюбинская Область",
                                "Караганда, Карагандинская Область",
                                "Тараз, Жамбылская Область",
                                "Павлодар, Павлодарская Область",
                                "Усть-Каменогорск, Восточно-Казахстанская Область",
                                "Семей, Восточно-Казахстанская Область",
                                "Атырау, Атырауская Область")),
                Arguments.of("Россия",
                        List.of("Москва",
                                "Екатеринбург, Свердловская Область",
                                "Новосибирск, Новосибирская Область",
                                "Санкт-Петербург",
                                "Краснодар, Краснодарский Край",
                                "Челябинск, Челябинская Область",
                                "Красноярск, Красноярский Край",
                                "Ростов-на-Дону, Ростовская Область",
                                "Самара, Самарская Область",
                                "Воронеж, Воронежская Область"))
        );
    }
}
