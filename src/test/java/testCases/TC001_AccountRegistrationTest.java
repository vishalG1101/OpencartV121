package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
 
	@Test(groups={"Regression","Master"})
	public void verify_account_regestration()
	{
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
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomeNumber());
		
		String password = randomeAlphaNumberic();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.clickPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validation expected message...");
		String confmsg = regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e) 
		{
			
			Assert.fail();
		}
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
	}
	
	
}
