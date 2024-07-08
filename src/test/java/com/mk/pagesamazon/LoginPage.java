package com.mk.pagesamazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mk.utilities.CommonMethods;

public class LoginPage {

	WebDriver driver = null;
	CommonMethods common = new CommonMethods();

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='nav-link-accountList']")
	private WebElement accountsHover;

	@FindBy(xpath = "//div[@id='nav-flyout-ya-signin']//span[text()='Sign in']")
	private WebElement signInBtn;

	@FindBy(xpath = "//input[@id='ap_email_login']//preceding::label")
	private WebElement emailmobileLabel;

	@FindBy(xpath = "//input[@id='ap_email_login']")
	private WebElement emailmobileInputField;

	@FindBy(xpath = "//input[@id='continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "//input[@id='ap_password']//preceding::label")
	private WebElement passwordLabel;

	@FindBy(xpath = "//input[@id='ap_password']")
	private WebElement passwordInputField;

	public void loginToApplication() {

		driver.get(common.getTheSpecifiedProperty("appliactionURL"));

	}

	public boolean clickOnTheSpecifiedButton(String btnName) {
		boolean flag = false;

		try {
			switch (btnName) {
			case "SignIn":
				Actions action = new Actions(driver);
				action.moveToElement(accountsHover).build().perform();
				signInBtn.click();
				flag = true;
				break;
			case "Continue":
				continueBtn.click();
				flag = true;
				break;
			default:
				throw new Exception("Invalid Option");
			}

		} catch (Exception e) {

			e.printStackTrace();
			common.resultStatus("FAIL", e.getMessage());
		}

		return flag;
	}

	public boolean verifyIfLoginPageDisplayed() {
		boolean flag = false;

		try {
			common.expectedConditions(emailmobileLabel, "Visibility", 30);
			String emailLabel = emailmobileLabel.getText();
			if (emailLabel.contains("Email or mobile phone number")) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			common.resultStatus("FAIL", e.getMessage());
		}

		return flag;

	}

	public boolean enterTheTextInField(String boxName) {
		boolean flag = false;

		try {
			switch (boxName) {
			case "Email":
				emailmobileInputField.click();
				emailmobileInputField.clear();
				emailmobileInputField.sendKeys(common.getTheSpecifiedProperty("email"));
				flag = true;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			common.resultStatus("FAIL", e.getMessage());
		}

		return flag;

	}

	public boolean verifyIfPasswordPageDisplayed() {
		boolean flag = false;

		try {
			common.expectedConditions(passwordLabel, "Visibility", 30);
			String pwdLabel = passwordLabel.getText();
			if (pwdLabel.contains("Password")) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			common.resultStatus("FAIL", e.getMessage());
		}

		return flag;

	}

}
