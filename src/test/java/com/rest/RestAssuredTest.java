package com.rest;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {
		
	@When("^Test the Get Rest method\\.$")
	public void testTheGetmethod(DataTable table) throws Throwable {
		List<List<String>> tableList = table.raw();
		String url = tableList.get(1).get(1);
		String resource = tableList.get(2).get(1);
		String id = tableList.get(3).get(1);
		get(url + resource + id).then().body("id", equalTo(new Long(id).intValue()));
	}
	
	@Then("^Test the Put Rest method\\.$")
	public void testThePutRestMethod(DataTable table) throws Throwable {
		List<List<String>> tableList = table.raw();
		
	}
	
}
