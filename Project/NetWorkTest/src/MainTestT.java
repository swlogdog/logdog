import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;


public class MainTestT {


	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		try{

			ClientReportData Temp = new ClientReportData(1);
			Gson gson = new Gson();
	
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
				"http://swlogdog7.appspot.com/logdog/Report/ErrorType");
 
			StringEntity input = new StringEntity(gson.toJson(new CallStackInfo(1)));
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			if (response.getStatusLine().getStatusCode() != 202) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			
			
			postRequest = new HttpPost(
					"http://swlogdog7.appspot.com/logdog/Report/UserInfo");
	 
			
		
			input = new StringEntity(gson.toJson(Temp));
			input.setContentType("application/json");
			postRequest.setEntity(input);

			 response = httpClient.execute(postRequest);
			if (response.getStatusLine().getStatusCode() != 202) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			
			 br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));
			
			System.out.println("Output from Server .... \n");
			String Key="";
			while ((output = br.readLine()) != null) {
				Key=output;
						System.out.println(output);
				
			}
			System.out.print(Key);
			
			
			
			postRequest = new HttpPost(
					"http://swlogdog7.appspot.com/logdog/Report/UserInfo");
	 
			HttpPut potRequest = new HttpPut(	"http://swlogdog7.appspot.com/logdog/Report/UserInfo/Key="+Key);
		
			input = new StringEntity(gson.toJson(Temp));
			input.setContentType("text/plain");
			potRequest.setEntity(input);

			 response = httpClient.execute(potRequest);
			if (response.getStatusLine().getStatusCode() != 202) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			
			 br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));
			
			System.out.println("Output from Server .... \n");
			
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			
			
			httpClient.getConnectionManager().shutdown();
			} catch (MalformedURLException e1) {
 
				e1.printStackTrace();
 
			} catch (IOException e) {
 
				e.printStackTrace();
 
			}
		
		
		
			
 
	}
		


}
