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

public class SearchAPI {

	private String apiKey;

	public SearchAPI(String apiKey) {
		this.apiKey = apiKey;
	}

	//Retrieve the Search Results for the search Query entered
	
	public Product searchProduct(String productQuery) throws IOException, SAXException, ParserConfigurationException {

		Product product = null;

		try {

			URL url = new URL(
					"http://api.walmartlabs.com/v1/search?apiKey=" + apiKey + "&query=" + productQuery + "&format=xml");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(15 * 1000); //Setting the time out for the API connection
			connection.connect();

			InputStream inputStream = connection.getInputStream();

			try {

				DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document xmlResult = docBuilder.parse(inputStream);

				NodeList nodes = xmlResult.getElementsByTagName("items");

				for (int i = 0; i < 1; i++) {
					Element element = (Element) nodes.item(i);

					System.out.println(
							"Search results for product " + productQuery + " are " + element.getTextContent().toString());

					product = new Product();
					product.setItemId(Long.parseLong(element.getElementsByTagName("itemId").item(0).getTextContent()));  //Get the Item Id of the first Product
					product.setName(element.getElementsByTagName("name").item(0).getTextContent()); //Get the Name of the first product from search Results
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			connection.disconnect();

			return product;

		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (NullPointerException npe) {
			System.out.println("There are no Search Results for the product you entered. Please try again " + npe);
		}

		return null;

	}

}