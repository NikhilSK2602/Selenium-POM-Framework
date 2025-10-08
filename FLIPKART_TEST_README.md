# Flipkart End-to-End Automation Tests

## Overview
This project implements comprehensive end-to-end Selenium automation for Flipkart.com, following the Page Object Model (POM) design pattern.

## Test Scenario
The automation covers the following workflow:
1. Launch Flipkart website
2. Handle login popup (close if present)
3. Search for iPhone products
4. Select first product from search results
5. Switch to product detail window
6. Add product to cart
7. Verify product is in cart
8. Proceed to checkout (requires login)
9. Navigate to My Orders page
10. Verify order in history
11. (Optional) Cancel order

## Project Structure

### Page Objects (src/main/java/Flipkart/)
- **FlipkartLoginPage.java** - Handles login functionality and popup management
- **FlipkartHomePage.java** - Search functionality and navigation
- **FlipkartSearchResultsPage.java** - Product selection from search results
- **FlipkartProductPage.java** - Add to cart and window switching
- **FlipkartCartPage.java** - Cart verification and checkout initiation
- **FlipkartCheckoutPage.java** - Address selection and payment options
- **FlipkartMyOrdersPage.java** - Order verification and cancellation
- **FlipkartCommonMethod.java** - Base class with browser setup and page object initialization

### Test Classes (src/test/java/Flipkart/)
- **FlipkartEndToEnd.java** - Complete E2E test with all scenarios

## Configuration

### Config.properties (src/test/resources/Config.properties)
Add your Flipkart credentials:
```properties
# Flipkart Configuration
FlipkartUrl = https://www.flipkart.com/
FlipkartEmail = your_email@example.com
FlipkartPassword = your_password
```

### testng.xml
The test suite is configured in testng.xml:
```xml
<test name="Flipkart End-to-End Test">
  <classes>
    <class name="Flipkart.FlipkartEndToEnd"/>
  </classes>
</test>
```

## Running the Tests

### Prerequisites
1. Java 17 or higher
2. Maven 3.6 or higher
3. Chrome browser installed
4. Valid Flipkart account (for full E2E flow)

### Execute Tests
```bash
# Run all tests
mvn test

# Run specific test suite
mvn test -DsuiteXmlFile=testng.xml

# Run from IDE
# Right-click on FlipkartEndToEnd.java and select "Run as TestNG Test"
```

## Key Features

### Robust Waits
- Explicit waits using WebDriverWait
- ExpectedConditions for element visibility and clickability
- Thread.sleep() for page load stabilization where needed

### Popup Handling
- Automatic detection and closing of login popups
- Try-catch blocks for graceful handling when popups don't appear

### Window Management
- Automatic switching between main and product detail windows
- Window handle tracking

### Assertions
- Verification of product in cart
- Order history validation
- Test status reporting

### Error Handling
- Comprehensive exception handling
- Informative error messages
- Test continues even if optional steps fail

## Test Priorities
Tests are executed in order using TestNG priority attribute:
1. Close login popup (if present)
2. Search for iPhone
3. Select first product
4. Switch to product window
5. Add to cart
6. Verify product in cart
7. Proceed to checkout
8. Verify order in history
9. Cancel order (optional, disabled by default)

## Notes

### Login Requirement
- Tests 7-9 (checkout, order verification, cancellation) require user authentication
- For demo purposes, these steps handle login failures gracefully
- For complete flow, implement login in test setup using FlipkartLoginPage

### Selenium Locators
- XPath used for dynamic Flipkart elements
- Multiple fallback locators for resilience
- CSS selectors where appropriate for performance

### Best Practices Followed
- Page Object Model (POM) design pattern
- Separation of test logic and page objects
- Reusable common methods
- Proper wait strategies
- Clean resource management with @AfterMethod

## Troubleshooting

### Common Issues
1. **Element not found** - Flipkart UI changes frequently; update XPath locators
2. **Login required** - Implement authentication or use existing session
3. **Timeout errors** - Increase wait durations in page objects
4. **Compilation errors** - Existing framework has TestNG scope issues in main package (known issue, not related to Flipkart implementation)

## Comparison with Existing Tests
This implementation follows the same pattern as FlowCheck/EndToEnd.java:
- Extends base class with common setup
- Uses @BeforeMethod and @AfterMethod
- Test priority ordering
- Similar assertion patterns
- Consistent code style

## Future Enhancements
1. Implement actual login flow in @BeforeMethod
2. Add data-driven testing with multiple products
3. Integrate with Extent Reports
4. Add screenshot capture on failure
5. Parallel test execution
6. API integration for test data cleanup
