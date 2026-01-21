package screens;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class WikipediaPage {

    private final SelenideElement searchButton = $(accessibilityId("Search Wikipedia")),
            searchInput = $(id("org.wikipedia.alpha:id/search_src_text")),
            resultDescription = $(id("org.wikipedia.alpha:id/page_list_item_description")),
            detailedResultDescription = $(id("pcs-edit-section-title-description")),
            searchResult = $(id("org.wikipedia.alpha:id/page_list_item_title")),
            listOfLanguages = $(id("org.wikipedia.alpha:id/option_label")),
            continueButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")),
            headerWelcomePage = $(id("org.wikipedia.alpha:id/primaryTextView"));

    private final ElementsCollection searchResults = $$(id("org.wikipedia.alpha:id/page_list_item_title"));

    @Step("Открываем поиск")
    public WikipediaPage openSearch() {
        searchButton.click();

        return this;
    }

    @Step("Записываем в строку поиска")
    public WikipediaPage searchText(String searchText) {
        searchInput.sendKeys(searchText);

        return this;
    }

    @Step("Проверяем наличие результатов поиска")
    public WikipediaPage checkSearchResult() {
        searchResults.shouldHave(sizeGreaterThan(0));

        return this;
    }

    @Step("Проверяем найденное описание")
    public WikipediaPage checkDescriptionResult() {
        resultDescription.shouldHave(Condition.text("Testing framework for web applications"));

        return this;
    }

    @Step("Нажимаем на найденный результат")
    public WikipediaPage clickResult() {
        searchResult.click();

        return this;
    }

    @Step("Проверяем детальное описание по найденному результату")
    public WikipediaPage checkDetailedDescriptionResult() {
        detailedResultDescription.shouldHave(Condition.text("Testing framework for web applications"));

        return this;
    }

    @Step("Проверяем наличие языков")
    public WikipediaPage checkLanguages(String language) {
        listOfLanguages.shouldHave(Condition.text(language));

        return this;
    }

    @Step("Нажимаем на кнопку Continue")
    public WikipediaPage pressContinueButton() {
        continueButton.click();

        return this;
    }

    @Step("Проверяем заголовок на странице")
    public WikipediaPage checkHeaderPage(String header) {
        headerWelcomePage.shouldHave(Condition.text(header));

        return this;
    }
}
