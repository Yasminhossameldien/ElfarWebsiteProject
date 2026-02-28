package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.ByteArrayInputStream;
import java.util.Arrays;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setupBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized", "--disable-blink-features=AutomationControlled")));
    }

    @AfterMethod
    public void takeScreenshotAndCloseContext(ITestResult result) {
        if (page != null) {
            String status = result.getStatus() == ITestResult.SUCCESS ? "PASSED" : "FAILED";
            try {
                page.waitForLoadState(LoadState.NETWORKIDLE);
                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
                Allure.addAttachment("Final Screenshot - " + status, new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        if (page != null) page.close();
        if (context != null) context.close();
    }

    @AfterClass
    public void tearDownPlaywright() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}