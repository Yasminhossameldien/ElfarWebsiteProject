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

                .setViewportSize(414, 896));
        page = context.newPage();
        mobileView = new WebResponsivePage(page);
    }

    @Test
    public void testIPhone11VisualValidation() {
        SoftAssert softAssert = new SoftAssert();
        mobileView.navigateToHomePage();
        softAssert.assertTrue(mobileView.isLogoVisible(), "Logo is NOT visible!");page.reload();
        softAssert.assertTrue(mobileView.isProfileIconClickable(), "Error: Profile Icon is overlapped or disabled!");
        softAssert.assertFalse(mobileView.isHorizontalScrollPresent(), "Horizontal scroll detected!");
        softAssert.assertAll();
        mobileView.takeScreenshot("WebResponsive_Check");


    }
}