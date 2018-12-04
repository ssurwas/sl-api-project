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
import java.util.HashMap;


public class RestAssuredExample2 extends TestBase {

	TestBase testBase;
	String serviceurl;
	String apiUrl;
	String url;
	CloseableHttpResponse httpresponse;
	HashMap<String,String> allheaders;
	@BeforeMethod
	public void setUp()
	{
		testBase = new TestBase();
		serviceurl=prop.getProperty("APIURL");
		url=serviceurl;
		allheaders=new HashMap<String,String>();
		allheaders.put("Authorization", "Basic ZTU3MTZiZTdkYzhmMWIyYzFkMDM3MTRiZWUxYWE0YTYxZTdkYTljOTJmMDk0ODEzNDQ4NDBiODY2NDM0M2I4NTplOTI1ZmE2ZTU3OTllZjU0MTI4NmYzMDAzNmQyN2U3MDFlZTI3ZmE5YjMyYmVlNzA0OA==");
		allheaders.put("Content-Type", "application/xml");

	}

	@Test
	public void GETAPI()
	{
		System.out.println(url);
		Response response=
				given().
				headers(allheaders).
				when().
				get(url);
		System.out.println("status code is :-" + response.getStatusCode());
		System.out.println("response code is :-" + response.getBody().asString());
		//int id=response.getBody().path("data[0].id");
		//System.out.println(id);
		//JsonPath id1=response.getBody().jsonPath();		
		//System.out.println(id1.getString("data[0].avatar"));

		XmlPath xmlpath=response.getBody().xmlPath();

		System.out.println(xmlpath.getString("orderstatus"));
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

	//@Test
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
