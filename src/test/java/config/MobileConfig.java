package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${type}.properties",
        "classpath:emulator.properties"
})
public interface MobileConfig extends Config {
    @Key("user")
    String browserstackUser();

    @Key("key")
    String browserstackKey();

    @Key("remoteUrl")
    String remoteUrl();

    @Key("device")
    String device();

    @Key("os_version")
    String osVersion();

    @Key("deviceHost")
    String deviceHost();

    @Key("platformVersion")
    String platformVersion();

}
