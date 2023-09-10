package helpers;

import config.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AddCookie {
    private static final WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
    public static void addAuthCookie(String value){
        String authCookieKey = "access-token";
        Cookie authCookie = new Cookie(authCookieKey, value);
        open(config.baseUrl() + "favicon.ico");
        getWebDriver().manage().addCookie(authCookie);
    }
}
