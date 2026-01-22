package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${type}.properties",
        "classpath:remote.properties"
})
public interface EmulatorMobileConfig extends Config {
    @Key("device")
    String device();

    @Key("deviceHost")
    String deviceHost();

    @Key("platformVersion")
    String platformVersion();
}
