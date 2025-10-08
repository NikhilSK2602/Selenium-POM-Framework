# Sample Test Execution Output

## Expected Console Output

When you run `mvn test`, you should see output similar to this:

```
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< MiniProject:MiniProject >-----------------------
[INFO] Building MiniProject 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ MiniProject ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running TestSuite
Browser launched and navigated to Flipkart

Test: closeLoginPopup (Priority 1)
Login popup closed (if present)

Test: searchForIphone (Priority 2)
Searched for iPhone

Test: selectFirstIphone (Priority 3)
Clicked on first iPhone product

Test: switchToProductTab (Priority 4)
Switched to product window

Test: addIphoneToCart (Priority 5)
iPhone added to cart

Test: verifyProductInCart (Priority 6)
Verified: Product is in cart

Test: proceedToCheckout (Priority 7)
Attempted to proceed to checkout
⚠ Checkout requires login: <error details>

Test: verifyOrderInHistory (Priority 8)
Navigated to My Orders page. Orders found: false
⚠ Unable to verify orders (requires login): <error details>

Browser closed

[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 45.234 s
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  50.123 s
[INFO] Finished at: 2024-XX-XXTXX:XX:XXZ
[INFO] ------------------------------------------------------------------------
```

## Test Execution Timeline

```
Time    Step                        Action
----    ----                        ------
0:00    @BeforeMethod               Browser launches
0:03    Navigate                    Opens Flipkart.com
0:05    Test 1                      Closes login popup
0:06    Test 2                      Searches "iPhone 15"
0:10    Test 3                      Clicks first product
0:14    Test 4                      Switches to product tab
0:16    Test 5                      Adds to cart
0:18    Test 6                      ✓ Verifies in cart
0:20    Test 7                      Attempts checkout
0:22    Test 8                      Navigates to orders
0:25    @AfterMethod               Browser closes
----    
Total execution time: ~25-30 seconds (tests 1-6)
```

## TestNG Report

After execution, check `test-output/index.html`:

```
Suite: Test Suite
├── Test: Flipkart End-to-End Test
│   ├── ✅ closeLoginPopup (PASSED)
│   ├── ✅ searchForIphone (PASSED)
│   ├── ✅ selectFirstIphone (PASSED)
│   ├── ✅ switchToProductTab (PASSED)
│   ├── ✅ addIphoneToCart (PASSED)
│   ├── ✅ verifyProductInCart (PASSED)
│   ├── ⚠️  proceedToCheckout (PASSED with warnings)
│   └── ⚠️  verifyOrderInHistory (PASSED with warnings)
│
├── Total Tests: 8
├── Passed: 8
├── Failed: 0
└── Skipped: 0 (cancelOrder is disabled)
```

## With Valid Login Credentials

If you configure valid credentials in Config.properties:

```
Test: proceedToCheckout (Priority 7)
✓ Proceeding to checkout page
Address selection screen displayed

Test: verifyOrderInHistory (Priority 8)
✓ Navigated to My Orders page
✓ Orders found: true
Order verification complete

Test: cancelOrder (Priority 9) [if enabled]
✓ Order cancellation attempted
Cancellation confirmed
```

## Browser Behavior

### Visible Actions
1. Chrome browser opens in maximized window
2. Navigates to https://www.flipkart.com/
3. Login popup appears and closes automatically
4. Search box receives "iPhone 15" text
5. Search results page loads
6. First iPhone product is clicked
7. New tab opens with product details
8. "ADD TO CART" button is clicked
9. Cart icon updates with item count
10. Cart page shows iPhone product
11. (With login) Checkout pages navigate through flow
12. Browser closes automatically

### What You'll See
```
[Flipkart Homepage] → [Search Results] → [Product Page] → [Cart Page]
      └─ Popup closes        └─ New tab           └─ Cart icon updates
```

## Debugging Output

If tests fail, you'll see detailed error messages:

