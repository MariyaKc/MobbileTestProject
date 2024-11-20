package config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/resources/constants.properties",
})
public interface ConstantsConfig extends Config {

    @Key("wait1S")
    int wait1S();
    @Key("waitForConditionLaunch")
    int waitForConditionLaunch();
}
