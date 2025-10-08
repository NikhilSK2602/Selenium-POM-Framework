# Flipkart Automation Test Architecture

## Project Structure Diagram

```
Selenium-POM-Framework/
│
├── src/
│   ├── main/java/
│   │   └── Flipkart/                          # Page Object Package
│   │       ├── FlipkartCommonMethod.java      # Base class with setup/teardown
│   │       ├── FlipkartLoginPage.java         # Login & popup handling
│   │       ├── FlipkartHomePage.java          # Search functionality
│   │       ├── FlipkartSearchResultsPage.java # Product selection
│   │       ├── FlipkartProductPage.java       # Add to cart
│   │       ├── FlipkartCartPage.java          # Cart operations
│   │       ├── FlipkartCheckoutPage.java      # Checkout flow
│   │       └── FlipkartMyOrdersPage.java      # Order verification
│   │
│   └── test/java/
│       └── Flipkart/
│           └── FlipkartEndToEnd.java          # E2E Test Orchestration
│
├── testng.xml                                  # TestNG Suite Configuration
├── Config.properties                           # Environment Configuration
└── FLIPKART_TEST_README.md                    # Documentation
```

## Test Flow Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                    FlipkartEndToEnd.java                        │
│                    (Test Orchestrator)                          │
└─────────────────────────────────────────────────────────────────┘
                              │
                              │ extends
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│                FlipkartCommonMethod.java                        │
│                      (Base Class)                               │
│  • launchBrowser()                                              │
│  • navigateToFlipkart()                                         │
│  • initializePageObjects()                                      │
│  • setupFlipkartTest()                                          │
│  • cleanUp()                                                    │
└─────────────────────────────────────────────────────────────────┘
                              │
                              │ initializes
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│                     Page Objects Layer                          │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  FlipkartLoginPage          FlipkartHomePage                   │
│  • closeLoginPopupIfPresent  • closeLoginPopupIfPresent        │
│  • login()                   • searchFor()                     │
│                                                                 │
│  FlipkartSearchResultsPage  FlipkartProductPage                │
│  • clickFirstProduct()       • switchToProductWindow()         │
│                              • addToCart()                      │
│                                                                 │
│  FlipkartCartPage           FlipkartCheckoutPage               │
│  • isProductInCart()         • selectExistingAddress()         │
│  • proceedToCheckout()       • selectCashOnDelivery()          │
│                              • confirmOrder()                   │
│                                                                 │
│  FlipkartMyOrdersPage                                          │
│  • navigateToOrders()                                          │
│  • verifyOrderExists()                                         │
│  • cancelOrder()                                               │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

## Test Execution Flow

```
@BeforeMethod: setupFlipkartTest()
       │
       ├─> launchBrowser()          [Chrome/Firefox/IE]
       ├─> navigateToFlipkart()     [https://www.flipkart.com]
       └─> initializePageObjects()  [Create all page object instances]
       
       ▼

Test Priority 1: closeLoginPopup()
       │
       └─> homePage.closeLoginPopupIfPresent()
       
       ▼

Test Priority 2: searchForIphone()
       │
       └─> homePage.searchFor("iPhone 15")
       
       ▼

Test Priority 3: selectFirstIphone()
       │
       └─> searchResultsPage.clickFirstProduct()
       
       ▼

Test Priority 4: switchToProductTab()
       │
       └─> productPage.switchToProductWindow()
       
       ▼

Test Priority 5: addIphoneToCart()
       │
       └─> productPage.addToCart()
       
       ▼

Test Priority 6: verifyProductInCart()
       │
       └─> Assert.assertTrue(cartPage.isProductInCart())
       
       ▼

Test Priority 7: proceedToCheckout()
       │
       └─> cartPage.proceedToCheckout()
       
       ▼

Test Priority 8: verifyOrderInHistory()
       │
       ├─> myOrdersPage.navigateToOrders()
       └─> myOrdersPage.verifyOrderExists()
       
       ▼

Test Priority 9: cancelOrder() [Optional - Disabled]
       │
       └─> myOrdersPage.cancelOrder()
       
       ▼

@AfterMethod: tearDown()
       │
       └─> cleanUp()  [driver.quit()]
```

## Page Object Model Pattern

