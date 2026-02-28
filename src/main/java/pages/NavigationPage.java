package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class NavigationPage extends BasePage {
    private final String ramadanIconSelector = "img[alt='Ramadan']";
    private final String mainCTASelector = "img.card_productCardImg__2sum2";
    private final String wishlistIconSelector = "img[alt='heart']";
    private final String loginNotificationSelector = ".ant-notification-notice-message";

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

    public void clickMainCTA() {
        page.locator(mainCTASelector).first().click();
    }

    public void clickWishlist() {
        page.locator(wishlistIconSelector).first().click();
    }

    public String getNotificationMessage() {
        page.locator(loginNotificationSelector).waitFor();
        return page.locator(loginNotificationSelector).innerText();
    }

    public boolean isDestinationLoaded() {
        return page.isVisible("button") || page.locator("h1").isVisible();
    }
}