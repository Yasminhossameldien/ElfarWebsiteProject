package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WebResponsivePage extends BasePage {

    public WebResponsivePage(Page page) {
        super(page);
    }

    public void navigateToHomePage() {
        navigateTo("https://mahmoudelfar.com/");
    }

    public boolean isHorizontalScrollPresent() {
        Object result = page.evaluate("() => document.documentElement.scrollWidth > document.documentElement.clientWidth");
        return result instanceof Boolean && (Boolean) result;
    }

    public boolean areVisibleElementsDisplayed(String selector) {
        Locator elements = page.locator(selector);
        int count = elements.count();
        for (int i = 0; i < count; i++) {
            Locator element = elements.nth(i);
            if (element.isVisible() && element.boundingBox() == null) {
                return false;
            }
        }
        return true;
    }
}