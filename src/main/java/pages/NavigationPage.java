package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class NavigationPage extends BasePage {
    private final String ramadanIconSelector = "img[alt='Ramadan']";
    private final String mainCTASelector = "img[alt='wishlist-Icon']";



    public NavigationPage(Page page) {
        super(page);
    }

    public void navigateToHomePage() {
        navigateTo("https://mahmoudelfar.com/");
    }

    public void clickRamadanIcon() {
        page.locator(ramadanIconSelector).click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);

    }

    public boolean isPageLoadedSuccessfully() {
        String pageText = page.locator("body").innerText();
        return !pageText.contains("404") && !pageText.contains("500") && !pageText.contains("Not Found");
    }

    public void clickMainCTA() {
        Locator mainCTA = page.locator(mainCTASelector).first();
        mainCTA.waitFor();
        mainCTA.click();
    }



    public boolean isDestinationLoaded() {
        return page.isVisible("button") || page.locator("h1").isVisible();
    }
}