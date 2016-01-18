package com.java.WalmartApiHomework;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReviewRatings {

	//Getting the Average Overall Review Rating for the top ten recommendations 
	
	public Review getReviewRatings(Long itemId, String apiKey) throws ParserConfigurationException, SAXException {

		Review review = null;

		try {
			
			// Fetching the XML Results
			URL url = new URL("http://api.walmartlabs.com/v1/reviews/" + itemId + "?apiKey=" + apiKey + "&format=xml");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.setReadTimeout(15 * 1000);
			connection.connect();

			InputStream inputStream = connection.getInputStream();
			
			try {

				DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document xmlDoc = docBuilder.parse(inputStream);

				NodeList nodes = xmlDoc.getElementsByTagName("reviewStatistics");

				for (int i = 0; i < 1; i++) {
					Element element = (Element) nodes.item(i);
					review = new Review();
					review.setAverageOverallRating(Double.parseDouble(element.getElementsByTagName("averageOverallRating").item(0).getTextContent()));
					
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			connection.disconnect();
			
			return review;

		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		return null;
	}
}
