package DAC.API_Automation;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.AssertionFailedError;

public class api_dtc_Report {
String url="https://lpad-api-report.azurewebsites.net/api/v1/External/LocationNumberValidator";
String accessToken="2fc36419-9979-45cc-ad4e-70dd266530e3";
String reportType="LDD";

@Test
	public void verifyLocations() {
		RestAssured.baseURI=api_Resources.reportsAPI;
		RequestSpecification httprequest=RestAssured.given().contentType(api_Resources.contentType).header("api-key",api_Resources.api_key);
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("accessToken", accessToken);
		requestParams.put("reportType", reportType);
	    requestParams.put("locationNumbers",api_Resources.locations);
	    
	    System.out.println(requestParams);
	    httprequest.body(requestParams.toJSONString());
	    Response response = httprequest.post();
	    int statusCode = response.getStatusCode();
	    
	    System.out.println(response.jsonPath().get().toString());
	    boolean status = response.jsonPath().get("Success");
	    int error=response.jsonPath().getInt("ErrorType");
	    System.out.println("Status is "+status);
	    System.out.println("Error Type is "+error);
	    
	    switch(error) {
	    case 0:
	      System.out.println(reportType +" Report Verification Success");
	      	break;
	    case 1:
	      System.out.println("Not access right to all locations");
	      	break;
	    case 2:
	    	System.out.println("INVALID Location Numbers");
	    	break;
	    case 3:
	    	System.out.println("Location Numbers from MULTIPLE Accounts");
		    break;
	    default:
	    	Assert.assertEquals( statusCode,200);
	  }
//	    
	    
	  
	}
}
