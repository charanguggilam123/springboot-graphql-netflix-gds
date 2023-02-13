package com.gsc.demo.domain;

import com.gsc.demo.apollo.client.client.schema.type.Rating;
import com.gsc.demo.apollo.client.client.schema.type.RatingInput;

public class BookInput {

	private String name;

	private int pages;

	private int price;
	
	private String publisher;

	private Long authorId;
	
	private RatingInput rating;

	public BookInput(String name, int pages,Rating rating, int price, Long authorId) {
		super();
		this.name = name;
		this.pages = pages;
		this.price = price;
		this.authorId = authorId;
	}

	
	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public RatingInput getRating() {
		return rating;
	}


	public void setRating(RatingInput rating) {
		this.rating = rating;
	}


	public BookInput() {

	}

	public String getName() {
		return name;
	}

	public int getPages() {
		return pages;
	}

	public int getPrice() {
		return price;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [ name=" + name + ", pages=" + pages + ", price=" + price + "]";
	}

}
