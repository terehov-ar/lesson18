package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void successfulDescriptionCheck() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenium software");
        });
        step("Assert first description result", () -> {
            $(id("org.wikipedia.alpha:id/page_list_item_description")).shouldHave(Condition.text("Testing framework for web applications"));
        });
    }
    @Test
    void unSuccessfulSearchTest405() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenium software");
        });
        step("Click on selenium search result", () -> {
            $(id("org.wikipedia.alpha:id/page_list_item_title")).click();
            $(id("pcs-edit-section-title-description")).shouldHave(Condition.text("Testing framework for web applications"));
        });
    }

}