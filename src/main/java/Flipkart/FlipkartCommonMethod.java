package Flipkart;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import resuableComponent.PropertiesOperation;

public class FlipkartCommonMethod {
    
    public static WebDriver driver;
    public static FlipkartLoginPage loginPage;
    public static FlipkartHomePage homePage;
    public static FlipkartSearchResultsPage searchResultsPage;
    public static FlipkartProductPage productPage;
    public static FlipkartCartPage cartPage;
    public static FlipkartCheckoutPage checkoutPage;
    public static FlipkartMyOrdersPage myOrdersPage;
    
    public void launchBrowser() throws Exception {
        String browser = PropertiesOperation.getPropertiesValueByKey("browser");
        
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    
    public void navigateToFlipkart() throws Exception {
        String flipkartUrl = PropertiesOperation.getPropertiesValueByKey("FlipkartUrl");
        driver.get(flipkartUrl);
        Thread.sleep(2000);
    }
    
    public void initializePageObjects() {
        loginPage = new FlipkartLoginPage(driver);
        homePage = new FlipkartHomePage(driver);
        searchResultsPage = new FlipkartSearchResultsPage(driver);
        productPage = new FlipkartProductPage(driver);
        cartPage = new FlipkartCartPage(driver);
        checkoutPage = new FlipkartCheckoutPage(driver);
        myOrdersPage = new FlipkartMyOrdersPage(driver);
    }
    
    public void setupFlipkartTest() throws Exception {
        launchBrowser();
        navigateToFlipkart();
        initializePageObjects();
    }
    
    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
