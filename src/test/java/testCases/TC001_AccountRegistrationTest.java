package testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void verify_account_regestration() throws IOException {
        logger.info("***** Starting TC001_AccountRegistrationTest *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount Link ");

            hp.clickRegister();
            logger.info("Clicked on Register Link ");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            logger.info("Providing customer details....");
            regpage.setFirstName(randomeString().toUpperCase());
            regpage.setlastName(randomeString().toUpperCase());
            regpage.setEmail(randomeString() + "@gmail.com");
            regpage.setTelephone(randomeNumber());

            String password = randomeAlphaNumberic();
            regpage.setPassword(password);
            regpage.setConfirmPassword(password);

            regpage.clickPrivacyPolicy();
            regpage.clickContinue();

            logger.info("Waiting for confirmation message...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));

            String confmsg = regpage.getConfirmationMsg();
            logger.info("Confirmation message received: " + confmsg);

            if ("Your Account Has Been Created!".equals(confmsg)) {
                logger.info("Account registration successful.");
                Assert.assertTrue(true);
            } else {
                logger.error("Test failed. Expected message not found.");
                captureScreen("AccountRegistrationFail");
                Assert.fail("Expected: 'Your Account Has Been Created!', but got: '" + confmsg + "'");
            }

        } catch (Exception e) {
            logger.error("Exception occurred during test: " + e.getMessage());
            captureScreen("AccountRegistrationException");
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC001_AccountRegistrationTest *****");
    }
}
