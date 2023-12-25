package com.seleniumFramework1.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreationDetails {

	// 1. create object of webdriver
	WebDriver ldriver;

	// constructor
	public AccountCreationDetails(WebDriver rdriver) {
		ldriver = rdriver;

		PageFactory.initElements(rdriver, this);
	}

	// identify webelements
	@FindBy(id = "id_gender2") // Title-Mrs
	WebElement titleMrs;

//	@FindBy(id = "id_gender1") // Title-Mrs
//	WebElement titleMr;

	@FindBy(id = "customer_firstname")
	WebElement firstName;

	@FindBy(id = "customer_lastname")
	WebElement lastName;

	@FindBy(id = "passwd")
	WebElement password;

	@FindBy(id = "submitAccount")
	WebElement register;
	// identify actions to be performed on web elements

	public void selectTitleMrs() {
		titleMrs.click();
	}

//	public void selectTitleMr() {
//		titleMr.click();
//	}

	public void enterFirstName(String fname) {
		firstName.sendKeys(fname);
	}

	public void enterLastName(String lname) {
		lastName.sendKeys(lname);
	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}

	
	public void clickOnRegister() {
		register.click();
	}

}
