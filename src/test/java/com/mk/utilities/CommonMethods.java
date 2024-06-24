package com.mk.utilities;

import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class CommonMethods {

	DriverInitialisation driverinitilaise = new DriverInitialisation();
	private WebDriver driver = driverinitilaise.getDriver();
	Assert hardassert;
	SoftAssert softassert = new SoftAssert();

	public void resultStatus(String status, String message) {
		try {
			String image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			if ("PASS".equals(status)) {
				DriverInitialisation.testRep.log(Status.PASS, message,
						MediaEntityBuilder.createScreenCaptureFromBase64String(image).build());
			} else if ("FAIL".equals(status)) {
				DriverInitialisation.testRep.log(Status.FAIL, message,
						MediaEntityBuilder.createScreenCaptureFromBase64String(image).build());
			} else if ("INFO".equals(status)) {
				DriverInitialisation.testRep.log(Status.INFO, message,
						MediaEntityBuilder.createScreenCaptureFromBase64String(image).build());
			} else if ("SKIP".equals(status)) {
				DriverInitialisation.testRep.log(Status.SKIP, message,
						MediaEntityBuilder.createScreenCaptureFromBase64String(image).build());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void hardAssert(boolean bool, String passmsg) {
		assertTrue(bool);
		resultStatus("PASS", passmsg);
	}

	public void softAssert(boolean bool) {
		softassert.assertTrue(bool);
	}

	public void expectedConditions(WebElement element, String condition, long time) {
		WebDriverWait wait = new WebDriverWait(driverinitilaise.getDriver(), Duration.ofSeconds(time));
		switch (condition) {
		case "Visibility":
			wait.until(ExpectedConditions.visibilityOf(element));
			break;
		}
	}

	public String getTheSpecifiedProperty(String key) {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Configuration\\Amazon.properties";
		String value = null;
		try {
			FileReader reader = new FileReader(path);
			Properties props = new Properties();
			props.load(reader);
			value = props.getProperty(key);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

}
