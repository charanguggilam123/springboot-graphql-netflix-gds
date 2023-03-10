package com.gsc.demo.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthorInput {
	
	
	private String name;
	private LocalDate dob;
	private List<BookInput> books;

	public AuthorInput(String name) {
		super();
		this.name = name;
	}
	
	public AuthorInput() {
		
	}
	

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BookInput> getBooks() {
		return books;
	}

	public void setBooks(List<BookInput> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "AuthorInput [name=" + name + ", books=" + books + "]";
	}
	
	
	
	

}
