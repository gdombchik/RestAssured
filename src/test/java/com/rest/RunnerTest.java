package com.rest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		//format={"pretty","html:target/html/"}, //format is depreciated
		//plugin={"pretty","html:target/html/"},
		plugin={"pretty","json:target/json/output.json","html:target/html/"},
		features={"src/test/resources/"}
		//,tags={"@angularJSWebsite,@angularJSWebsiteTestTheBasics,@angularJSWebsiteTestAddSomeControl"} //using the tags keyword creates a test suite where we can specify the features to run
)	

public class RunnerTest {

}
