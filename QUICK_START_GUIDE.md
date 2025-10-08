# Flipkart Automation - Quick Start Guide

## 🚀 Quick Setup (5 Minutes)

### Step 1: Prerequisites
```bash
# Verify Java installation
java -version  # Should be Java 17+

# Verify Maven installation
mvn -version   # Should be Maven 3.6+
```

### Step 2: Configure Credentials
Edit `src/test/resources/Config.properties`:
```properties
FlipkartUrl = https://www.flipkart.com/
FlipkartEmail = your_actual_email@example.com
FlipkartPassword = your_actual_password
```

### Step 3: Run Tests
```bash
# Option 1: Run via Maven
mvn clean test

# Option 2: Run via TestNG XML
mvn test -DsuiteXmlFile=testng.xml

# Option 3: Run from IDE
# Right-click FlipkartEndToEnd.java → Run As TestNG Test
```

## 📋 What Gets Tested

| Priority | Test Name | What It Does | Login Required? |
|----------|-----------|--------------|-----------------|
| 1 | closeLoginPopup | Close Flipkart login popup | No |
| 2 | searchForIphone | Search for "iPhone 15" | No |
| 3 | selectFirstIphone | Click first product | No |
| 4 | switchToProductTab | Switch to product window | No |
| 5 | addIphoneToCart | Add product to cart | No |
| 6 | verifyProductInCart | Verify item in cart | No |
| 7 | proceedToCheckout | Navigate to checkout | **Yes** |
| 8 | verifyOrderInHistory | Check My Orders page | **Yes** |
| 9 | cancelOrder | Cancel the order | **Yes** (Disabled) |

## ⚠️ Important Notes

### Login Requirement
Tests 1-6 work WITHOUT login (guest mode).
Tests 7-9 require valid Flipkart credentials.

### Current Test Behavior
- Tests run but skip checkout/order steps if not logged in
- Error messages are logged but tests don't fail
- This allows basic cart functionality testing

## 🔧 Customization

### Change Product Search
Edit `FlipkartEndToEnd.java`:
```java
@Test(priority = 2)
public void searchForIphone() throws Exception {
    homePage.searchFor("Samsung Galaxy S24");  // Change product here
    Thread.sleep(2000);
}
```

### Change Browser
Edit `src/test/resources/Config.properties`:
```properties
browser = firefox  # chrome, firefox, or ie
```

### Add Full Login Flow
Edit `FlipkartEndToEnd.java`:
```java
@BeforeMethod
public void setup() throws Exception {
    setupFlipkartTest();
    
    // Add login here
    String email = PropertiesOperation.getPropertiesValueByKey("FlipkartEmail");
    String password = PropertiesOperation.getPropertiesValueByKey("FlipkartPassword");
    loginPage.login(email, password);
    
    System.out.println("Logged in and ready");
}
```

## 📊 Understanding Test Results

### Success Indicators
```
✅ "Login popup closed"
✅ "Searched for iPhone"
✅ "Clicked on first iPhone product"
✅ "Switched to product window"
✅ "iPhone added to cart"
✅ "Verified: Product is in cart"
```

### Expected Messages (Without Login)
```
⚠️ "Checkout requires login: ..."
⚠️ "Unable to verify orders (requires login): ..."
```

### Actual Failures
```
❌ Element not found errors
❌ Timeout exceptions
❌ Assertion failures in verifyProductInCart
```

## 🐛 Common Issues & Fixes

### Issue 1: ChromeDriver Not Found
```bash
# Solution: WebDriverManager handles this automatically
# But if issue persists:
mvn clean install
```

### Issue 2: Element Not Found
```
Cause: Flipkart UI changed
Fix: Update XPath in respective Page Object
Example: FlipkartSearchResultsPage.java line 17
```

### Issue 3: Timeout Errors
```
Cause: Slow network or page load
Fix: Increase wait time in page object constructor
Change: Duration.ofSeconds(10) → Duration.ofSeconds(20)
```

### Issue 4: Popup Handling Issues
```
Cause: Popup doesn't appear or has different locator
Fix: Already handled with try-catch
Check console logs for details
```

## 📁 File Reference

### Page Objects Location
```
src/main/java/Flipkart/
├── FlipkartCommonMethod.java      ← Browser setup
├── FlipkartLoginPage.java         ← Login methods
├── FlipkartHomePage.java          ← Search methods
├── FlipkartSearchResultsPage.java ← Product selection
├── FlipkartProductPage.java       ← Add to cart
├── FlipkartCartPage.java          ← Cart operations
├── FlipkartCheckoutPage.java      ← Checkout flow
└── FlipkartMyOrdersPage.java      ← Order verification
```

### Test Location
```
src/test/java/Flipkart/
└── FlipkartEndToEnd.java          ← Main test class
```

### Config Location
```
src/test/resources/
└── Config.properties              ← Settings & credentials
```

## 🎯 Quick Debug

### Enable Verbose Logging
Tests already have `System.out.println()` statements.
Check console output for step-by-step execution.

### Check Element Locators
Open Chrome DevTools (F12) on Flipkart:
1. Press Ctrl+F in Elements tab
2. Paste XPath from code (e.g., `//input[@name='q']`)
3. Verify element is found

### Run Single Test
```java
@Test(priority = 6, enabled = true)  // Enable only this
public void verifyProductInCart() throws Exception {
    // ... test code
}

@Test(priority = 7, enabled = false)  // Disable others
```

## 📈 Success Metrics

After running tests, check:
- ✅ Browser launches successfully
- ✅ Navigates to Flipkart
- ✅ Search works
- ✅ Product page loads
- ✅ Cart shows product
- ✅ No unhandled exceptions

## 🔄 Continuous Improvement

### Update Locators Regularly
Flipkart updates UI frequently. When tests fail:
1. Check Flipkart website manually
2. Inspect element in browser
3. Update XPath in respective Page Object
4. Re-run test

### Monitor Test Execution Time
Typical execution: 30-45 seconds for tests 1-6
If slower: Optimize waits in page objects

## 📞 Need Help?

### Check Documentation
1. `FLIPKART_TEST_README.md` - Comprehensive guide
2. `FLIPKART_ARCHITECTURE.md` - Technical details
3. Code comments in each file

### Common Commands
```bash
# Clean build
mvn clean

# Compile only
mvn compile

# Run tests with logging
mvn test -X

# Skip tests during build
mvn clean install -DskipTests
```

---

**Ready to Go!** Run `mvn test` and watch the automation in action! 🎉
