package Flipkart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class FlipkartProductPage {
    WebDriver driver;
    WebDriverWait wait;
    String mainWindowHandle;
    
    @FindBy(xpath = "//button[contains(text(),'ADD TO CART') or contains(@class,'_2KpZ6l _2U9uOA')]")
    public WebElement addToCartButton;
    
    @FindBy(xpath = "//button[contains(text(),'BUY NOW')]")
    public WebElement buyNowButton;
    
    public FlipkartProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.mainWindowHandle = driver.getWindowHandle();
        PageFactory.initElements(driver, this);
    }
    
    public void switchToProductWindow() {
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
            Thread.sleep(2000); // Wait for page to load
        } catch (Exception e) {
            System.out.println("Failed to switch to product window: " + e.getMessage());
        }
    }
    
    public void addToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            addToCartButton.click();
            Thread.sleep(2000); // Wait for cart to update
        } catch (Exception e) {
            System.out.println("Failed to add to cart: " + e.getMessage());
        }
    }
}
