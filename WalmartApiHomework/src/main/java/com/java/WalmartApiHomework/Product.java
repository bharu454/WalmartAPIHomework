package com.java.WalmartApiHomework;

import java.io.Serializable;

// Getters and Setters for the Product Attributes
public class Product implements Serializable, Comparable<Product> {

	private static final long serialVersionUID = 1L;

	private Long itemId;

	private String name;

	private Review review;

	public Long getItemId() {
		return itemId;
	}

	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Review getReview() {
		return review;
	}

	
	public void setReview(Review review) {
		this.review = review;
	}
	
	public String toString() {
		return " itemId= " + itemId + ", name = " + name + ",\n " + review ;
	}

	/*For Sorting the Items based on the Average Overall Rating*/
	public int compareTo(Product items) {

		if (this.review != null && items.review != null) {
			
			if (this.review.getAverageOverallRating() < items.review.getAverageOverallRating())
				return -1;
			else if (items.review.getAverageOverallRating() < this.review.getAverageOverallRating())
				return 1;

		}
		return 0;
	}

}