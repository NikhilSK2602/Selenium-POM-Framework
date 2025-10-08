package Flipkart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class FlipkartMyOrdersPage {
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//a[contains(@href,'/account/orders')]")
    public WebElement ordersLink;
    
    @FindBy(xpath = "//div[contains(@class,'_2dxPJg')]//a[contains(text(),'iPhone') or contains(@title,'iPhone')]")
    public List<WebElement> orderItems;
    
    @FindBy(xpath = "//div[contains(@class,'_3gijNv')]")
    public List<WebElement> orderCards;
    
    @FindBy(xpath = "//span[contains(text(),'Cancel')]")
    public WebElement cancelButton;
    
    @FindBy(xpath = "//button[contains(text(),'CONFIRM')]")
    public WebElement confirmCancelButton;
    
    public FlipkartMyOrdersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void navigateToOrders() {
        try {
            driver.get("https://www.flipkart.com/account/orders");
            Thread.sleep(3000); // Wait for orders page to load
        } catch (Exception e) {
            System.out.println("Failed to navigate to orders page: " + e.getMessage());
        }
    }
    
    public boolean verifyOrderExists() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(orderCards));
            return orderItems.size() > 0 || orderCards.size() > 0;
        } catch (Exception e) {
            System.out.println("No orders found or page didn't load: " + e.getMessage());
            return false;
        }
    }
    
    public void cancelOrder() {
        try {
            if (cancelButton.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
                cancelButton.click();
                Thread.sleep(1000);
                
                wait.until(ExpectedConditions.elementToBeClickable(confirmCancelButton));
                confirmCancelButton.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("Failed to cancel order or cancel option not available: " + e.getMessage());
        }
    }
}
