package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class Sumvalidation
{
   @Test
   public void sumcourses()
   {
	    int sum = 0;
		JsonPath js = new JsonPath(Payload.complexjson());
		int count =     js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{
			 int price = js.get("courses["+i+"].price");
			 int copies = 	js.get("courses["+i+"].copies");
			 int amount = price * copies;
			 System.out.println(amount);
			 sum = sum + amount;
		}
		System.out.println(sum);
		int totalamount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, totalamount);
   }
}
