package config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/resources/pages/credentials.properties",
})
public interface CredentialsConfig extends Config {
    @Key("login")
    String login();
    @Key("password")
    String password();
}
