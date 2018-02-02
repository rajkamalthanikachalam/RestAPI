package com.qa.api;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.testbase.TestBase;
import com.qa.util.TestUtil;

public class GetAPI extends TestBase {

	String URL ;
	String Request_URL;
	String Service_Parameter;
	String Query_Parameter;
	TestBase testbase;
	RestClient obj;
	CloseableHttpResponse httpResponse;
	
@BeforeMethod
public void Setup(){
	
	testbase = new TestBase();
	URL = prop.getProperty("URL");
	Service_Parameter = prop.getProperty("Service_Parameter");
	Query_Parameter = prop.getProperty("Query_Parameter", null);
	Request_URL = URL+Service_Parameter+Query_Parameter;
}

@Test
public void getAPI_WithoutHeader() throws ClientProtocolException, IOException{
	obj = new RestClient();
	httpResponse =  obj.get(Request_URL);
	
	//Status code
	int StatusCode = httpResponse.getStatusLine().getStatusCode();		
	System.out.println("Status Code : " +StatusCode );
	assertEquals(200, Success_ResponseCode, "Response is Success");
	
	//JSON String
	String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
	JSONObject json = new JSONObject(responseString);
	System.out.println("Response String : " + json);
	
	//Single Value Assertion
	String TotalValue = TestUtil.getValueByJPath(json, "/total");
	assertEquals(12, Integer.parseInt(TotalValue), "Total Value is matched");
	
	String TotalPage = TestUtil.getValueByJPath(json, "/total_pages");
	assertEquals(4, Integer.parseInt(TotalPage), "Total Page is matched");
	
	//To get value stored in array 
	// value before square attribute will be used followed by Key name
	String ID = TestUtil.getValueByJPath(json, "/data[0]/id");
	assertEquals(4, Integer.parseInt(ID), "ID Value is");
	
	String FirstName = TestUtil.getValueByJPath(json, "/data[0]/first_name");
	assertEquals("Eve", FirstName, "First Name ");
	
	String LastName = TestUtil.getValueByJPath(json, "/data[0]/last_name");
	assertEquals("Holt", LastName, "Last Name");
	
	String Avatar = TestUtil.getValueByJPath(json, "/data[0]/avatar");
	assertEquals("https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg", Avatar, "Avatar");
	
	//Headers
	Header[] headerArray = httpResponse.getAllHeaders();
	HashMap< String, String> allheaders =  new HashMap<String, String>();
	for(Header header :headerArray ){
		allheaders.put(header.getName(), header.getValue());
	}
		System.out.println("Headers array : " + allheaders);
}

@Test
public void getAPI_WithHeader() throws ClientProtocolException, IOException{
	obj = new RestClient();
	
	HashMap<String, String> headermap = new HashMap<String, String>();
	headermap.put("Content-Type", "application/json");  // Key & Value of Headers content
	
	httpResponse =  obj.get(Request_URL);
	
	//Status code
	int StatusCode = httpResponse.getStatusLine().getStatusCode();		
	System.out.println("Status Code : " +StatusCode );
	assertEquals(200, Success_ResponseCode, "Response is Success");
	
	//JSON String
	String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
	JSONObject json = new JSONObject(responseString);
	System.out.println("Response String : " + json);
	
	//Single Value Assertion
	String TotalValue = TestUtil.getValueByJPath(json, "/total");
	assertEquals(12, Integer.parseInt(TotalValue), "Total Value is matched");
	
	String TotalPage = TestUtil.getValueByJPath(json, "/total_pages");
	assertEquals(4, Integer.parseInt(TotalPage), "Total Page is matched");
	
	//To get value stored in array 
	// value before square attribute will be used followed by Key name
	String ID = TestUtil.getValueByJPath(json, "/data[0]/id");
	assertEquals(4, Integer.parseInt(ID), "ID Value is");
	
	String FirstName = TestUtil.getValueByJPath(json, "/data[0]/first_name");
	assertEquals("Eve", FirstName, "First Name ");
	
	String LastName = TestUtil.getValueByJPath(json, "/data[0]/last_name");
	assertEquals("Holt", LastName, "Last Name");
	
	String Avatar = TestUtil.getValueByJPath(json, "/data[0]/avatar");
	assertEquals("https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg", Avatar, "Avatar");
	
	
	
	//Headers
	Header[] headerArray = httpResponse.getAllHeaders();
	HashMap< String, String> allheaders =  new HashMap<String, String>();
	for(Header header :headerArray ){
		allheaders.put(header.getName(), header.getValue());
	}
		System.out.println("Headers array : " + allheaders);
}
	
}
