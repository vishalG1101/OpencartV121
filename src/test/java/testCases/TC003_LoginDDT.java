package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataDrivern") // getting data provider from another class
    public void verify_loginDDT(String email, String pwd, String exp) {
        logger.info("***** Start TC003_LoginDDT ******");

        try {
            // HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            // LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            // MyAccount
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();

            // Validate login result
            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage) {
                    macc.clickLogout();
                    Assert.assertTrue(true, "Login successful - Test Passed");
                } else {
                    Assert.fail("Expected login success, but login failed.");
                }
            } else if (exp.equalsIgnoreCase("Invalid")) {
                if (!targetPage) {
                    Assert.assertTrue(true, "Login failed as expected - Test Passed");
                } else {
                    macc.clickLogout();
                    Assert.fail("Expected login failure, but login succeeded.");
                }
            } else {
                Assert.fail("Invalid test data category: " + exp);
            }
        } catch (Exception e) {
            logger.error("Test execution failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e);
        }

        logger.info("***** Finished TC003_LoginDDT ******");
    }
}
