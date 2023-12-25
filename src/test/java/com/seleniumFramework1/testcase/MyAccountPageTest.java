package com.seleniumFramework1.testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.seleniumFramework1.pageobject.AccountCreationDetails;
import com.seleniumFramework1.pageobject.IndexPage;
import com.seleniumFramework1.pageobject.MyAccountPage;
import com.seleniumFramework1.pageobject.RegistredUserAccount;

public class MyAccountPageTest extends BaseClass {

	@Test(enabled = false) // enabled=false disable the test case true enable the test case
	public void verifyregistrationAndLogin() {

		logger.info("***************TestCase Verify Registration and Login starts*****************");

		IndexPage pg = new IndexPage(driver);
		pg.clickOnSignIn();
		logger.info("clicked on sign in button");

		MyAccountPage ma = new MyAccountPage(driver);
		ma.enterCreateEmailAddress("arshadnt19@gmail.com");
		logger.info("email address entered in create account section");

		ma.clickSubmitCreate();
		logger.info("clicked on create an account button");

		// account creation
		AccountCreationDetails accCreationPg = new AccountCreationDetails(driver);
		accCreationPg.selectTitleMrs();
		accCreationPg.enterFirstName("shahil");
		accCreationPg.enterLastName("khan");
		accCreationPg.enterPassword("arshad4526");

		logger.info("entered user details");
		accCreationPg.clickOnRegister();

		logger.info("clicked on registered button");

		RegistredUserAccount regUser = new RegistredUserAccount(driver);
		String expTitle = "Your account has been created.";
		String ActualTitle = regUser.geAccountTitle();
		Assert.assertEquals(ActualTitle, expTitle);

		logger.info("***************TestCase Verify Registration and Login ends*****************");

	}

	@Test(enabled=true)
	public void verifyLogin() throws IOException {

		logger.info("***************TestCase VerifyLogin starts*****************");

		IndexPage pg = new IndexPage(driver);
		pg.clickOnSignIn();
		logger.info("clicked on sign in link");

		MyAccountPage ma = new MyAccountPage(driver);

		ma.enterEmailAddress("arshadnt19@gmail.com");
		logger.info("entered email address");

		ma.enterPassword("arshad4526");
		logger.info("entered password");

		ma.clickSubmit();
		logger.info("clicked on sign in button");

		RegistredUserAccount regUser = new RegistredUserAccount(driver);
		String userName = regUser.getUserName();

		// verifying after login
		if (userName.equals("Shahil khan")) {
			logger.info("verifyLogin - Passed");
			Assert.assertTrue(true);
		} else {
			logger.info("verifyLogin - Failed");
			captureScreenShot(driver, "verifyLogin");
			Assert.assertTrue(false);
		}

		logger.info("***************TestCase Verify login ends*****************");

	}

	@Test(enabled=true)
	public void VerifySignOut() throws IOException {

		logger.info("***************TestCase Verify Sign out starts*****************");

		IndexPage pg = new IndexPage(driver);

		pg.clickOnSignIn();
		logger.info("Clicked on sign in link");

		MyAccountPage myAcpg = new MyAccountPage(driver);

		myAcpg.enterEmailAddress("arshadnt19@gmail.com");
		logger.info("Entered email address");

		myAcpg.enterPassword("arshad4526");
		logger.info("Entered password");

		myAcpg.clickSubmit();
		logger.info("Clicked on sign in link..");

		RegistredUserAccount regUser = new RegistredUserAccount(driver);
		regUser.clickOnSignOut();

		if (pg.getPageTitle().equals("Login - My Shop")) {
			logger.info("VerifySignOut - Passed");
			Assert.assertTrue(true);
		}

		else {
			logger.info("VerifySignOut - Failed");
			captureScreenShot(driver, "VerifySignOut");
			Assert.assertTrue(false);
		}

		logger.info("***************TestCase Verify Sign out ends*****************");

	}

}
