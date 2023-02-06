package api;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class Complexjsonapi {
  public static void main(String[] args) {
	JsonPath js = new JsonPath(Payload.complexjson());
	int count =     js.getInt("courses.size()");
	System.out.println(count);
	//print purchase amount
     int totalamount = js.getInt("dashboard.purchaseAmount");
     System.out.println(totalamount);
     //print title of first course
     String titlefirstcourse  = js.get("courses[2].title");
     System.out.println(titlefirstcourse);
     //Print courses title and amount
     for(int i=0;i<count;i++)
     {
    	 String coursetitle = js.get("courses["+i+"].title");
    	 System.out.println(js.get("courses["+i+"].price").toString());
    	 System.out.println(coursetitle);
    	 
     }
   //Print no. of copies
     for(int i=0;i<count;i++)
     {
    	 String coursetitle = js.get("courses["+i+"].title");
    	if(coursetitle.equalsIgnoreCase("RPA"));
    	{
    	int copies = 	js.get("courses["+i+"].copies");
    	System.out.println(copies);
    	break;
    	}
    	 
     } 
     
}
  
  
	

}
