package com.Selenium.FileHandling;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiHandling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       try {
    	   URL url = new URL("https://jsonplaceholder.typicode.com/users/1");    //Using URL class create  an object(url) to parse an api url
    	   
    	   //created Object(url) , parse into HttpURLConnection class to create an object of conn 
    	   //(HttpURLConnection) -> for Type Casting 
    	   HttpURLConnection conn=(HttpURLConnection) url.openConnection();    
    	   
    	   //setRequestMethod helps to set the API methods such as GET, POST, PUT ,DELETE ...
    	   conn.setRequestMethod("GET");
    	   
    	   //setRequestProperty to set the header for an API request
    	   conn.setRequestProperty("Accept", "application/json");
    	   
    	   //getResponseCode helps to fetch the reponse code status 
    	   int responseCode = conn.getResponseCode();
    	   System.out.println(responseCode);
    	   
    	   //getInputStream used to get the api reponse(raw byte stream from server/files)	 
    	   //InputStreamReader helps to convert the byte  stream into Character Stream
    	   //BufferReader read the text line by line 
    	   BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	   
    	   String inputLine;
    	   //String Builder used to append the String for memory usage and  Time Efficiency. It is Not Synchronised
    	   
    	   StringBuilder response = new StringBuilder();
    	   
    	   //readLine() returns null when it reached an end of the stream..
    	   while ((inputLine = in.readLine())!= null) {
    		   response.append(inputLine).append("\n");
    	   }
    	   in.close();
    	   
    	   System.out.println("Response Body:");
    	   System.out.println(response.toString());
    	   
    	   conn.disconnect();
       }
    	   catch(Exception e) {
    		   e.printStackTrace();
    	   }
       }
	

}
