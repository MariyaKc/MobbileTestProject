package config;

import org.aeonbits.owner.Config;

import java.util.ArrayList;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/main/java/resources/app.properties",
})
public interface AppConfig extends Config {
    @Key("protocol")
    String protocol();
    @Key("ip")
    String ip();
    @Key("appiumPorts")
    ArrayList<String> appiumPorts();

}

