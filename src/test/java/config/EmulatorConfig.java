package config;

import org.aeonbits.owner.Config;

public interface EmulatorConfig extends Config {
    @Key("appiumServer")
    @DefaultValue("http://127.0.0.1:4723/wd/hub")
    String appiumServer();

    @Key("deviceName")
    @DefaultValue("Pixel_6a_API_33")
    String deviceName();

    @Key("platformVersion")
    @DefaultValue("13.0")
    String platformVersion();

    @Key("appPath")
    @DefaultValue("src/test/resources/apps/chitaygorod.apk")
    String appPath();

    @Key("appPackage")
    @DefaultValue("ru.chitaigorod.mobile")
    String appPackage();

    @Key("appActivity")
    @DefaultValue("ru.handh.chitaigorod.ui.main.MainActivity")
    String appActivity();
}
