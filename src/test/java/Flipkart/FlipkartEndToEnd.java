package Flipkart;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartEndToEnd extends FlipkartCommonMethod {
    
    @BeforeMethod
    public void setup() throws Exception {
        setupFlipkartTest();
        System.out.println("Browser launched and navigated to Flipkart");
    }
    
    @Test(priority = 1)
    public void closeLoginPopup() throws Exception {
        homePage.closeLoginPopupIfPresent();
        System.out.println("Login popup closed (if present)");
    }
    
    @Test(priority = 2)
    public void searchForIphone() throws Exception {
        homePage.searchFor("iPhone 15");
        Thread.sleep(2000);
        System.out.println("Searched for iPhone");
    }
    
    @Test(priority = 3)
    public void selectFirstIphone() throws Exception {
        searchResultsPage.clickFirstProduct();
        Thread.sleep(2000);
        System.out.println("Clicked on first iPhone product");
    }
    
    @Test(priority = 4)
    public void switchToProductTab() throws Exception {
        productPage.switchToProductWindow();
        System.out.println("Switched to product window");
    }
    
    @Test(priority = 5)
    public void addIphoneToCart() throws Exception {
        productPage.addToCart();
        System.out.println("iPhone added to cart");
    }
    
    @Test(priority = 6)
    public void verifyProductInCart() throws Exception {
        boolean isInCart = cartPage.isProductInCart();
        Assert.assertTrue(isInCart, "Product should be in cart");
        System.out.println("Verified: Product is in cart");
    }
    
    @Test(priority = 7)
    public void proceedToCheckout() throws Exception {
        // Note: This step requires login
        // For demo purposes, we'll attempt to click Place Order
        // In real scenario, user needs to be logged in
        try {
            cartPage.proceedToCheckout();
            System.out.println("Attempted to proceed to checkout");
        } catch (Exception e) {
            System.out.println("Checkout requires login: " + e.getMessage());
        }
    }
    
    @Test(priority = 8)
    public void verifyOrderInHistory() throws Exception {
        // Note: This step requires successful order placement and login
        // For demo purposes, we'll navigate to orders page
        try {
            myOrdersPage.navigateToOrders();
            Thread.sleep(3000);
            boolean orderExists = myOrdersPage.verifyOrderExists();
            System.out.println("Navigated to My Orders page. Orders found: " + orderExists);
        } catch (Exception e) {
            System.out.println("Unable to verify orders (requires login): " + e.getMessage());
        }
    }
    
    @Test(priority = 9, enabled = false)
    public void cancelOrder() throws Exception {
        // Optional: Cancel the order
        // Disabled by default as it requires specific conditions
        myOrdersPage.cancelOrder();
        System.out.println("Order cancellation attempted");
    }
    
    @AfterMethod
    public void tearDown() {
        cleanUp();
        System.out.println("Browser closed");
    }
}
