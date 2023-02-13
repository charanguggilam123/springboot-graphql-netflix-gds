package com.gsc.demo.domain;

public class BookFilter {
	
	private int minRating;

	public int getMinRating() {
		return minRating;
	}

	public void setMinRating(int minRating) {
		this.minRating = minRating;
	}

	@Override
	public String toString() {
		return "BookFilter [minRating=" + minRating + "]";
	}
	
	

}
