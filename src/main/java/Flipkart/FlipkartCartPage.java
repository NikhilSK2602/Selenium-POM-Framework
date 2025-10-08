package Flipkart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FlipkartCartPage {
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//a[contains(text(),'iPhone') or contains(@title,'iPhone')]")
    public WebElement productInCart;
    
    @FindBy(xpath = "//span[contains(text(),'Place Order')]")
    public WebElement placeOrderButton;
    
    @FindBy(xpath = "//button[contains(@class,'_2KpZ6l _2MWPVM')]")
    public WebElement alternatePlaceOrderButton;
    
    public FlipkartCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public boolean isProductInCart() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productInCart));
            return productInCart.isDisplayed();
        } catch (Exception e) {
            System.out.println("Product not found in cart: " + e.getMessage());
            return false;
        }
    }
    
    public void proceedToCheckout() {
        try {
            WebElement checkoutBtn = placeOrderButton.isDisplayed() ? 
                                      placeOrderButton : alternatePlaceOrderButton;
            wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
            checkoutBtn.click();
        } catch (Exception e) {
            System.out.println("Failed to proceed to checkout: " + e.getMessage());
        }
    }
}
