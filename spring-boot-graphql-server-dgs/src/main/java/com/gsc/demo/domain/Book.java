package com.gsc.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Book {
	
	@Id
	@SequenceGenerator(initialValue = 1,name = "book_id_seq",sequenceName = "book_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_id_seq")
	private Long id;
	
	private String name;
	
	private String publisher;
	
	@Enumerated(EnumType.STRING)
	private Rating rating;
	
	private int pages;
	
	private int price;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,targetEntity = Author.class)
	@JoinColumn(name = "author_id",referencedColumnName = "id")
	private Author author;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Long getId() {
		return id;
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

	public Book(String name, String publisher, Rating rating, int pages, int price) {
		super();
		this.name = name;
		this.publisher = publisher;
		this.rating = rating;
		this.pages = pages;
		this.price = price;
	}
	
	public Book() {
		
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", publisher=" + publisher + ", pages=" + pages + ", price="
				+ price + "]";
	}	
	
	

}
