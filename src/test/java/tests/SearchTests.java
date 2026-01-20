package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import screens.WikipediaPage;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class SearchTests extends TestBase {

    WikipediaPage wikiPage = new WikipediaPage();

    @Test
    void successfulSearchTest() {
        wikiPage.openSearch()
                .searchText("Appium")
                .checkSearchResult();
    }

    @Test
    void successfulDescriptionCheck() {
        wikiPage.openSearch()
                .searchText("Selenium software")
                .checkDescriptionResult();
    }

    @Test
    void unSuccessfulSearchTest405() {
        wikiPage.openSearch()
                .searchText("Selenium software")
                .clickResult()
                .checkDetailedDescriptionResult();
    }

    @Test
    void unSuccessfulSearchTest4052() {

        $(id("org.wikipedia.alpha:id/option_label")).shouldHave(Condition.text("1. English"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(Condition.text("New ways to explore"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(Condition.text("Reading lists with sync"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(Condition.text("Data & Privacy"));

    }

}