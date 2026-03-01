package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.GoogleSearchPage;
import pages.ImageCheck;
import com.microsoft.playwright.Browser;

public class HomePageTest extends BaseTest {

    private GoogleSearchPage googleSearchPage;
    private ImageCheck imageCheck;

    @BeforeMethod
    public void setupContextAndPages() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36"));
        page = context.newPage();

        googleSearchPage = new GoogleSearchPage(page);
        imageCheck = new ImageCheck(page);
    }

    @Test(priority = 1)
    public void testOfficialSiteSearchAndDomain() {
        SoftAssert softAssert = new SoftAssert();

        googleSearchPage.navigateToGoogle();
        googleSearchPage.searchFor("Mahmoud elfar official website");

        String pageTitle = googleSearchPage.getPageTitle("mahmoud elfar");
        softAssert.assertTrue(pageTitle.toLowerCase().contains("mahmoud elfar"), "Search results page did not load successfully!");

        softAssert.assertTrue(googleSearchPage.isOfficialResultVisible(), "Official site link not found in Google results!");
        googleSearchPage.takeScreenshot("screenshots/google_search_results.png");

        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void SiteOpensAndCorrectDomain() {

        googleSearchPage.navigateToGoogle();
        googleSearchPage.searchFor("Mahmoud elfar official website");

        googleSearchPage.clickOfficialResult();

        String currentUrl = googleSearchPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.equals("https://mahmoudelfar.com/"), "Domain check failed! URL is: " + currentUrl);

        googleSearchPage.takeScreenshot("screenshots/homepage_validation.png");

    }

    @Test(priority = 3)
    public void testImagesIntegrityOnHomepage() {
        imageCheck.navigateToHomePage();
        imageCheck.scrollGradually();

        int brokenImages = imageCheck.getBrokenImagesCount();
        Assert.assertEquals(brokenImages, 0, "Found " + brokenImages + " broken images!");

        imageCheck.takeScreenshot("screenshots/homepage_full_check.png");
    }
}