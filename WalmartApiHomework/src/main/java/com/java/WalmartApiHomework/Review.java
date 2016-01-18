package com.java.WalmartApiHomework;

//Setters and Getters for the review API 
public class Review {

	private double averageOverallRating;

	/**
	 * @return the averageOverallRating
	 */
	public double getAverageOverallRating() {
		return averageOverallRating;
	}

	/**
	 * @param averageOverallRating
	 *            the averageOverallRating to set
	 */
	public void setAverageOverallRating(double averageOverallRating) {
		this.averageOverallRating = averageOverallRating;
	}

	
	public String toString() {
		return "averageOverallRating = " + averageOverallRating;
	}

}