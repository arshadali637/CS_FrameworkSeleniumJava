package com.seleniumFramework1.testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.seleniumFramework1.pageobject.IndexPage;
import com.seleniumFramework1.pageobject.MyAccountPage;
import com.seleniumFramework1.pageobject.OrderAddressPage;
import com.seleniumFramework1.pageobject.OrderConfirmationPage;
import com.seleniumFramework1.pageobject.OrderPaymentPage;
import com.seleniumFramework1.pageobject.OrderShippingPage;
import com.seleniumFramework1.pageobject.OrderSummaryPage;
import com.seleniumFramework1.pageobject.ProductPage;
import com.seleniumFramework1.pageobject.RegistredUserAccount;
import com.seleniumFramework1.pageobject.SearchResultPage;

public class ProductPageTest extends BaseClass {

	@Test(enabled = true)
	public void VerifySearchProduct() throws IOException {
		String searchKey = "T-shirts";
		logger.info("\n***************TestCase Search Product started*****************");

		// Sign in
		IndexPage indexPg = new IndexPage(driver);
		indexPg.clickOnSignIn();

		// Enter account details- email and password
		MyAccountPage pg = new MyAccountPage(driver);
		pg.enterEmailAddress(emailAddress);

		logger.info("User Email and Password entered.");
		pg.enterPassword(password); //password inherited from base class which we call from readconfig file

		pg.clickSubmit();
		logger.info("Sign In link clicked");

		// Enter searchkey in search box
		RegistredUserAccount productPg = new RegistredUserAccount(driver);
		productPg.EnterDataInSearchBox(searchKey);
		productPg.ClickOnSearchButton();

		// Get Name of Searched Product
		SearchResultPage resultPg = new SearchResultPage(driver);

		String SearchResultProductname = resultPg.getSearchResultProductName();

		// Verify that correct Product is displaying after search

		if (SearchResultProductname.contains(searchKey)) {
			logger.info("Search Product testcase - Passed");
			Assert.assertTrue(true);

			productPg.clickOnSignOut();

		} else {
			logger.info("Search Product testcase - Failed");
			captureScreenShot(driver, "VerifySearchProduct");
			Assert.assertTrue(false);

		}

		logger.info("***************TestCase Search Product ends*****************");

	}

	@Test(enabled = true)
	public void VerifyBuyProduct() throws IOException {

		logger.info("\n***************TestCase Buy Product started*****************");

		/*
		 * driver.get(url); logger.info("Url opened");
		 */

		// Sign in
		IndexPage indexPg = new IndexPage(driver);
		indexPg.clickOnSignIn();

		// Enter account details- email and password
		MyAccountPage pg = new MyAccountPage(driver);
		pg.enterEmailAddress(emailAddress);

		logger.info("User Email and Password entered.");
		pg.enterPassword(password);

		pg.clickSubmit();
		logger.info("Sign In link clicked");

		RegistredUserAccount prodCatPg = new RegistredUserAccount(driver);
		prodCatPg.EnterDataInSearchBox("T-shirts");
		logger.info("T-shirt entered in search box");

		prodCatPg.ClickOnSearchButton();
		logger.info("clicked on search button");

		SearchResultPage searchResultPg = new SearchResultPage(driver);
		searchResultPg.ClickOnMoreLink();
		System.out.println("error here "+searchResultPg);
		logger.info("Clicked on more button");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//

		ProductPage prodPg = new ProductPage(driver);
		prodPg.setQuantity("2");
		logger.info("quantity 2 entereed");

		prodPg.setSize("M");
		logger.info("size M entered");

		prodPg.clickOnAddToCart();
		logger.info("Clicked on add to cart");

		prodPg.clickOnProceedToCheckOut();
		logger.info("Clicked on proceed to checkout on product page");

		OrderSummaryPage orderSumPg = new OrderSummaryPage(driver);
		orderSumPg.cickOnProceedToCheckout();
		logger.info("Clicked on proceed to checkout on order summary page");

		OrderAddressPage orderAddPg = new OrderAddressPage(driver);
		orderAddPg.cickOnProceedToCheckout();
		logger.info("Clicked on proceed to checkout on order address page");

		OrderShippingPage orderShippingPg = new OrderShippingPage(driver);
		orderShippingPg.selectTermsOfServices();
		logger.info("selecged term of service check box");

		orderShippingPg.cickOnProceedToCheckout();
		logger.info("Clicked on proceed to checkout on order shipping page");

		OrderPaymentPage orderPaymentPg = new OrderPaymentPage(driver);
		logger.info(orderPaymentPg.getPageTitle());

		orderPaymentPg.clickOnPayByCheque();
		logger.info("Clicked on pay by cheque");

		OrderConfirmationPage orderConfirmPg = new OrderConfirmationPage(driver);
		orderConfirmPg.cickOnConfirmOrder();

		logger.info("Clicked on confirmed order");
		System.out.println("fault here1*******************************************");
		String sucessMsg = orderConfirmPg.getOrderSucessMessage();
		System.out.println(sucessMsg);

		// Assert.assertEquals(sucessMsg, "Your order on My Store is complete.");

		if (sucessMsg.equals("Your order on My Shop is complete.")) {
			logger.info("VerifyBuyProduct - Passed");
			Assert.assertTrue(true);
			System.out.println("title checked");

			orderConfirmPg.clickOnSignOut();
			

		} else {
			logger.info("VerifyBuyProduct - Failed");
			captureScreenShot(driver, "VerifyBuyProduct");
			Assert.assertTrue(false);

		}

		logger.info("***************TestCase BuyProduct ends*****************");

	}

}
