package com.mk.runners;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import com.mk.utilities.DriverInitialisation;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;

@Test
@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/Features/Web/Amazon" }, plugin = { "pretty",
		"html:target/cucumber" }, glue = { "com/mk/testsamazon"}, monochrome= true, dryRun = false)
public class CucumberRunner extends DriverInitialisation {

}