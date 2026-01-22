package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.RemoteMobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    private final RemoteMobileConfig configRemote = ConfigFactory.create(RemoteMobileConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", configRemote.browserstackUser());
        caps.setCapability("browserstack.key", configRemote.browserstackKey());

        caps.setCapability("app", "bs://sample.app");

        caps.setCapability("device", configRemote.device());
        caps.setCapability("os_version", configRemote.osVersion());

        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");

        try {
            return new RemoteWebDriver(
                    new URL(configRemote.remoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}