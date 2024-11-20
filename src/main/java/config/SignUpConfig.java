package config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/resources/pages/signup.properties",
})
public interface SignUpConfig extends Config {
    /**
     * general
     */
    @Key("title")
    String title();
    @Key("fieldUsername")
    String fieldUsername();
    @Key("fieldPassword")
    String fieldPassword();
    /** text */
    @Key("titleText")
    String titleText();
}
