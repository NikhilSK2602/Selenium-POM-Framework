package Flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class FlipkartSearchResultsPage {
    WebDriver driver;
    By productLinks = By.cssSelector("a._1fQZEK");

    public FlipkartSearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstIphone() {
        List<WebElement> products = driver.findElements(productLinks);
        if (!products.isEmpty()) {
            products.get(0).click();
        }
    }
}