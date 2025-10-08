package Flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlipkartCartPage {
    WebDriver driver;
    By cartItemTitle = By.xpath("//a[contains(text(),'iPhone')]");

    public FlipkartCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isIphoneInCart() {
        return driver.findElements(cartItemTitle).size() > 0;
    }
}