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

import edu.ycp.cs.cs496.TGOH.json.JSON;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class AddCourse {
	public boolean postCourse(String Username, String Course) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		return makePostRequest(Username, Course);
	}
	
	public boolean makePostRequest(String Username, String Course) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
	HttpClient client = new DefaultHttpClient();
	
	// Construct URI
	URI uri = URIUtils.createURI("http", "10.0.2.2", 8081, "/user/" + Username + "/" + Course, null, null);
	
	// Construct request
	HttpPost request = new HttpPost(uri);

	if(Username != null && Course != null){
		User user = new User();
		//User.addCourse(Course);
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
