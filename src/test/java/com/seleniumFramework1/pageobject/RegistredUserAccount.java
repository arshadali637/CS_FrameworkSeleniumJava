package com.seleniumFramework1.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistredUserAccount {

	// 1. create object of webdriver
	WebDriver ldriver;

	// constructor
	public RegistredUserAccount(WebDriver rdriver) {
		ldriver = rdriver;

		PageFactory.initElements(rdriver, this);
	}

	// identify webelements
	@FindBy(xpath = "//*[@id=\"center_column\"]/p[1]")
	WebElement MyAccountTitle;

	@FindBy(xpath = "//a[@title='View my customer account']")
	WebElement userName;

	@FindBy(linkText = "Sign out")
	WebElement signOut;

	@FindBy(name = "search_query")
	WebElement searchBox;

	@FindBy(name = "submit_search")
	WebElement submit_search;

	@FindBy(linkText = "Women")
	WebElement WomenMenu;

	@FindBy(linkText = "T-shirts")
	WebElement TShirtMenu;

	public String geAccountTitle() {
		String text = MyAccountTitle.getText();
		return text;
	}

	public String getUserName() {
		String text = userName.getText();
		return text;
	}

	public void clickOnSignOut() {
		signOut.click();
	}

	public void EnterDataInSearchBox(String searchKey) {
		searchBox.sendKeys(searchKey);
	}

	public void ClickOnSearchButton() {
		submit_search.click();

	}

}
