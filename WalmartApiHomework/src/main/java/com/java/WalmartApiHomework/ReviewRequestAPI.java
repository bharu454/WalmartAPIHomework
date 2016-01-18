package com.java.WalmartApiHomework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;



public class ReviewRequestAPI {

	private String apiKey;

	public ReviewRequestAPI(String apiKey) {
		this.apiKey = apiKey;
	}
	
	//Getting the Rank Ordered reviews for the first ten recommendations 

	public List<Product> getRankOrderedRecommendedItems(String productRecommendedResultsResponse) throws ParserConfigurationException, SAXException {

		JSONParser parser = new JSONParser();
		List<Product> reviews = null;
		try {
			
			//Parsing using JSON for reading the values
			
			JSONArray recommendedItemsArray = (JSONArray) parser.parse(productRecommendedResultsResponse);

			if (recommendedItemsArray != null && !recommendedItemsArray.isEmpty()) {

				reviews = new ArrayList<Product>();
				int numberOfRecommendations = 10;

				if (recommendedItemsArray.size() < numberOfRecommendations) {
					numberOfRecommendations = recommendedItemsArray.size();
				}

				for (int i = 0; i < numberOfRecommendations; i++) {
					Product product = new Product();
					JSONObject recommendedItems = (JSONObject) recommendedItemsArray.get(i);
					product.setItemId((Long) recommendedItems.get("itemId"));
					product.setName((String) recommendedItems.get("name"));
					product.setReview(new ReviewRatings().getReviewRatings(product.getItemId(), apiKey)); // Getting Average Overall RatingReview  for every Item Id in the loop

					reviews.add(product);
				}

				Collections.sort(reviews, Collections.reverseOrder()); //Sorting the List based on the Average Overall rating
			}
		} catch (ParseException e) {
			System.out.println(e);
		} catch (NullPointerException npe) {
			System.out.println("No reviews for the search Item " + npe);
		}
		return reviews;

	}

}