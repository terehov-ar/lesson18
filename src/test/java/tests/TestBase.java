package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.MobileConfig;
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

    private static final MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    @BeforeAll
    static void setupSelenideConfig() {

        switch (config.deviceHost()) {
            case "emulator" -> Configuration.browser = EmulatorDriver.class.getName();
            case "remote" -> Configuration.browser = BrowserstackDriver.class.getName();
            default -> throw new IllegalArgumentException(
                    "Unknown deviceHost: " + config.deviceHost());
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
    void addAttachments() {

        if (config.deviceHost().equals("remote")) {
            String sessionId = Selenide.sessionId().toString();
            Attach.pageSource();
            Attach.addVideo(sessionId);
        };

        closeWebDriver();
    }
}