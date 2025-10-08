package Flipkart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FlipkartHomePage {
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//input[@name='q' or @placeholder='Search for Products, Brands and More']")
    public WebElement searchBox;
    
    @FindBy(xpath = "//button[@type='submit' or contains(@class,'L0Z3Qu')]")
    public WebElement searchButton;
    
    @FindBy(xpath = "//button[contains(text(),'✕')]")
    public WebElement closeLoginPopup;
    
    @FindBy(xpath = "//a[contains(@href,'/account/login')]")
    public WebElement loginLink;
    
    public FlipkartHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void closeLoginPopupIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeLoginPopup));
            closeLoginPopup.click();
            Thread.sleep(1000); // Wait for popup to close
        } catch (Exception e) {
            // Popup not present, continue
        }
    }
    
    public void searchFor(String product) {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchBox));
            searchBox.clear();
            searchBox.sendKeys(product);
            searchButton.click();
        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }
}
