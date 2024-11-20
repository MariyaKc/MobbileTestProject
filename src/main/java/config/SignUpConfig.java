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
    @Key("buttonLogin")
    String buttonLogin();
    @Key("buttonShowPassword")
    String buttonShowPassword();
    @Key("labelError")
    String labelError();
    /** text */
    @Key("titleText")
    String titleText();
    @Key("labelLoginFieldText")
    String labelLoginFieldText();
    @Key("labelPasswordFieldText")
    String labelPasswordFieldText();
    @Key("labelButtonLoginText")
    String labelButtonLoginText();
}
