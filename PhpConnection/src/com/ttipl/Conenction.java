package com.ttipl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection; 
import java.net.URL;

 

public class Conenction {

	public static void main(String[] args) throws Exception {
		 
			URL url = new URL("http://192.168.1.104/sms/hssc_sms.php?sendsmsmsg=Dear%20Candidate,%20Your%20OTP%20for%20login%20at%20onetimeregn.haryana.gov.in%20is%20:%20234567&&mobileno=8179730126");
			HttpURLConnection conn =   (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			
			 String payload = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
		        conn.setDoOutput(true);
		        OutputStream os = conn.getOutputStream();
		        os.write(payload.getBytes("UTF-8"));
		        os.flush();
		        os.close();
		        
		        int responseCode = conn.getResponseCode();
		        System.out.println("Response code: " + responseCode);
		        
		        // Read the response from the server
		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String inputLine;
		        StringBuilder response = new StringBuilder();
		        while ((inputLine = in.readLine()) != null) {
		            response.append(inputLine);
		        }
		        in.close();
		        System.out.println("Response body: " + response.toString());
		        
		        System.out.println("Request Sent");
		 

	}

}
