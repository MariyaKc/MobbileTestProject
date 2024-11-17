package config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/main/java/resources/emulator.properties",
})
public interface EmulatorConfig extends Config {
    //path
    @Key("androidHome")
    String androidHome();
    @Key("EmulatorPath")
    String EmulatorPath();
    @Key("adbPath")
    String adbPath();
    // command
    @Key("avd")
    String avd();
    @Key("gpu")
    String gpu();
    @Key("shellCommand")
    String shellCommand();
    @Key("getPropCommand")
    String getPropCommand();
    @Key("emuCommand")
    String emuCommand();
    @Key("killCommand")
    String killCommand();
    //parametrs
    @Key("bootCompletedProperty")
    String bootCompletedProperty();
    @Key("emulatorName")
    String emulatorName();
    @Key("on")
    String on();
}
