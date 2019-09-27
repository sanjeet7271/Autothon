
package com.keywords;

import com.propertyReader.PropertyReader;
import com.reportLogger.ReportFactory;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredRequest {
	
	/* Function Decription - Function will create a Rest API request and do operations based on RequestPayload object      
	 * Created by - Sachin Ahuja
	 * Created on - 15th March
	 * Modified by
	 * Modified on
	 * */
	   public final static Response sendAPIRequest(RestRequestPayload payload) {
			Response resp=null;
		   	payload.httpRequest.headers(payload.headers);
		   	payload.httpRequest.cookie(payload.cookieName);
		   	payload.httpRequest.params(payload.params);
		   	payload.httpRequest.queryParams(payload.qParams);
		   	
		   	if(payload.authorizationType=="oauth") {
//		   		payload.httpRequest.auth().
//				oauth(PropertyReader.propertyMap.get("ConsumerKey"), PropertyReader.propertyMap.get("ConsumerSecret"), PropertyReader.propertyMap.get("Token"), PropertyReader.propertyMap.get("TokenSecret"));
		   	}
		   	
		   	ReportFactory.getInstance().info("Rest request end point: "+RestAssured.baseURI+payload.resource);
			
		   	if(payload.requestType.equals("Get")) {
		   		resp = payload.httpRequest
		  	   		  .when().get(payload.resource, new Object[0]);
		   	}
		   	else if(payload.requestType.equals("Post")) {
		   		payload.httpRequest.body(payload.body);
		   		resp = payload.httpRequest
		  	   		  .when().post(payload.resource, new Object[0]);
		   	}
		   	else if(payload.requestType.equals("Post")) {
		   		payload.httpRequest.body(payload.body);
		   		resp = payload.httpRequest
		  	   		  .when().put(payload.resource, new Object[0]);
		   	}
		   	
		   return resp;
		   
		}
	   

}