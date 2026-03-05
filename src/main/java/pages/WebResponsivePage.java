package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WebResponsivePage extends BasePage {

    private final String profileIconSelector = "img[alt='Profile Icon']";

    public WebResponsivePage(Page page) {
        super(page);
    }

    public void navigateToHomePage() {
        navigateTo("https://mahmoudelfar.com/");

        page.waitForLoadState(LoadState.NETWORKIDLE);

    }

    public boolean isLogoVisible() {
        return page.getByAltText("logo").first().isVisible();
    }

    public boolean isProfileIconClickable() {
        return page.locator(profileIconSelector).first().isVisible() &&
                page.locator(profileIconSelector).first().isEnabled();
    }

    public boolean isHorizontalScrollPresent() {
        Object result = page.evaluate("() => document.documentElement.scrollWidth > document.documentElement.clientWidth");
        return result instanceof Boolean && (Boolean) result;
    }
}