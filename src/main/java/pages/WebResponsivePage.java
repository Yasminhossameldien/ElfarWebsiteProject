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
        page.reload();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        for (int i = 0; i < 5; i++) {
            page.evaluate("window.scrollBy(0, window.innerHeight)");
            page.waitForLoadState(LoadState.NETWORKIDLE);
        }
    }

    public boolean isLogoVisible() {
        return page.getByAltText("logo").first().isVisible();
    }

    public boolean isProfileIconVisible() {
        return page.locator(profileIconSelector).first().isVisible();
    }

    public boolean isProfileIconClickable() {
        return page.locator(profileIconSelector).first().isVisible() &&
                page.locator(profileIconSelector).first().isEnabled();
    }

    public void scrollThroughPage() {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight )");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public boolean isHorizontalScrollPresent() {
        Object result = page.evaluate("() => document.documentElement.scrollWidth > document.documentElement.clientWidth");
        return result instanceof Boolean && (Boolean) result;
    }
}