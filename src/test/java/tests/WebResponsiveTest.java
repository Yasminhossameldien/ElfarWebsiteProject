package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
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
        SoftAssert softAssert = new SoftAssert();
        mobileView.navigateToHomePage();
        page.reload();
        softAssert.assertTrue(mobileView.isLogoVisible(), "Logo is NOT visible!");
        softAssert.assertTrue(mobileView.isProfileIconVisible(), "Error: Profile Icon is NOT visible!");
        softAssert.assertTrue(mobileView.isProfileIconClickable(), "Error: Profile Icon is overlapped or disabled!");
        mobileView.scrollThroughPage();
        softAssert.assertFalse(mobileView.isHorizontalScrollPresent(), "Horizontal scroll detected!");
        mobileView.takeScreenshot("WebResponsive_Check");
        softAssert.assertAll();
    }
}