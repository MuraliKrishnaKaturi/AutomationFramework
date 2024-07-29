package com.mk.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberPropertiesProvider;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInitialisation extends AbstractTestNGCucumberTests {

	private static WebDriver driver = null;
	private TestNGCucumberRunner testNGCucumberRunner;
	public static ExtentSparkReporter sprkRep;
	public static ExtentReports extRep;
	public static ExtentTest testRep;

	String repName;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		DriverInitialisation.driver = driver;
	}

	@Override
	@BeforeClass(alwaysRun = true)
	public void setUpClass(ITestContext context) {
		XmlTest currentXmlTest = context.getCurrentXmlTest();
		CucumberPropertiesProvider properties = currentXmlTest::getParameter;
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass(), properties);
		setDriver(setUpDriver(properties.get("BrowserName")));
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report" + timeStamp + ".html";
		sprkRep = new ExtentSparkReporter("./reports/" + repName);
		sprkRep.config().setDocumentTitle("WebAutomationProject");
		sprkRep.config().setReportName(context.getName());
		sprkRep.config().setTheme(Theme.DARK);
		extRep = new ExtentReports();
		extRep.attachReporter(sprkRep);
	}

	@Override
	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		if (testNGCucumberRunner == null) {
			return;
		}
		driver.quit();
		extRep.flush();
		testNGCucumberRunner.finish();
	}

	@Override
	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
		// the 'featureWrapper' parameter solely exists to display the feature
		// file in a test report
		testRep = extRep.createTest(pickleWrapper.getPickle().getName());
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
		testRep.createNode(pickleWrapper.getPickle().getName());
	}

	@DataProvider
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}

	public WebDriver setUpDriver(String BrowserName) {
		switch (BrowserName) {
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			setDriver(driver);
			driver.manage().window().maximize();
			break;

		case "EDGE":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			setDriver(driver);
			driver.manage().window().maximize();
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driver = new EdgeDriver();
			setDriver(driver);
			driver.manage().window().maximize();
			break;
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new EdgeDriver();
			setDriver(driver);
			driver.manage().window().maximize();
			break;
		case "SAFARI":
			WebDriverManager.safaridriver().setup();
			driver = new EdgeDriver();
			setDriver(driver);
			driver.manage().window().maximize();
			break;
		case "EDGE_CHROMIUM":
			WebDriverManager.chromiumdriver().setup();
			driver = new EdgeDriver();
			setDriver(driver);
			driver.manage().window().maximize();
			break;

		default:

		}
		return driver;
	}

}
