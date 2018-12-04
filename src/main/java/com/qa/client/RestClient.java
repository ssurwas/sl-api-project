package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {



	//1. GET METHOD
	public CloseableHttpResponse get(String url) throws Exception
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);  //http get Request
		CloseableHttpResponse httpresponse=httpclient.execute(httpget);  //execute the get request
		return httpresponse;
	}

	//2. GET METHOD WITH HEADERS
	public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws Exception
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);  //http get Request

		for(Map.Entry<String, String> entry : headerMap.entrySet())
		{ 
			httpget.addHeader(entry.getKey(), entry.getValue());   // Add header information to the GET CALL

		}

		CloseableHttpResponse httpresponse=httpclient.execute(httpget);  //execute the get request
		return httpresponse;
	}


	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerMap) throws Exception
	{
		CloseableHttpClient httpClient=HttpClients.createDefault();

		HttpPost httppost=new HttpPost(url);  // http post request

		httppost.setEntity(new StringEntity(entityString));  // for payload

		for(Map.Entry<String, String> entry : headerMap.entrySet())
		{ 
			httppost.addHeader(entry.getKey(), entry.getValue());   // Add header information to the POSTS CALL

		}

		CloseableHttpResponse httpresponse=httpClient.execute(httppost);  //execute the POST request
		return httpresponse;

	}
}
