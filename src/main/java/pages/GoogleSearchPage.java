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
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
    public String getPageTitle(String expectedText) {
        page.waitForFunction("document.title.toLowerCase().includes('" + expectedText.toLowerCase() + "')");
        return page.title();
    }

    public boolean isOfficialResultVisible() {
        try {
            page.locator(officialResultSelector).first().waitFor();
            return page.locator(officialResultSelector).first().isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOfficialResult() {
        Locator officialResult = page.locator(officialResultSelector).first();
        officialResult.waitFor();
        officialResult.click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
}