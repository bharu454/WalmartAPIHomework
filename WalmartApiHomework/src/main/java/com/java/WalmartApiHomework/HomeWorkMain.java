package com.java.WalmartApiHomework;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom.JDOMException;
import org.xml.sax.SAXException;

// Main Class

public class HomeWorkMain {

	public static final String apiKey = "8u9v28xd6x336seeupsu6rzs";

	private static String productQuery = "";
	private static String concat = "%20";
	
	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException, JDOMException {

		
		// Read Input from the console
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the product item you want to search");
		productQuery = in.nextLine(); 
		
		// Replace the Space separated words with %20 in between words 
		String item = productQuery.replaceAll("\\s+",concat); 
		
		
		// To send API Key and Search query for retrieving the products
		SearchAPI searchAPI = new SearchAPI(apiKey);
		Product firstResultProduct = searchAPI.searchProduct(item);

		long firstResultProductId = firstResultProduct.getItemId();

		System.out.println("First Result Product Id is " + firstResultProductId);

		//To send API Key and First Result Product Id for retrieving the Recommended products
		ProductRecommendRequestAPI productRecommendRequestAPI = new ProductRecommendRequestAPI(apiKey);
		String productRecommendedResultsResponse = productRecommendRequestAPI.getProductRecommendedItems(firstResultProductId);

		System.out.println("Recommended Items for Product Id " + firstResultProductId + " are " + productRecommendedResultsResponse);

		//To send API Key and Product Recommendations Response for retrieving the Rank Ordered Recommended products
		ReviewRequestAPI reviewRequestAPI = new ReviewRequestAPI(apiKey);
		java.util.List<Product> rankOrderedRecommendations = reviewRequestAPI.getRankOrderedRecommendedItems(productRecommendedResultsResponse);

		Iterator itr = ((java.util.List) rankOrderedRecommendations).iterator();
		int i = 1;
		while (itr.hasNext()) {
			System.out.println("Rank Ordered Recommendations " + i + ")" + itr.next());
			i++;
		}
	}

}
