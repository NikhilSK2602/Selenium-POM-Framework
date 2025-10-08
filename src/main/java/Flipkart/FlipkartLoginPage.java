package Flipkart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FlipkartLoginPage {
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//input[contains(@class,'_2IX_2-') and (@type='text' or @type='tel')]")
    public WebElement emailOrPhone;
    
    @FindBy(xpath = "//input[@type='password']")
    public WebElement password;
    
    @FindBy(xpath = "//button[contains(@class,'_2KpZ6l _2HKlqd')]")
    public WebElement loginButton;
    
    @FindBy(xpath = "//button[contains(text(),'✕')]")
    public WebElement closeLoginPopup;
    
    public FlipkartLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void closeLoginPopupIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeLoginPopup));
            closeLoginPopup.click();
        } catch (Exception e) {
            // Popup not present, continue
        }
    }
    
    public void login(String email, String pwd) {
        try {
            wait.until(ExpectedConditions.visibilityOf(emailOrPhone));
            emailOrPhone.sendKeys(email);
            password.sendKeys(pwd);
            loginButton.click();
        } catch (Exception e) {
            System.out.println("Login failed or already logged in: " + e.getMessage());
        }
    }
}
