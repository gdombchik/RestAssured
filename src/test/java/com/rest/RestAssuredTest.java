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
	
	private static String REST_POST_BY_OBJECT_MAPPING = "object_mapping";
	private static String REST_POST_BY_VALUE = "value";
		
	@Given("^Test the Get Rest method\\.$")
	public void testTheGetmethod(DataTable table) throws Throwable {
		List<List<String>> tableList = table.raw();
		String url = tableList.get(1).get(1);
		String resource = tableList.get(2).get(1);
		String id = tableList.get(3).get(1);
		String idField = tableList.get(3).get(0);
		//HTTP Status Code:  200 OK
		get(url + resource + id).then().statusCode(200).body(idField, equalTo(new Long(id).intValue()));
	}
	
	@Then("^Test the Put Rest method by Object Mapping\\.$")
	public void testThePutRestMethodByObjectMapping(DataTable table) throws Throwable {
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
		
		PostsDto postsDto = new PostsDto(userId,id,title,body);
		
		/*given().contentType("application/json").body(postsDto)
	    .when().post(url + resource).then().statusCode(201);*/
		
		/*given().contentType("application/json").body(postsDto)
	    .when().post(url + resource).body().print();*/
		
		//HTTP Status Code:  201 Created
		given().contentType("application/json").body(postsDto)
	    .when().post(url + resource).then().statusCode(201).
	    body(userIdField,equalTo(new Long(userId).intValue()),
	    		idField, equalTo(new Long(id).intValue()),
	    		titleField, equalTo(title),
	    		bodyField,equalTo(body)).and().body(matchesJsonSchemaInClasspath("posts-schema.json"));
	}
	
	@Then("^Test the Put Rest method by Response Body as String\\.$")
	public void testThePutRestMethodByResponseBodyAsString(DataTable table) throws Throwable {
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
		
		StringBuilder jsonBody = new StringBuilder();
		jsonBody.append("{").
		append("\""+userIdField+"\":"+userId+",").
		append("\""+idField+"\":"+id+",").
		append("\""+titleField+"\":\""+title+"\",").
		append("\""+bodyField+"\":\""+body+"\"").
		append("}");
		
		//HTTP Status Code:  201 Created
		given().contentType("application/json").body(jsonBody.toString())
	    .when().post(url + resource).then().statusCode(201).
	    body(userIdField,equalTo(new Long(userId).intValue()),
	    		idField, equalTo(new Long(id).intValue()),
	    		titleField, equalTo(title),
	    		bodyField,equalTo(body)).and().body(matchesJsonSchemaInClasspath("posts-schema.json"));
	}


	@Given("^Post Rest Request by ([^\"]*)$")
	public void post_Rest_Request_by_Object_Mapping(String value, DataTable arg1) throws Throwable {
		 if(value.equals(this.REST_POST_BY_OBJECT_MAPPING)){
			 System.out.println(value);
		 }else if (value.equals(this.REST_POST_BY_OBJECT_MAPPING)){
			 System.out.println(value);
		 }
	}

	
}
