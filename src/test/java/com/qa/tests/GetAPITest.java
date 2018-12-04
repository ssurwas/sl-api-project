package com.qa.tests;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {


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
	public void getTest() throws Exception
	{
		RestClient rest=new RestClient();
		httpresponse=rest.get(url);

		int Statuscode = httpresponse.getStatusLine().getStatusCode(); // get the status code
		System.out.println("Status Code--------"+ Statuscode);

		Assert.assertEquals(Statuscode,RESPONSE_STATUS_CODE_200,"Response code is not 200");

		String responseString=EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		System.out.println("Response Code in String--------"+ responseString);

		JSONObject responseJson=new JSONObject(responseString);
		System.out.println("JSON Response from API--------"+ responseJson); // get the JSON response

		//verify Per Page Value
		String perPageValue=TestUtil.getValuebyJPath(responseJson, "/per_page");
		System.out.println("value of per page is:- "+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);

		//verify Total Value
		String TotalValue=TestUtil.getValuebyJPath(responseJson, "/total");
		System.out.println("value of per page is:- "+ TotalValue);
		Assert.assertEquals(Integer.parseInt(TotalValue), 12);

		String lastname=TestUtil.getValuebyJPath(responseJson, "/data[0]/last_name");
		System.out.println("value of last name is:- "+ lastname);

		Assert.assertEquals(lastname, "Bluth");

		Header[] headerArray=httpresponse.getAllHeaders();
		HashMap<String,String> allheaders=new HashMap<String,String>();

		for(Header header:headerArray)
		{
			allheaders.put(header.getName(), header.getValue());   // get the headers as key/value pair
		}

		System.out.println("Headers Array-------"+allheaders);

	}


	@Test
	public void getTestwithHeaders() throws Exception
	{
		RestClient rest=new RestClient();

		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("username", "test");
		headerMap.put("password", "test123");

		httpresponse=rest.get(url,headerMap);

		int Statuscode = httpresponse.getStatusLine().getStatusCode(); // get the status code
		System.out.println("Status Code--------"+ Statuscode);

		Assert.assertEquals(Statuscode,RESPONSE_STATUS_CODE_200,"Response code is not 200");

		String responseString=EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		System.out.println("Response Code in String--------"+ responseString);

		JSONObject responseJson=new JSONObject(responseString);
		System.out.println("JSON Response from API--------"+ responseJson); // get the JSON response

		//verify Per Page Value
		String perPageValue=TestUtil.getValuebyJPath(responseJson, "/per_page");
		System.out.println("value of per page is:- "+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);

		//verify Total Value
		String TotalValue=TestUtil.getValuebyJPath(responseJson, "/total");
		System.out.println("value of per page is:- "+ TotalValue);
		Assert.assertEquals(Integer.parseInt(TotalValue), 12);

		String lastname=TestUtil.getValuebyJPath(responseJson, "/data[0]/last_name");
		System.out.println("value of last name is:- "+ lastname);

		Assert.assertEquals(lastname, "Bluth");

		Header[] headerArray=httpresponse.getAllHeaders();
		HashMap<String,String> allheaders=new HashMap<String,String>();

		for(Header header:headerArray)
		{
			allheaders.put(header.getName(), header.getValue());   // get the headers as key/value pair
		}

		System.out.println("Headers Array-------"+allheaders);

	}
	
	
	
}
