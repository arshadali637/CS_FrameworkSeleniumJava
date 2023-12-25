package com.seleniumFramework1.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
	
	WebDriver ldriver;

	//create a new account
	@FindBy(id = "email_create")
	WebElement createEmailId;

	@FindBy(id = "SubmitCreate")
	WebElement submitCreate;
	
	//already registred user
	@FindBy(id = "email")
	WebElement registeredUserEmail;
	
	@FindBy(id = "passwd")
	WebElement registeredUserPwd;
	
	@FindBy(id = "SubmitLogin")
	WebElement submitLogin;

	// constructor
	public MyAccountPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public void enterCreateEmailAddress(String emailAdd) {
		createEmailId.sendKeys(emailAdd);
	}
	
	public void clickSubmitCreate() {
		submitCreate.click();
	}
	
	
	public void enterEmailAddress(String email) {
		registeredUserEmail.sendKeys(email);
	}
	
	public void enterPassword(String pwd) {
		registeredUserPwd.sendKeys(pwd);
	}
	
	public void clickSubmit() {
		submitLogin.click();
	}

}
