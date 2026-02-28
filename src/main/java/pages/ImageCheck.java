package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import java.util.List;

public class ImageCheck extends BasePage {

    public ImageCheck(Page page) {
        super(page);
    }

    public void navigateToHomePage() {
        navigateTo("https://mahmoudelfar.com/");
    }

    public void scrollGradually() {
        for (int i = 0; i < 10; i++) {
            page.mouse().wheel(0, 1000);
            page.waitForTimeout(500);
        }
    }

    public int getBrokenImagesCount() {
        List<ElementHandle> images = page.querySelectorAll("img");
        int brokenCount = 0;
        for (ElementHandle img : images) {
            Boolean isLoaded = (Boolean) img.evaluate(
                    "img => img.complete && typeof img.naturalWidth != 'undefined' && img.naturalWidth > 0"
            );
            if (!isLoaded) {
                brokenCount++;
            }
        }
        return brokenCount;
    }
}