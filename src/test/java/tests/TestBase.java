package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.*;
import drivers.*;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    private static final RemoteMobileConfig configRemote = ConfigFactory.create(RemoteMobileConfig.class, System.getProperties());
    private static final EmulatorMobileConfig configEmulator = ConfigFactory.create(EmulatorMobileConfig.class, System.getProperties());

    @BeforeAll
    static void setupSelenideConfig() {

        if (configRemote.deviceHost().equals("remote")) {
            Configuration.browser = BrowserstackDriver.class.getName();
        }
        else if (configEmulator.deviceHost().equals("emulator")) {
            Configuration.browser = EmulatorDriver.class.getName();
        }
        else {
            throw new IllegalArgumentException(
                    "Unknown deviceHost: " + configRemote.deviceHost());
        };
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void addListenerAndOpen() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachmentsAndCloseDriver() {

        if (configRemote.deviceHost().equals("remote")) {
            String sessionId = Selenide.sessionId().toString();
            Attach.addVideo(sessionId);
        }
        else {
            Attach.screenshotAs("Last screenshot");
        };

        Attach.pageSource();
        closeWebDriver();
    }
}