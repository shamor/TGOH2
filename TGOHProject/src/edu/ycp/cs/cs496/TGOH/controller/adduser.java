package edu.ycp.cs.cs496.TGOH.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.ycp.cs.cs496.TGOH.User.User;
import edu.ycp.cs.cs496.TGOH.model.json.JSON;

public class adduser {
	public boolean postItem(String FirstName, String LastName, String Username, String Password) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		return makePostRequest(FirstName, LastName, Username, Password);
	}
	
	public boolean makePostRequest(String FirstName, String LastName, String Username, String Password) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		// Create HTTP client
 		HttpClient client = new DefaultHttpClient();
		
		// Construct URI
		URI uri = URIUtils.createURI("http", "10.0.2.2", 8081, "/user", null, null);
		
		// Construct request
		HttpPost request = new HttpPost(uri);
		
		if(FirstName != null && LastName != null && Username != null && Password != null){
			User user = new User(FirstName, LastName, Username, Password);
			StringWriter sw = new StringWriter();
			JSON.getObjectMapper().writeValue(sw, user);
			
			// Add JSON object to request
			StringEntity reqEntity = new StringEntity(sw.toString());
			reqEntity.setContentType("application/json");
			request.setEntity(reqEntity);
				
			// Execute request
			HttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return true;
			}else{
				return false;
			}
		}	
		
		return false;
	}
}
