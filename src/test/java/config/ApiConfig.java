package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources(
        {
                "classpath:${env}.properties"
        }
)
public interface ApiConfig extends Config {
    @Key("baseApiUrl")
    @DefaultValue("https://web-gate.chitai-gorod.ru/api/v1")
    String baseApiUrl();
}
