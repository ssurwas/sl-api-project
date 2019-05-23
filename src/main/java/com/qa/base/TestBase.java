package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {

public int RESPONSE_STATUS_CODE_200=200;
public int RESPONSE_STATUS_CODE_500=500;
public int RESPONSE_STATUS_CODE_400=400;
public int RESPONSE_STATUS_CODE_401=401;
public int RESPONSE_STATUS_CODE_503=503;
	public int RESPONSE_STATUS_CODE_201=201;
	public int RESPONSE_STATUS_CODE_202=201;

	public Properties prop;
	public TestBase()
	{
		prop=new Properties();
		FileInputStream fio;
		try {
			fio = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fio);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("hello");
		System.out.println("hello");
	}
}
