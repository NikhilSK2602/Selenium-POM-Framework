package Flipkart;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartEndToEndTest {

    @Test
    public void addIphoneToCart() throws Exception {
        // Setup
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Step 1: Go to Flipkart
        driver.get("https://www.flipkart.com/");

        // Step 2: Close login popup if present
        FlipkartHomePage homePage = new FlipkartHomePage(driver);
        homePage.closeLoginPopupIfPresent();

        // Step 3: Search for iPhone
        homePage.searchFor("iPhone");

        // Step 4: Select the first iPhone from results
        FlipkartSearchResultsPage resultsPage = new FlipkartSearchResultsPage(driver);
        resultsPage.clickFirstIphone();

        // Step 5: Switch to new tab
        for(String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }

        // Step 6: Click 'Add to Cart'
        FlipkartProductPage productPage = new FlipkartProductPage(driver);
        productPage.addToCart();

        // Step 7: Assert item is in cart
        FlipkartCartPage cartPage = new FlipkartCartPage(driver);
        Assert.assertTrue(cartPage.isIphoneInCart(), "iPhone should be present in the cart!");

        // Cleanup
        driver.quit();
    }
}