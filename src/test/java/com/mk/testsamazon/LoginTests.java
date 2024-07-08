package com.mk.testsamazon;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import com.mk.pagesamazon.LoginPage;
import com.mk.utilities.CommonMethods;
import com.mk.utilities.DriverInitialisation;

import org.openqa.selenium.WebDriver;

public class LoginTests {

	DriverInitialisation driverinitalse = new DriverInitialisation();
	WebDriver driver = driverinitalse.getDriver();
	LoginPage login = new LoginPage(driver);
	CommonMethods common = new CommonMethods();

	@Given("Launch the application")
	public void launch_the_application() {

		login.loginToApplication();
		common.resultStatus("PASS", "Launched the application successfully");

	}

	@Then("Click on the {string} button")
	public void click_on_the_sign_in_on_button(String string) {
		common.hardAssert(login.clickOnTheSpecifiedButton(string), "Clicked on " + string + " successfully");
	}

	@Then("Verify if login page is displayed")
	public void verify_if_login_page_is_displayed() {
		common.hardAssert(login.verifyIfLoginPageDisplayed(), "Login page displayed successfully");
	}

	@Then("Enter the {string} in text field")
	public void enter_the_email_to_login(String string) {
		common.hardAssert(login.enterTheTextInField(string), "Entered the " + string + " in respective field");
	}

	@Then("Verify if password page is displayed")
	public void verify_if_password_page_is_displayed() {
		common.hardAssert(login.verifyIfPasswordPageDisplayed(), "Password page displayed successfully");

	}

}
