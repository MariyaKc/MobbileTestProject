package config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/resources/pages/signup.properties",
})
public interface SignUpConfig extends Config {

    @Key("title")
    String title();
    @Key("fieldLogin")
    String fieldLogin();
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
    @Key("errorInvalidDataText")
    String errorInvalidDataText();
    @Key("errorUserNotFoundText")
    String errorUserNotFoundText();
    /** element name */
    @Key("elementNameTitle")
    String elementNameTitle();
    @Key("elementNameFieldLogin")
    String elementNameFieldLogin();
    @Key("elementNameFieldPassword")
    String elementNameFieldPassword();
    @Key("elementNameButtonLogin")
    String elementNameButtonLogin();
    @Key("elementNameButtonShowPassword")
    String elementNameButtonShowPassword();
    @Key("elementNameErrorLoginInvalidValue")
    String elementNameErrorLoginInvalidValue();
    @Key("elementNameErrorUserNotFound")
    String elementNameErrorUserNotFound();

}
