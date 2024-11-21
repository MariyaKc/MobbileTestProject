package config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/resources/pages/successSignUp.properties",
})
public interface SuccessSignUpConfig extends Config {
    @Key("textSuccessSignUpTitle")
    String textSuccessSignUpTitle();
    @Key("titleSuccessSignUpX")
    String titleSuccessSignUpX();
}
