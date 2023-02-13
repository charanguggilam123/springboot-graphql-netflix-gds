package com.gsc.demo.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Author {

	@Id
	@SequenceGenerator(initialValue = 1, name = "author_id_seq", sequenceName = "author_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
	private Long id;

	private String name;

	private LocalDate dob;

	@OneToMany(mappedBy = "author", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// If not provided it does 1+n query.If provided uses IN and query once for all
	// the data
	@Fetch(FetchMode.SUBSELECT)
	private List<Book> books;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	public Author(String name, LocalDate dob) {
		super();
		this.name = name;
		this.dob = dob;
	}

	public Author() {

	}

}
