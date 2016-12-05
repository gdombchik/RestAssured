package com.rest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.rest.dto.PostsDto;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class RestAssuredTest {
	private static final Logger logger = LogManager.getLogger(RestAssuredTest.class.getName());
	private static final String REST_POST_BY_OBJECT_MAPPING = "Object Mapping";
	private static final String REST_POST_BY_VALUE = "JSON String";

	@Given("^Test the Get REST method\\.$")
	public void testTheGetRESTMethod(DataTable table) throws Throwable {
		Map<String,String> data = table.asMap(String.class,String.class);
		String url = data.get("url");
		String resource = data.get("resource");
		String idField = table.raw().get(3).get(0); //id (first column)
		String id = data.get(idField);
		String nameField = table.raw().get(4).get(0);
		String name = data.get(nameField);
		String userNameField = table.raw().get(5).get(0);
		String userName = data.get(userNameField);
		String emailField = table.raw().get(6).get(0);
		String email = data.get(emailField);
		String addressStreetField = table.raw().get(7).get(0);
		String addressStreet = data.get(addressStreetField);
		String addressSuiteField = table.raw().get(8).get(0);
		String addressSuite = data.get(addressSuiteField);
		String addressCityField = table.raw().get(9).get(0);
		String addressCity = data.get(addressCityField);
		String addressZipCodeField = table.raw().get(9).get(0);
		String addressZipCode = data.get(addressZipCodeField);
		String addressGeoLatField = table.raw().get(10).get(0);
		String addressGeoLat = data.get(addressGeoLatField);
		String addressGeoLngField = table.raw().get(11).get(0);
		String addressGeoLng = data.get(addressGeoLngField);
		String phoneField = table.raw().get(12).get(0);
		String phone = data.get(phoneField);
		String websiteField = table.raw().get(13).get(0);
		String website = data.get(websiteField);
		String companyNameField = table.raw().get(14).get(0);
		String companyName = data.get(companyNameField);
		String companyCatchPhraseField = table.raw().get(15).get(0);
		String companyCatchPhrase = data.get(companyCatchPhraseField);
		String companyBsField = table.raw().get(16).get(0);
		String companyBs = data.get(companyBsField);
		
		// HTTP Status Code: 200 OK
		get(url + resource + id).then().statusCode(200)
				.body(idField, equalTo(new Long(id).intValue()),
					 nameField,equalTo(name),
					 userNameField,equalTo(userName),
					 emailField,equalTo(email),
					 addressStreetField,equalTo(addressStreet),
					 addressSuiteField,equalTo(addressSuite),
					 addressCityField,equalTo(addressCity),
					 addressZipCodeField,equalTo(addressZipCode),
					 addressGeoLatField,equalTo(addressGeoLat),
					 addressGeoLngField,equalTo(addressGeoLng),
					 phoneField,equalTo(phone),
					 websiteField,equalTo(website),
					 companyNameField,equalTo(companyName),
					 companyCatchPhraseField,equalTo(companyCatchPhrase),
					 companyBsField,equalTo(companyBs));
	}

	@Given("^Test the Post REST method by ([^\"]*)$")
	public void testThePostRESTMethodBy(String value,
			DataTable table) throws Throwable {
		Map<String,String> data = table.asMap(String.class,String.class);
		String url = data.get("url");
		String resource = data.get("resource"); 
		String userIdField = table.raw().get(3).get(0);
		String userId = data.get(userIdField);
		String idField = table.raw().get(4).get(0);
		String id =  data.get(idField);
		String titleField = table.raw().get(5).get(0);
		String title = data.get(titleField);
		String bodyField = table.raw().get(6).get(0);
		String body = data.get(bodyField);
		String contentType = data.get("content type");
		String jsonSchema = data.get("json schema");

		if (value.equals(RestAssuredTest.REST_POST_BY_OBJECT_MAPPING)) {
			PostsDto postsDto = new PostsDto(userId, id, title, body);

			/*
			 * given().contentType("application/json").body(postsDto)
			 * .when().post(url + resource).then().statusCode(201);
			 */

			/*
			 * given().contentType("application/json").body(postsDto)
			 * .when().post(url + resource).body().print();
			 */

			// HTTP Status Code: 201 Created
			given().contentType(contentType)
					.body(postsDto)
					.when()
					.post(url + resource)
					.then()
					.statusCode(201)
					.body(userIdField, equalTo(new Long(userId).intValue()),
							idField, equalTo(new Long(id).intValue()), titleField,
							equalTo(title), bodyField, equalTo(body)).and()
					.body(matchesJsonSchemaInClasspath(jsonSchema));
		} else if (value.equals(RestAssuredTest.REST_POST_BY_VALUE)) {
			StringBuilder jsonBody = new StringBuilder();
			jsonBody.append("{")
					.append("\"" + userIdField + "\":" + userId + ",")
					.append("\"" + idField + "\":" + id + ",")
					.append("\"" + titleField + "\":\"" + title + "\",")
					.append("\"" + bodyField + "\":\"" + body + "\"")
					.append("}");

			// HTTP Status Code: 201 Created
			given().contentType(contentType)
					.body(jsonBody.toString())
					.when()
					.post(url + resource)
					.then()
					.statusCode(201)
					.body(userIdField, equalTo(new Long(userId).intValue()),
							idField, equalTo(new Long(id).intValue()),
							titleField, equalTo(title), bodyField,
							equalTo(body)).and()
					.body(matchesJsonSchemaInClasspath(jsonSchema));
		}
	}
	
	@Given("^Test the Put REST method\\.$")
	public void testThePutRESTMethod(DataTable table) throws Throwable {
		Map<String,String> data = table.asMap(String.class,String.class);
		String url = data.get("url");
		String resource = data.get("resource"); 
		String userIdField = table.raw().get(3).get(0);
		String userId = data.get(userIdField);
		String idField = table.raw().get(4).get(0);
		String id =  data.get(idField);
		String titleField = table.raw().get(5).get(0);
		String title = data.get(titleField);
		String bodyField = table.raw().get(6).get(0);
		String body = data.get(bodyField);
		String contentType = data.get("content type");
		String jsonSchema = data.get("json schema");
		
		PostsDto postsDto = new PostsDto(userId, id, title, body);
		
		// HTTP Status Code: 200 OK
		// Put - Update/Replace
		given()
		.contentType(contentType)
				.body(postsDto)
				.when()
				.put(url + resource + id) //http://jsonplaceholder.typicode.com/posts/1
				.then()
				.statusCode(200)
				.body(userIdField, equalTo(new Long(userId).intValue()),
						idField, equalTo(new Long(id).intValue()), titleField,
						equalTo(title), bodyField, equalTo(body)).and()
				.body(matchesJsonSchemaInClasspath(jsonSchema));
	}
	
	@Given("^Test the Patch REST method\\.$")
	public void test_the_Patch_REST_method(DataTable table) throws Throwable {
		Map<String,String> data = table.asMap(String.class,String.class);
		String url = data.get("url");
		String resource = data.get("resource"); 
		String userIdField = table.raw().get(3).get(0);
		String userId = data.get(userIdField);
		String idField = table.raw().get(4).get(0);
		String id =  data.get(idField);
		String titleField = table.raw().get(5).get(0);
		String title = data.get(titleField);
		String contentType = data.get("content type");
				
		StringBuilder jsonBody = new StringBuilder();
		jsonBody.append("{")
				.append("\"" + userIdField + "\":" + userId + ",")
				.append("\"" + idField + "\":" + id + ",")
				.append("\"" + titleField + "\":\"" + title + "\"")
				.append("}");
		
		// HTTP Status Code: 200 OK
		// Patch - Update/Modify
		logger.trace(given()
		.contentType(contentType)
				.body(jsonBody.toString())
				.when()
				.patch(url + resource + id) //http://jsonplaceholder.typicode.com/posts/1
				.then()
				.statusCode(200)
				.body(userIdField, equalTo(new Long(userId).intValue()),
						idField, equalTo(new Long(id).intValue()), titleField,
						equalTo(title)).log().body());
	}

	@Given("^Test the Delete REST method\\.$")
	public void testTheDeleteRESTMethod(DataTable table) throws Throwable {
		Map<String,String> data = table.asMap(String.class,String.class);
		String url = data.get("url");
		String resource = data.get("resource");
		String idField = table.raw().get(3).get(0);
		String id =  data.get(idField);
		String contentType = data.get("content type");
		
		// HTTP Status Code: 200 OK
		given()
		.contentType(contentType)
				.when()
				.delete(url + resource + id) //http://jsonplaceholder.typicode.com/posts/1
				.then()
				.statusCode(200);
	}
}
