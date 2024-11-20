package config;

import org.aeonbits.owner.Config;
import org.checkerframework.checker.regex.qual.Regex;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/resources/pages/testData.properties",
})
public interface TestDataConfig extends Config {

    @Key("allowedCharsLogin")
    String allowedCharsLogin();
    @Key("validCharsLogin")
    String validCharsLogin();
    @Key("maxLengthLogin")
    int maxLengthLogin();
    @Key("regexPatternLogin")
    String regexPatternLogin();
    @Key("allowedInvalidCharsLogin")
    String allowedInvalidCharsLogin();
    @Key("invalidCharsLogin")
    String invalidCharsLogin();
    @Key("russianChars")
    String russianChars();

}
