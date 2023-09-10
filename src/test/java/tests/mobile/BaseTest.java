package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.AndroidLocalDriver;
import drivers.BrowserstackDriver;
import helpers.Attach;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class BaseTest {
    public static String mobileEnv = System.getProperty("envMobile", "android_remote");

    @BeforeAll
    public static void setUp() {
        if (mobileEnv.equals("android_remote")) {
            Configuration.browser = BrowserstackDriver.class.getName();
        } else {
            Configuration.browser = AndroidLocalDriver.class.getName();
        }
        Configuration.browserSize = null;
        Configuration.timeout = 40000;
    }

    @BeforeEach
    public void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
        step("Пропуск онбординга", () ->
                $(AppiumBy.id("ru.chitaigorod.mobile:id/buttonSkip")).click()
        );
        if (mobileEnv.equals("android_local")) {
            step("Пропуск включения уведомлений", () ->
                    $(AppiumBy.id("ru.chitaigorod.mobile:id/buttonNotNow")).click()
            );
        }
        step("Выбор города", () ->
                $(AppiumBy.id("ru.chitaigorod.mobile:id/buttonProceed")).click()
        );
        step("Пропуск регистрации", () ->
                $(AppiumBy.id("ru.chitaigorod.mobile:id/buttonNotNow")).click()
        );
    }

    @AfterEach
    public void tearDown() {
        String sessionId = sessionId().toString();
        Attach.pageSource();
        closeWebDriver();
        if (mobileEnv.equals("android_remote")) {
            Attach.addVideoBrowserstack(sessionId);
        }
    }
}
