package IbmFullstack.RestAssured;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PetProject {
	
	//DataProvider 
	@DataProvider(name="provider")
	public Object[][] data(){
		Object[][] petData=new Object[2][8];
		petData[0][0]="1";
		petData[0][1]="abhi44";
		petData[0][2]="abhishek";
		petData[0][3]="bahukhandi";
		petData[0][4]="a@a.com";
		petData[0][5]="12345678";
		petData[0][6]="9910512345";
		petData[0][7]="1";
		petData[1][0]="2";
		petData[1][1]="anshu44";
		petData[1][2]="anshuman";
		petData[1][3]="dogra";
		petData[1][4]="b@b.com";
		petData[1][5]="123456789";
		petData[1][6]="9910567891";
		petData[1][7]="2";
	
		return petData;
	}
	
	//Create the user using the data provider
	@Test(enabled=true,priority=1,dataProvider="provider")
	public void createUser(String id,String un,String fn,String ln,String email,String pass,String ph,String us){
		
		RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
		JSONObject obj =new JSONObject();
		
		obj.put("id",id);
		obj.put("username",un);
		obj.put("firstName",fn);
		obj.put("lastName",ln);
		obj.put("email",email);
		obj.put("password",pass);
		obj.put("phone",ph);
		obj.put("userstatus",us);
		
		
		given().contentType(ContentType.JSON)
		       .body(obj.toJSONString()).
		 when().post("/user")
		.then().statusCode(200).log().all();
		
	}
	
	//Get user data 
	@Test(enabled=true,priority=2)
	public void getUser(){
		
		RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
		given().get("/user/abhi44").
		then()
		.statusCode(200).log().all();
		
		
		given().get("/user/anshu44").
		then()
		.statusCode(200).log().all();
	}
	
	//Login into the account using the dataProvider
	@Test(enabled=true,priority=3,dataProvider="provider")
	public void LoginUser(String id,String un,String fn,String ln,String email,String pass,String ph,String us){
       RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
	
	given().queryParam("username", un)
	.queryParam("password", pass).
	when()
	  .get("/user/login").
	then()
	   .statusCode(200)
	   .log().all();
		
		
	}
	
	//Update the user using the put method
	@Test(enabled=true,priority=4)
	public void UpdateUser(){
		 RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		 
		 
		 JSONObject obj=new JSONObject();
			
		 obj.put("id","1");
			obj.put("username","abhi44");
			obj.put("firstName","Shanti");
			obj.put("lastName","Shanti");
			obj.put("email","a@a.com");
			obj.put("password","12345678");
			obj.put("phone","9910512345");
			obj.put("userstatus","1");
			
			given().contentType(ContentType.JSON)
			   .body(obj.toJSONString())
			.when()
			   .put("/user/abhi44")
			   .then()
			   .statusCode(200).log().all();
	}
	
	//delete the user
	@Test(enabled=true,priority=5)
	public void deleteUser(){
		 RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		 
		 given().delete("/user/abhi44").then().statusCode(200).log().all();
		 given().delete("/user/anshu44").then().statusCode(200).log().all();
	}

}