```
┌──────────────────────────────────────────────────────────────┐
│                      Page Object                             │
├──────────────────────────────────────────────────────────────┤
│                                                              │
│  WebDriver driver                    (Instance variable)    │
│  WebDriverWait wait                  (Explicit wait)        │
│                                                              │
│  @FindBy WebElement elements         (Locators)             │
│                                                              │
│  Constructor(WebDriver driver)       (Initialization)       │
│    • this.driver = driver                                   │
│    • this.wait = new WebDriverWait(driver, Duration)        │
│    • PageFactory.initElements(driver, this)                 │
│                                                              │
│  public void actionMethods()         (Page Actions)         │
│    • wait.until(ExpectedConditions...)                      │
│    • element.click()                                        │
│    • element.sendKeys()                                     │
│    • Exception handling                                     │
│                                                              │
└──────────────────────────────────────────────────────────────┘
```

## Wait Strategy

```
Implicit Wait (30 seconds)
    └─> Set in FlipkartCommonMethod.launchBrowser()
    
Explicit Wait (10-15 seconds per page)
    └─> WebDriverWait with ExpectedConditions
        ├─> elementToBeClickable()
        ├─> visibilityOf()
        └─> visibilityOfAllElements()
        
Thread.sleep() (Strategic pauses)
    └─> After major actions
        ├─> Page navigation (2-3 seconds)
        ├─> Popup closing (1 second)
        └─> Cart updates (2 seconds)
```

## Error Handling Pattern

```
try {
    wait.until(ExpectedConditions.visibilityOf(element))
    element.click()
    Thread.sleep(waitTime)  // Stabilization
} catch (Exception e) {
    System.out.println("Descriptive error: " + e.getMessage())
    // Graceful continuation or failure
}
```

## Configuration Flow

```
Config.properties
    │
    ├─> browser = chrome
    ├─> FlipkartUrl = https://www.flipkart.com/
    ├─> FlipkartEmail = user@example.com
    └─> FlipkartPassword = password
            │
            ▼
    PropertiesOperation.getPropertiesValueByKey()
            │
            ▼
    FlipkartCommonMethod reads configuration
            │
            ▼
    WebDriverManager.chromedriver().setup()
            │
            ▼
    driver = new ChromeDriver()
```

## Key Design Decisions

### 1. Separation of Concerns
- Page Objects: Define elements and actions
- Test Class: Orchestrate test flow
- Common Method: Browser and setup management

### 2. Reusability
- All page objects are reusable across tests
- Common base class reduces code duplication
- Configuration-driven execution

### 3. Maintainability
- Centralized element locators
- Easy to update when UI changes
- Clear method naming conventions

### 4. Robustness
- Multiple fallback locators
- Comprehensive exception handling
- Flexible wait strategies

### 5. Testability
- Independent test methods
- Priority-based execution
- Optional tests can be disabled

## Comparison with Existing Framework

```
SauceDemo (FlowCheck/EndToEnd.java)  ←→  Flipkart (Flipkart/FlipkartEndToEnd.java)
────────────────────────────────────────────────────────────────────────────────
CommonMethod extends ObjectRepo       ←→  FlipkartCommonMethod (standalone)
testBase.CommonMethod base class      ←→  Flipkart.FlipkartCommonMethod
PageObject.ProductPage                ←→  Flipkart.FlipkartProductPage
@BeforeMethod/@AfterMethod            ←→  @BeforeMethod/@AfterMethod
Priority-based test execution         ←→  Priority-based test execution
Implicit + manual waits               ←→  Implicit + Explicit waits + Thread.sleep
```

## Dependencies

```
Maven Dependencies (pom.xml)
│
├─> selenium-java (4.22.0)
├─> testng (7.10.0)
├─> webdrivermanager (5.9.1)
└─> extentreports (5.1.2)
```

## Future Enhancement Opportunities

1. **Login Integration**
   - Implement actual login in @BeforeMethod
   - Secure credential management

2. **Data-Driven Testing**
   - Excel/CSV for multiple products
   - TestNG DataProvider

3. **Reporting**
   - Extent Reports integration
   - Screenshot on failure

4. **Parallel Execution**
   - TestNG parallel configuration
   - ThreadLocal WebDriver

5. **CI/CD Integration**
   - Jenkins pipeline
   - Docker containerization
