package Flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlipkartHomePage {
    WebDriver driver;
    By closeLoginPopupBtn = By.cssSelector("button._2KpZ6l._2doB4z");
    By searchBox = By.name("q");
    By searchBtn = By.cssSelector("button[type='submit']");

    public FlipkartHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeLoginPopupIfPresent() {
        try {
            driver.findElement(closeLoginPopupBtn).click();
        } catch (Exception e) {
            // Ignore if not present
        }
    }

    public void searchFor(String query) {
        driver.findElement(searchBox).sendKeys(query);
        driver.findElement(searchBtn).click();
    }
}