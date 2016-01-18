# WalmartAPIHomework
Walmart API

Project Overview:
 1.Initially, the solutions asks to enter the item that need to be searched. Taking the search query and apikey as input, search results for that item are fetched along with the first ItemId and Name of the product.
 2.By using the first item Id from the search results, product recommendations results are fetched using the API URL.
 3.Using the top ten recommendation results from the Recommendation API response, Review response is fetched for every recommendation and are rank ordered based upon the Overall Average Rating.
	 
Instructions for Executing the solution

  The project is compiled and an executable Jar is created WalmartApiHomework-0.0.1-SNAPSHOT.jar
  
  Steps:
  1. Download the above jar and place it in a desired file location.
  2. Open the command prompt and Navigate to the file location where the above Jar is available.
  3. When in the above location is set then enter --> java -cp WalmartApiHomework-0.0.1-SNAPSHOT.jar com.java.WalmartApiHomework.HomeWorkMain
  4. It will prompt for the Search query, then provide your desired query and enter
  5. It will show all the results one after the other and it will display the Rank Ordered Recommendations based on the overall Average Rating for the query provided.