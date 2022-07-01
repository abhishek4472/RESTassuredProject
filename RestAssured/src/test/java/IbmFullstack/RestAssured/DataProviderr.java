package IbmFullstack.RestAssured;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DataProviderr {
	
	@DataProvider(name="testcase")
	public Object[][] data()
	{
	   Object[][] studentData = new Object[3][2];
	   studentData[0][0]="saif";
	   studentData[0][1]="23";
	   studentData[1][0]="hari";
	   studentData[1][1]="54";
	   studentData[2][0]="Sanik";
	   studentData[2][1]="32";
	   return studentData;
	}
	
	@Test(enabled=true,dataProvider="testcase")
	public void push(String fname,String batch){
		RestAssured.baseURI="http://localhost:3000/";
		
		JSONObject obj=new JSONObject();
		
		obj.put("name", fname);
		obj.put("batchno", batch);
		
		given().contentType(ContentType.JSON)
		      .body(obj.toJSONString()).
		 when()
		     .post("ibmstudents").
		 then().statusCode(201).log().all();
		
	}
	

}
