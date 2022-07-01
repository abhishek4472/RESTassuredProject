package IbmFullstack.RestAssured;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HttpMethods {
	@Test(enabled=false)
	public void getMethod(){
		Response obj=RestAssured.get("http://localhost:3000/ibmstudents");
		System.out.println(obj.asString());
		//status code 
		System.out.println(obj.statusCode());
		
		//header code
		System.out.println(obj.headers());
		
	}
	@Test(enabled=false)
	public void deleteMethod(){
		Response obj=RestAssured.delete("http://localhost:3000/ibmstudents/6");
		System.out.println(obj.asString());
	}
	
	@Test(enabled=false)
	public void methodsTogether(){
		RestAssured.baseURI="http://localhost:3000/";
		
		given().get("ibmstudents").
		then()
		.statusCode(200).log().all();
		
		given().delete("ibmstudents/3").
		then()
		.statusCode(200).log().all();
		
	}
	
	
	@Test(enabled=false)
	public void post(){
		RestAssured.baseURI="http://localhost:3000/";
		
		String body="{\"name\":\"abhishek\",\"batchno\":\"16thmay\"}";
		
		given()
		   .contentType(ContentType.JSON)
		   //this is sending the header
		   
		   .body(body).
		   //sending the body
		   
		when()
		   .post("ibmstudents"). 
		   
		then()
		   .statusCode(201)
		   //assertion 
		   
		   .log().all();
		//show me all the response in the console
		
		
	}
	
	@Test(enabled=false)
	public void post2(){
		
		RestAssured.baseURI="http://localhost:3000/";
		
		JSONObject obj=new JSONObject();
		//another way for creating a json object
		
		obj.put("name", "lallu");
		obj.put("batchno", "6");
		//this put is json object class function
		
		System.out.println(obj);
		
		given()
		   .contentType(ContentType.JSON)
		   //this is sending the header
		   
		   .body(obj.toJSONString()).
		   //sending the body using json obj
		   
		when()
		   .post("ibmstudents"). 
		   
		then()
		   .statusCode(201)
		   //assertion 
		   
		   .log().all();
		//show me all the response in the console
		
		
	}
	
	@Test(enabled=false)
	public void patch(){
		RestAssured.baseURI="http://localhost:3000/";
		
		JSONObject obj=new JSONObject();
		
		obj.put("name","shallu");
		
		given().contentType(ContentType.JSON)
		   .body(obj.toJSONString())
		.when()
		   .patch("ibmstudents/7")
		   .then()
		   .statusCode(200).log().all();

		
	}
	
	@Test(enabled=false)
	public void put(){
		RestAssured.baseURI="http://localhost:3000/";
		
		JSONObject obj=new JSONObject();
		
		obj.put("name", "hallu");
		obj.put("batchno", "8");
		
		given().contentType(ContentType.JSON).
		  body(obj.toJSONString()).
		when()
		  .put("ibmstudents/7").
		then()
		   .statusCode(200).log().all();
		
		
		
	}
	
	@Test(enabled=true)
	public void queryParameter(){
		RestAssured.baseURI="https://petstore.swagger.io/v2";
				
		given().queryParam("username", "abhishek")
		.queryParam("password", "22345677").
		when()
		  .get("/user/login").
		then()
		   .statusCode(200)
		   .log().all();
		
	}
}
