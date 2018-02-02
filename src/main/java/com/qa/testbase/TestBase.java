package com.qa.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public Properties prop;
	public int Success_ResponseCode = 200;
	public int Creation_ResponseCode = 201;
	public int BadRequest_ResponseCode = 400;
	public int Unauthorized_ResponseCode = 401;
	public int NotFound_ResponseCode = 404;
	public int InternalError_ResponseCode = 500;
	
	
	public TestBase(){
		
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream("../RestAPIAutomation/src/main/java/com/qa/config/config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
