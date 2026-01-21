package tests;

import org.junit.jupiter.api.Test;
import screens.WikipediaPage;

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
    void checkHeaderPages() {

        wikiPage.checkLanguages("1. English")
                .pressContinueButton()
                .checkHeaderPage("New ways to explore")
                .pressContinueButton()
                .checkHeaderPage("Reading lists with sync")
                .pressContinueButton()
                .checkHeaderPage("Data & Privacy");
    }
}