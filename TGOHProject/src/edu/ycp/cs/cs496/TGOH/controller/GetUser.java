package edu.ycp.cs.cs496.TGOH.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import edu.ycp.cs.cs496.TGOH.UserClient.User;
import edu.ycp.cs.cs496.TGOH.json.JSON;


public class GetUser {

	public User getUser(String UserName) throws ClientProtocolException, URISyntaxException, IOException {
		return makeGetRequest(UserName);
	}
	
	private User makeGetRequest(String Username) throws URISyntaxException, ClientProtocolException, IOException{
		// Create HTTP client
 		HttpClient client = new DefaultHttpClient();
		
 		String msg = "/user/"+ Username;
		// Construct URI
		URI uri;
		uri = URIUtils.createURI("http", "10.0.2.2", 8081, msg, null, null);

		// Construct request
		HttpGet request = new HttpGet(uri);
		
		// Execute request
		HttpResponse response = client.execute(request);
		System.out.println("Here: " + msg + response.getStatusLine().getStatusCode()); 
		// Parse response
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// Add JSON object to request
			HttpEntity entity = response.getEntity();
			System.out.println("Before return " ); 
			// Parse JSON
			return JSON.getObjectMapper().readValue(entity.getContent(), User.class);
		} 
		
		// Return null if invalid response
		return null;
	}
}
