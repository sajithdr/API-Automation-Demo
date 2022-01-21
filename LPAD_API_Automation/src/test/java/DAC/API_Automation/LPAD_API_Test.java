package DAC.API_Automation;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.*;


public class LPAD_API_Test {
	String _APIpresetvalues="https://lpad-api-account.azurewebsites.net/api/v2/accounts/";
	createOutput out=new createOutput();
	String longname="";
	String BusinessName="";
	String ChainName="KFC";
	String countryCode="";
	String regionCode="";
	String accountid="6d504f5b-1179-4da3-908d-6d257ed0e426";
	String[] logs;
	
@Test(groups = { "smoke" }, description = "TC: Get Preset Values")
  public void GetPresetValues() {
		String filename=out.getmethod();
		ArrayList<String> ar = new ArrayList<String>();
		RestAssured.baseURI=_APIpresetvalues;
		
		RequestSpecification httprequest=RestAssured.given()
			  .contentType(api_Resources.contentType)
			  .header("api-key",api_Resources.api_key);
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("BusinessName.LongName", longname);
		requestParams.put("BusinessName.Name", BusinessName);
	    requestParams.put("KeyFields.ChainName",ChainName);
	    requestParams.put("MainAddress.RegionCode",regionCode);
	    requestParams.put("MainAddress.CountryCode",countryCode);
	    
	    httprequest.body(requestParams.toJSONString()); 
	    Response response = httprequest.post(accountid+"/presetvalues");
	    String statusCode =String.valueOf(response.getStatusCode());
	    String body=response.getBody().asString();
	   
	    System.out.println("body "+body+statusCode);
	    System.out.println(filename);
	    
	    ar.add("Input>> ");
	    ar.add(requestParams.toJSONString());
	    ar.add("OutPut>> ");
	    ar.add(body);
	    ar.add("Status: "+statusCode);
	    logs = new String[ar.size()];
	    ar.toArray(logs);
	    out.writeToLog(filename, logs);
	  }


}
