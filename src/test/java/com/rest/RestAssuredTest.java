package com.rest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import java.util.List;
import java.util.Map;

import com.test.rest.dto.PostsDto;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class RestAssuredTest {

	private static String REST_POST_BY_OBJECT_MAPPING = "Object Mapping";
	private static String REST_POST_BY_VALUE = "JSON String";

	@Given("^Test the Get REST method\\.$")
	public void testTheGetRESTMethod(DataTable table) throws Throwable {
		Map<String,String> data = table.asMap(String.class,String.class);
		String url = data.get("url");
		String resource = data.get("resource");
		String idField = table.raw().get(3).get(0); //id (first column)
		String id = data.get(idField);
				
		// HTTP Status Code: 200 OK
		get(url + resource + id).then().statusCode(200)
				.body(idField, equalTo(new Long(id).intValue()));
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
