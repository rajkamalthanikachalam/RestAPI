package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class RestClient_Original {
	
	public void get(String URL) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClinet = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(URL);
		CloseableHttpResponse httpResponse = httpClinet.execute(httpget);
		
		int StatusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code : " +StatusCode );
		
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject json = new JSONObject(responseString);
		System.out.println("Response String : " + json);
		
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap< String, String> allheaders =  new HashMap<String, String>();
		for(Header header :headerArray ){
			allheaders.put(header.getName(), header.getValue());
		}
			System.out.println("Headers array : " + allheaders);
	}

	

	

}
