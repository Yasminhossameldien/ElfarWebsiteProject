package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;


public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigateTo(String url) {
        page.navigate(url);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String name) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }

    public String getCurrentUrl() {
        return page.url();
    }
}