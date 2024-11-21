package config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/resources/executor.properties",
})
public interface ExecutorConfig extends Config {
    /**
     * general
     */
    @Key("startAppium")
    String startAppium();
    @Key("killAppium")
    String killAppium();
    @Key("killEmulator")
    String killEmulator();
    @Key("portArray")
    String[] portArray();

}
