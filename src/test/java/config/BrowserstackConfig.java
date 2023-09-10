package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${androidEnv}.properties"
})
public interface BrowserstackConfig extends Config {

    @Key("user")
    @DefaultValue("jeffbezos_DrqNJO")
    String user();

    @Key("key")
    @DefaultValue("GvsVzaDY76sHyVY2fLnn")
    String key();

    @Key("baseBsUrl")
    @DefaultValue("http://hub.browserstack.com/wd/hub")
    String baseBsUrl();

    @Key("app")
    @DefaultValue("bs://aa775cf68308e8ff9270c7b46ec6b3ee8ddd0873")
    String app();

    @Key("device")
    @DefaultValue("Google Pixel 6 Pro")
    String device();

    @Key("osVersion")
    @DefaultValue("12.0")
    String osVersion();

    @Key("project")
    @DefaultValue("Chitay-gorod")
    String project();

    @Key("build")
    @DefaultValue("browserstack-build-1")
    String build();

    @Key("name")
    @DefaultValue("mobile_test")
    String name();
}
