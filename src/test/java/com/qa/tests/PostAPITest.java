package com.qa.tests;

import java.io.File;
import java.util.HashMap;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {


	TestBase testBase;
	String serviceurl;
	String apiUrl;
	String url;
	CloseableHttpResponse httpresponse;
	RestClient restclient;

	@BeforeMethod
	public void setUp()
	{
		testBase = new TestBase();
		serviceurl=prop.getProperty("URL");
		apiUrl=prop.getProperty("serviceURL");
		url=serviceurl+apiUrl;
	}


	@Test
	public void postAPITest() throws Exception
	{
		restclient=new RestClient();

		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");

		//JACKSON API

		ObjectMapper mapper = new ObjectMapper();
		Users users=new Users("swapnil","Leader");

		//Object to JSON FILE
		mapper.writeValue(new File("C:\\testapiproject\\src\\main\\java\\com\\qa\\data\\users.json"), users);


		//Object to JSON  in String
		String usersJsonString=mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		System.out.println(url);
		System.out.println(headerMap);
		httpresponse=restclient.post(url, usersJsonString, headerMap);   // add URL, JSON REQUEST and HEADER for POST Method.

		//1. verify Status Code
		int StatusCode=httpresponse.getStatusLine().getStatusCode();
		Assert.assertEquals(StatusCode, RESPONSE_STATUS_CODE_201);

		//2. Verify Response in String
		String responseString=EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		System.out.println(responseString);

		//3. Verify Response in JSON
		JSONObject jsonresponse=new JSONObject(responseString);
		System.out.println(jsonresponse);

		//JSON to JAVA Object
		Users usersResObj=mapper.readValue(responseString,Users.class);
		System.out.println(usersResObj);

		Assert.assertEquals(usersResObj.getName(),users.getName());
		Assert.assertEquals(usersResObj.getJob(),users.getJob());
		//	Assert.assertEquals(usersResObj.getId(),users.getId());
		//	Assert.assertEquals(usersResObj.getCreatedAt(),users.getCreatedAt());

	}

}
