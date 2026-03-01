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

        softAssert.assertTrue(navigationPage.isPageLoadedSuccessfully(), "Error: Page loaded with 404 or 500!");

        String chosenPageUrl = page.url();
        System.out.println("Recorded Chosen Page URL: " + chosenPageUrl);
        navigationPage.takeScreenshot("screenshots/1_chosen_page.png");

        navigationPage.clickMainCTA();

        softAssert.assertTrue(navigationPage.isPageLoadedSuccessfully(), "Error: Destination page loaded with 404 or 500!");

        String destinationUrl = page.url();
        System.out.println("Recorded Destination URL: " + destinationUrl);
        softAssert.assertTrue(destinationUrl.contains("/auth/login"), "User was not redirected to login page!");
        navigationPage.takeScreenshot("screenshots/wishlist_login_validation.png");

        softAssert.assertAll();
    }
}