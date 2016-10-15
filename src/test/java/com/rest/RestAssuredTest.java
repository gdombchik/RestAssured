package com.rest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import io.restassured.response.ResponseBody;

import java.util.List;

import org.junit.Assert;

import com.test.rest.dto.PostsDto;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

//http://www.hascode.com/2011/10/testing-restful-web-services-made-easy-using-the-rest-assured-framework/
//http://testdetective.com/rest-assured-going-deeper/
//http://www.restapitutorial.com/httpstatuscodes.html
public class RestAssuredTest {

	private static String REST_POST_BY_OBJECT_MAPPING = "Object Mapping";
	private static String REST_POST_BY_VALUE = "JSON String";

	@Given("^Test the Get REST method\\.$")
	public void testTheGetRESTMethod(DataTable table) throws Throwable {
		List<List<String>> tableList = table.raw();
		String url = tableList.get(1).get(1);
		String resource = tableList.get(2).get(1);
		String id = tableList.get(3).get(1);
		String idField = tableList.get(3).get(0);
		// HTTP Status Code: 200 OK
		get(url + resource + id).then().statusCode(200)
				.body(idField, equalTo(new Long(id).intValue()));
	}

	@Given("^Test the Post REST method by ([^\"]*)$")
	public void testThePostRESTMethodBy(String value,
			DataTable table) throws Throwable {
		List<List<String>> tableList = table.raw();
		String url = tableList.get(1).get(1);
		String resource = tableList.get(2).get(1);
		String userIdField = tableList.get(3).get(0);
		String userId = tableList.get(3).get(1);
		String idField = tableList.get(4).get(0);
		String id = tableList.get(4).get(1);
		String titleField = tableList.get(5).get(0);
		String title = tableList.get(5).get(1);
		String bodyField = tableList.get(6).get(0);
		String body = tableList.get(6).get(1);
		String contentType = tableList.get(7).get(1);
		String jsonSchema = tableList.get(8).get(1);

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
		List<List<String>> tableList = table.raw();
		String url = tableList.get(1).get(1);
		String resource = tableList.get(2).get(1);
		String userIdField = tableList.get(3).get(0);
		String userId = tableList.get(3).get(1);
		String idField = tableList.get(4).get(0);
		String id = tableList.get(4).get(1);
		String titleField = tableList.get(5).get(0);
		String title = tableList.get(5).get(1);
		String bodyField = tableList.get(6).get(0);
		String body = tableList.get(6).get(1);
		String contentType = tableList.get(7).get(1);
		String jsonSchema = tableList.get(8).get(1);
		
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
		List<List<String>> tableList = table.raw();
		String url = tableList.get(1).get(1);
		String resource = tableList.get(2).get(1);
		String id = tableList.get(3).get(1);
		String contentType = tableList.get(4).get(1);
		
		// HTTP Status Code: 200 OK
		given()
		.contentType(contentType)
				.when()
				.delete(url + resource + id) //http://jsonplaceholder.typicode.com/posts/1
				.then()
				.statusCode(200);
	}
}
