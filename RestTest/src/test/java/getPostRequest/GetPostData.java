package getPostRequest;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;

import java.nio.charset.Charset;
import java.util.Random;

public class GetPostData {
	
	
	@Test
	public void testResponseCode()
	{
		System.out.println("Get Methods -->");
		//1. parametresiz çekiş
		Response response = given().when().get("https://samples.openweathermap.org/");
		System.out.println("Response 1 : " + response.asString());
		 System.out.println("Status code : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//2. parametreli çekiş
		response = given().when().get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		System.out.println("Response 2 : " + response.asString());
		 System.out.println("Status code : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);

		//3. parametresiz çekiş
		response = given().when().get("http://ergast.com/api/f1/seasons");
		System.out.println("Response 3 : " + response.asString());
		 System.out.println("Status code : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//4. parametresiz çekiş
		response = given().when().get("http://restcountries.eu/rest/v1").then().contentType(ContentType.JSON).extract().response();
		String cty = response.jsonPath().getString("name[0]");
		System.out.println("Response 4 : " + response.asString());
		System.out.println("Status code : " + response.getStatusCode());
		System.out.println("First city " + cty);
		Assert.assertEquals(response.getStatusCode(), 200);

		//5. üstteki servisten gelen sonucu parametre olarak kullanmak
		response = given().when().get("http://restcountries.eu/rest/v1/name/{country}", cty);
		System.out.println("Response 5 : " + response.asString());
		System.out.println("Status code : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//6. post method
		System.out.println("Post Method --->");
		//post servisi icin kullanılan methodda isim unique olmalı o yüzden random test ile birleştirdim
		byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
		String requestBody = "{\"name\":\"murat sayrim " + generatedString + "\",\"salary\":\"35000\",\"age\":\"27\"}";
		 
		 try {
			 response = given().contentType(ContentType.JSON).body(requestBody).post("http://dummy.restapiexample.com/api/v1/create");
			 System.out.println("Response : " + response.asString());
			 System.out.println("Status code : " + response.getStatusCode());
			 Assert.assertEquals(response.getStatusCode(), 200);
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
	
	}
	
	
	
}
