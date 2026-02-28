package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class GoogleSearchPage extends BasePage {
    private final String searchFieldSelector = "textarea[name='q'], input[name='q']";
    private final String officialResultSelector = "a[href*='mahmoudelfar.com']";

    public GoogleSearchPage(Page page) {
        super(page);
    }

    public void navigateToGoogle() {
        navigateTo("https://www.google.com");
    }

    public void searchFor(String query) {
        page.locator(searchFieldSelector).fill(query);
        page.keyboard().press("Enter");
    }

    public void clickOfficialResult() {
        Locator officialResult = page.locator(officialResultSelector).first();
        officialResult.waitFor();
        officialResult.click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
}