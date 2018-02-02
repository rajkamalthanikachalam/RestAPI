package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {
	
	//Get Method without headers
	public CloseableHttpResponse get(String URL) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClinet = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(URL);
		CloseableHttpResponse httpResponse = httpClinet.execute(httpget);
		return httpResponse;
	}
	
	//Get Method with headers
	public CloseableHttpResponse get(String URL, HashMap<String, String> headermap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClinet = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(URL);
		for(Map.Entry<String, String> entry :headermap.entrySet()){
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse httpResponse = httpClinet.execute(httpget);	
		return httpResponse;
	}
	
	//POST Method with headers
	public CloseableHttpResponse post(String URL,String EntityString,  HashMap<String, String> headermap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClinet = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(URL);
		httppost.setEntity(new StringEntity(EntityString));//For Payload
		
		for(Map.Entry<String, String> entry :headermap.entrySet()){
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse httpResponse = httpClinet.execute(httppost);
		return httpResponse;
	}
		
	
}
