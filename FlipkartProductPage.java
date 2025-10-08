package Flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlipkartProductPage {
    WebDriver driver;
    By addToCartBtn = By.xpath("//button[contains(text(),'Add to cart')]");

    public FlipkartProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart() {
        driver.findElement(addToCartBtn).click();
    }
}