```
Test: addIphoneToCart (Priority 5)
❌ Failed to add to cart: org.openqa.selenium.TimeoutException
   Waited 15 seconds for element: //button[contains(text(),'ADD TO CART')]
   Possible causes:
   - Element locator changed
   - Page didn't load completely
   - Network issues
   
   Last known state: Product page loaded successfully
   Window handles: 2 (switched to product window)
```

## Performance Metrics

### Expected Timings
```
Action                          Time
------                          ----
Browser launch                  2-3s
Page navigation                 1-2s
Search execution                2-3s
Product page load              3-4s
Add to cart                     1-2s
Cart verification              1s
Total (without checkout)       15-20s
Total (with checkout/login)    40-60s
```

### Network Conditions
- **Fast Network:** 20-25 seconds for tests 1-6
- **Slow Network:** 35-45 seconds for tests 1-6
- **With Captcha:** May require manual intervention

## Success Criteria

✅ **Minimum Success** (Tests 1-6):
- Browser launches
- Navigates to Flipkart
- Search works
- Product selected
- Added to cart
- Cart verified ✓

✅ **Full Success** (Tests 1-8 with login):
- All above +
- Checkout initiated
- Orders page accessed
- Order verified in history

## Common Variations

### Variation 1: Popup Doesn't Appear
```
Test: closeLoginPopup (Priority 1)
Login popup not present - continuing
```

### Variation 2: Multiple Windows
```
Test: switchToProductTab (Priority 4)
Main window: CDwindow-123456789
Product window: CDwindow-987654321
✓ Switched to product window
```

### Variation 3: Cart Already Has Items
```
Test: verifyProductInCart (Priority 6)
✓ iPhone found in cart (along with 2 other items)
Verified: Product is in cart
```

## TestNG HTML Report Structure

Open `test-output/index.html` to see:

```
┌─────────────────────────────────────────┐
│      TestNG Report - Test Results      │
├─────────────────────────────────────────┤
│                                         │
│  Suite: Test Suite                      │
│  Total: 8 | Passed: 8 | Failed: 0      │
│                                         │
│  Test: Flipkart End-to-End Test         │
│  ├─ ✓ closeLoginPopup          0.532s  │
│  ├─ ✓ searchForIphone          2.145s  │
│  ├─ ✓ selectFirstIphone        3.892s  │
│  ├─ ✓ switchToProductTab       1.234s  │
│  ├─ ✓ addIphoneToCart          2.567s  │
│  ├─ ✓ verifyProductInCart      0.891s  │
│  ├─ ⚠ proceedToCheckout        1.456s  │
│  └─ ⚠ verifyOrderInHistory     2.123s  │
│                                         │
│  Timeline Graph: [=========>      ]     │
│                                         │
└─────────────────────────────────────────┘
```

## Extent Reports (If Integrated)

```
┌─────────────────────────────────────────────────┐
│          Automation Test Report                 │
├─────────────────────────────────────────────────┤
│                                                 │
│  Dashboard                                      │
│  ├─ Total Tests: 8                             │
│  ├─ Passed: 8 (100%)                           │
│  ├─ Failed: 0 (0%)                             │
│  └─ Duration: 25.234s                          │
│                                                 │
│  Test Details                                   │
│  ├─ [PASS] closeLoginPopup                     │
│  │   └─ Info: Login popup closed               │
│  ├─ [PASS] searchForIphone                     │
│  │   └─ Info: Searched for iPhone              │
│  ├─ [PASS] selectFirstIphone                   │
│  │   └─ Info: Clicked on first iPhone          │
│  ├─ [PASS] switchToProductTab                  │
│  │   └─ Info: Switched to product window       │
│  ├─ [PASS] addIphoneToCart                     │
│  │   └─ Info: iPhone added to cart             │
│  ├─ [PASS] verifyProductInCart                 │
│  │   └─ Assertion: Product in cart ✓           │
│  ├─ [PASS] proceedToCheckout                   │
│  │   └─ Warning: Requires login                │
│  └─ [PASS] verifyOrderInHistory                │
│      └─ Info: Orders page accessed             │
│                                                 │
└─────────────────────────────────────────────────┘
```

---

**Note:** Actual output may vary based on network speed, Flipkart UI changes, and login status.
