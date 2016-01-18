package com.java.WalmartApiHomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProductRecommendRequestAPI {

	private String apiKey;

	public ProductRecommendRequestAPI(String apiKey) {
		this.apiKey = apiKey;
	}
	
	//Retrieve the Recommended Results for the First Item Id

	public String getProductRecommendedItems(final long firstResultProductId) {

		BufferedReader reader = null;
		String results = null;

		try {
			
			//No XML format for the Product Recommendation API, So fetching the results in JSON
			URL url = new URL("http://api.walmartlabs.com/v1/nbp?apiKey=" + apiKey + "&itemId=" + firstResultProductId);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.setReadTimeout(15 * 1000);
			connection.connect();
			InputStreamReader inputStreamReader = new InputStreamReader((connection.getInputStream()));
			reader = new BufferedReader(inputStreamReader);
			StringBuilder productRecommendedResults = new StringBuilder();
			while ((results = reader.readLine()) != null) {
				productRecommendedResults.append(results);
			}
			connection.disconnect();

			return productRecommendedResults.toString();
			
		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (IOException ioe) {
			System.out.println(ioe);
		} catch (NullPointerException npe) {
			System.out.println(npe);
		}
		return null;
	}

}