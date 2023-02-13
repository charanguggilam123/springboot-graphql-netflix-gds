package com.gsc.demo.domain;

public class BookInput {

	private String name;

	private int pages;

	private int price;
	
	private String publisher;

	private Long authorId;
	
	private Rating rating;

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


	public Rating getRating() {
		return rating;
	}


	public void setRating(Rating rating) {
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
