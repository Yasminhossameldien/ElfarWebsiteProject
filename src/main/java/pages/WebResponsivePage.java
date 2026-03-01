package pages;

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

    public boolean isLogoVisible() {
        return page.getByAltText("logo").first().isVisible();
    }
}