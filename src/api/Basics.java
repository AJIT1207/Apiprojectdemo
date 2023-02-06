package api;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import files.Reusable;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Scenario -> Add place then update the place id
		// given - input
		// when - submit queries
		// then - response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123")
				.headers("Content-Type", "application/json").body(Payload.addPlace()).when().log().all()
				.post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing data
		String placecid = js.getString("place_id");
		System.out.println(placecid);

		// update the place id
		String newaddress = "side layout, cohen 09";

		given().log().all().queryParams("key", "qaclick123").headers("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placecid + "\",\r\n" + "\"address\":\"" + newaddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get place id
		// String newaddress = "29, side layout, cohen 09";

		
		  String getplaceresponse = given().log().all().queryParams( "key",
		  "qaclick123") .queryParam("place_id", placecid)
		  .when().get("maps/api/place/get/json")
		  .then().assertThat().log().all().statusCode(200).extract().response().
		  asString();
		  
		  //JsonPath js1 = new JsonPath(getplaceresponse); 
		  JsonPath js1 =   Reusable.rawToJson(getplaceresponse);
		  String actualaddress = js1.getString("address"); 
		  System.out.println(actualaddress);
		  Assert.assertEquals(actualaddress, newaddress);
		  
	}

}
