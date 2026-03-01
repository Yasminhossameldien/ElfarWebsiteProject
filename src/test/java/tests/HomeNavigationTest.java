package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.NavigationPage;
import com.microsoft.playwright.Browser;

public class HomeNavigationTest  extends BaseTest {

    private NavigationPage navigationPage;

    @BeforeMethod
    public void setupContextAndPages() {
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = context.newPage();
        navigationPage = new NavigationPage(page);
    }

    @Test
    public void testNavigationAndCtaValidation() {
        SoftAssert softAssert = new SoftAssert();

        navigationPage.navigateToHomePage();
        navigationPage.clickRamadanIcon();
        navigationPage.clickMainCTA();
        navigationPage.clickWishlist();

        String alertMessage = navigationPage.getNotificationMessage();
        softAssert.assertEquals(alertMessage, "Please login to add to wishlist", "Validation message mismatch!");

        page.waitForURL("**/auth/login");
        String currentUrl = page.url();
        softAssert.assertTrue(currentUrl.contains("/auth/login"), "User was not redirected to login page!");

        navigationPage.takeScreenshot("screenshots/wishlist_login_validation.png");

        softAssert.assertAll();
    }
}