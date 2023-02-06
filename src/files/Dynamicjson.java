package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

//import static io.restassured.RestAssured;

public class Dynamicjson
{
    @Test(dataProvider="booksdata")
    public void addBook(String isbn,String aisle)
    {
    	RestAssured.baseURI = "http://216.10.245.166";
       String response =	given().header("Content-Type", "application/json").
    	body(Payload.addBook(isbn,aisle)).
    	when()
    	.post("Library/Addbook.php")
    	.then().log().all().assertThat().statusCode(200)
    	.extract().response().asString();
    	JsonPath js = Reusable.rawToJson(response);
    	String ID = js.get("ID");
    	System.out.println(ID);
    }
    
    //delete book form library
    @DataProvider(name = "booksdata")
    public Object[][] getData()
    {
    	return new Object[][]  {{"hsdjsh","2334"}, {"hsdjfh","2333"}, {"hsdj1h","2338"} };
    }
}
