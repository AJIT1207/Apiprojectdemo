package files;

import io.restassured.path.json.JsonPath;

public class Reusable {
  
    	 public static JsonPath rawToJson(String respnse)
    	 {
    		 JsonPath js1 = new JsonPath(respnse);
    		 return js1;
    	 }
     }
	

