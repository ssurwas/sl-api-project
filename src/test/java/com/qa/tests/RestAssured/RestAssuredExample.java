package com.qa.tests.RestAssured;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;


public class RestAssuredExample extends TestBase {

	TestBase testBase;
	String serviceurl;
	String apiUrl;
	String url;
	CloseableHttpResponse httpresponse;

	@BeforeMethod
	public void setUp()
	{
		testBase = new TestBase();
		serviceurl=prop.getProperty("URL");
		apiUrl=prop.getProperty("serviceURL");
		url=serviceurl+apiUrl;
	}

	//@Test
	public void GETAPI()
	{
		Response response=
				given().
				contentType("application/xml").
				when().
				get(url);
		System.out.println("status code is :-" + response.getStatusCode());
		System.out.println("response code is :-" + response.getBody().asString());
		int id=response.getBody().path("data[0].id");
		System.out.println(id);
		JsonPath id1=response.getBody().jsonPath();		
		System.out.println(id1.getString("data[0].avatar"));

		XmlPath xmlpath=response.getBody().xmlPath();

		System.out.println(xmlpath.getString("data[0]/avatar"));
		Headers header=response.getHeaders();
		System.out.println("Headers are:- "+header);
		System.out.println("value of content Type is:- "+header.getValue("content-type"));
	}


	//@Test
	public void POSTAPI()
	{
		Response response=
				given().
				contentType("application/json").
				when().
				body(new File("./src/main/java/com/qa/data/users.json")).
				post(url);
		System.out.println("status code is :-" + response.getStatusCode());
		System.out.println("response code is :-" + response.getBody().asString());
		String id=response.getBody().path("id");
		System.out.println(id);
		JsonPath jsonbody=response.getBody().jsonPath();
		System.out.println(jsonbody.getString("createdAt"));
		Headers header=response.getHeaders();
		System.out.println("Headers are:- "+header);
		System.out.println("value of content Type is:- "+header.getValue("content-type"));
	}


	//@Test
	public void PUTAPI()
	{
		Response response=
				given().
				contentType("application/json").
				when().
				body(new File("./src/main/java/com/qa/data/put.json")).
				put(url);
		System.out.println("status code is :-" + response.getStatusCode());
		System.out.println("response code is :-" + response.getBody().asString());

		JsonPath jsonbody=response.getBody().jsonPath();
		System.out.println(jsonbody.getString("updatedAt"));

		Headers header=response.getHeaders();
		System.out.println("Headers are:- "+header);
		System.out.println("value of content Type is:- "+header.getValue("content-type"));
	}

	@Test
	public void DELETEAPI()
	{
		Response response=
				given().
				contentType("application/json").
				when().
				//body(new File("./src/main/java/com/qa/data/put.json")).
				delete(url);
		System.out.println("status code is :-" + response.getStatusCode());
		System.out.println("response code is :-" + response.getBody().asString());

		Headers header=response.getHeaders();
		System.out.println("Headers are:- "+header);
		System.out.println("value of content Type is:- "+header.getValue("Date"));
	}


}
