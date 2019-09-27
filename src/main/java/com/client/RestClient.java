package com.client;

import io.restassured.RestAssured;

public class RestClient {
	
	public void setBaseURL(String baseURL) {
		RestAssured.baseURI=baseURL;
	}

}
