package tests;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.WebResponsivePage;

public class WebResponsiveTest extends BaseTest {

    private WebResponsivePage mobileView;

    @BeforeMethod
    public void setupMobileContext() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(414, 896)
                .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 15_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.4 Mobile/15E148 Safari/604.1")
                .setDeviceScaleFactor(2)
                .setHasTouch(true));

        page = context.newPage();
        mobileView = new WebResponsivePage(page);
    }

    @Test
    public void testIPhone11VisualValidation() {
        mobileView.navigateToHomePage();

        boolean hasScroll = mobileView.isHorizontalScrollPresent();
        Assert.assertFalse(hasScroll, "Layout Error: Horizontal scroll detected on iPhone 11!");

        Assert.assertTrue(mobileView.areVisibleElementsDisplayed("button"), "Visible buttons error!");
        Assert.assertTrue(mobileView.areVisibleElementsDisplayed("img"), "Visible images error!");

        mobileView.takeScreenshot("WebResponsive_Check");
    }
}