package com.gsc.demo.domain;

public enum Rating {
	
	FIVE_STARS("*****"),
	FOUR_STARS("****"),
	THREE_STARS("***"),
	TWO_STARS("**"),
	ONE_STARS("*");
	
	
	private String value;
	
	Rating(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}
	
	

}
