package com.qa.api;
import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.testbase.TestBase;
import com.qa.users.Users;

public class PostAPI extends TestBase {

	String URL ;
	String Request_URL;
	String Service_Parameter;
	TestBase testbase;
	RestClient obj;
	CloseableHttpResponse httpResponse;
	
@BeforeMethod
public void Setup(){
	
	testbase = new TestBase();
	URL = prop.getProperty("URL");
	Service_Parameter = prop.getProperty("Service_Parameter");
	Request_URL = URL+Service_Parameter;
}

@Test
public void postAPI_WithHeader() throws ClientProtocolException, IOException{
	
	obj = new RestClient();
	
	HashMap<String, String> headermap = new HashMap<String, String>();
	headermap.put("Content-Type", "application/json");  // Key & Value of Headers content
	
	ObjectMapper mapper = new ObjectMapper();
	Users user = new Users("morpheus","leader");
	
	//object to json file - Marshalling
	mapper.writeValue(new File("../RestAPIAutomation/src/main/java/com/qa/data/User.json"), user);
	
	//JavaObject to string 
	String jsonString = mapper.writeValueAsString(user);
	System.out.println("User Name : "+jsonString);
	
	httpResponse = obj.post(URL, jsonString, headermap);
	
	//Status code
	int StatusCode = httpResponse.getStatusLine().getStatusCode();		
	System.out.println("Status Code : " +StatusCode );
	assertEquals(201, Creation_ResponseCode, "Response is Success");
	
	//JSON String
	String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
	JSONObject json = new JSONObject(responseString);
	System.out.println("Response String : " + json);
	
	//JSON to  Object - UnMarshalling 
	
	Users userRepObj = mapper.readValue(responseString, Users.class); //Actual User Object
	System.out.println(userRepObj);
	
	Assert.assertTrue(user.getJob().equals(userRepObj.getJob()));
	Assert.assertTrue(user.getName().equals(userRepObj.getName()));
	
	System.out.println(userRepObj.getId());
	System.out.println(userRepObj.getCreatedAt());
}
	
}
