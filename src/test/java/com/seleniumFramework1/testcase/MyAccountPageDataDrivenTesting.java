package com.seleniumFramework1.testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.seleniumFramework1.pageobject.AccountCreationDetails;
import com.seleniumFramework1.pageobject.IndexPage;
import com.seleniumFramework1.pageobject.MyAccountPage;
import com.seleniumFramework1.pageobject.RegistredUserAccount;
import com.seleniumFramework1.utilities.ReadExcelFile;

public class MyAccountPageDataDrivenTesting extends BaseClass {

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

	@Test(dataProvider = "LoginDataProvider")
	public void verifyLogin(String userEmail, String userPwd, String expectedUsername) throws IOException {

		logger.info("***************TestCase VerifyLogin starts*****************");

		IndexPage pg = new IndexPage(driver);
		pg.clickOnSignIn();
		logger.info("clicked on sign in link");

		MyAccountPage ma = new MyAccountPage(driver);

		ma.enterEmailAddress(userEmail);
		logger.info("entered email address");

		ma.enterPassword(userPwd);
		logger.info("entered password");

		ma.clickSubmit();
		logger.info("clicked on sign in button");

		RegistredUserAccount regUser = new RegistredUserAccount(driver);
		String userName = regUser.getUserName();

		// verifying after login
		if (userName.equals(expectedUsername)) {
			logger.info("verifyLogin - Passed");
			Assert.assertTrue(true);

			regUser.clickOnSignOut();

		} else {
			logger.info("verifyLogin - Failed");
			captureScreenShot(driver, "verifyLogin");
			Assert.assertTrue(false);
		}

		logger.info("***************TestCase Verify login ends*****************");

	}

	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataProvider() {

		// System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir") + "\\TestData\\MyStoreTestData.xlsx";

		int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData");

		String data[][] = new String[ttlRows - 1][ttlColumns];

		for (int i = 1; i < ttlRows; i++)// rows =1,2
		{
			for (int j = 0; j < ttlColumns; j++)// col=0, 1,2
			{

				data[i - 1][j] = ReadExcelFile.getCellValue(fileName, "LoginTestData", i, j);
			}

		}
		return data;

	}

}
