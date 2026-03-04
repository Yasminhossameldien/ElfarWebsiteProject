package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
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

    public List<String> getBrokenImagesInfo() {
        List<ElementHandle> images = page.querySelectorAll("img");
        List<String> brokenImages = new ArrayList<>();

        for (ElementHandle img : images) {
            try {
                page.waitForFunction("img => img.complete", img);

                Boolean isLoaded = (Boolean) img.evaluate(
                        "img => img.complete && typeof img.naturalWidth != 'undefined' && img.naturalWidth > 0"
                );

                if (!isLoaded) {
                    String src = (String) img.getAttribute("src");
                    brokenImages.add(src != null ? src : "Empty Source");
                }
            } catch (Exception e) {
                String src = (String) img.getAttribute("src");
                brokenImages.add(src);
            }
        }

        System.out.println("Broken Images Count: " + brokenImages.size());
        brokenImages.forEach(System.out::println);

        return brokenImages;
    }
}