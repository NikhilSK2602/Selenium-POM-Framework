package Flipkart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class FlipkartSearchResultsPage {
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//div[contains(@class,'_1AtVbE') or contains(@class,'_4rR01T')]//a")
    public List<WebElement> productLinks;
    
    @FindBy(xpath = "//div[contains(@class,'_4ddWXP')]//a[contains(@href,'/p/')]")
    public List<WebElement> alternateProductLinks;
    
    public FlipkartSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void clickFirstProduct() {
        try {
            Thread.sleep(2000); // Wait for search results to load
            
            List<WebElement> products = productLinks.size() > 0 ? productLinks : alternateProductLinks;
            
            if (!products.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
                products.get(0).click();
            } else {
                System.out.println("No products found in search results");
            }
        } catch (Exception e) {
            System.out.println("Failed to click first product: " + e.getMessage());
        }
    }
}
