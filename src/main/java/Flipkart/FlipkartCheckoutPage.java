package Flipkart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FlipkartCheckoutPage {
    WebDriver driver;
    WebDriverWait wait;
    
    // Address fields
    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameField;
    
    @FindBy(xpath = "//input[@name='phone']")
    public WebElement phoneField;
    
    @FindBy(xpath = "//input[@name='pincode']")
    public WebElement pincodeField;
    
    @FindBy(xpath = "//input[@name='addressLine1']")
    public WebElement addressLine1;
    
    @FindBy(xpath = "//input[@name='addressLine2']")
    public WebElement addressLine2;
    
    @FindBy(xpath = "//input[@name='city']")
    public WebElement cityField;
    
    @FindBy(xpath = "//input[@name='state']")
    public WebElement stateField;
    
    @FindBy(xpath = "//button[contains(text(),'DELIVER HERE') or contains(@class,'_2KpZ6l _2ObVJD')]")
    public WebElement deliverHereButton;
    
    @FindBy(xpath = "//button[contains(text(),'CONTINUE')]")
    public WebElement continueButton;
    
    // Payment options
    @FindBy(xpath = "//label[contains(.,'Cash on Delivery')]//input")
    public WebElement codOption;
    
    @FindBy(xpath = "//button[contains(text(),'CONFIRM ORDER')]")
    public WebElement confirmOrderButton;
    
    public FlipkartCheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void selectExistingAddress() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(deliverHereButton));
            deliverHereButton.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("No existing address or failed to select: " + e.getMessage());
        }
    }
    
    public void selectCashOnDelivery() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(codOption));
            codOption.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Failed to select COD: " + e.getMessage());
        }
    }
    
    public void confirmOrder() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
            confirmOrderButton.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Failed to confirm order: " + e.getMessage());
        }
    }
    
    public void clickContinue() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            continueButton.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Continue button not found or not clickable: " + e.getMessage());
        }
    }
}
