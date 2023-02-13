package com.gsc.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gsc.demo.domain.Author;
import com.gsc.demo.domain.AuthorInput;
import com.gsc.demo.domain.Book;
import com.gsc.demo.domain.BookInput;
import com.gsc.demo.domain.Rating;
import com.gsc.demo.exception.DBException;
import com.gsc.demo.repo.AuthorRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class AuthorService {
	
	@Autowired
	private AuthorRepo repo;
	
	private Sinks.Many<Author> authorSink = Sinks.many().multicast().onBackpressureBuffer();
	
	public Author findAuthorById(String authorId) {
		Optional<Author> author=repo.findById(Long.valueOf(authorId));
		if(!author.isPresent())
			throw new DBException("Please enter a valid ID");
				
		return author.get();
	}
	
	public Author addAuthor(AuthorInput input) {
		Author author = new Author();
		author.setName(input.getName());
		author.setDob(input.getDob());
		if(input.getBooks()!=null) {
			author.setBooks(prepareBooks(author,input.getBooks()));
		}
		Author resp = repo.save(author);
		//emitting event for subscription
		authorSink.tryEmitNext(resp);
		return resp;
		
	}
	
	private List<Book> prepareBooks(Author author, List<BookInput> books) {

		return books.stream().map(bookInput -> {

			Book book = new Book(bookInput.getName(),bookInput.getPublisher(),bookInput.getRating(), bookInput.getPages(), bookInput.getPrice());
			book.setRating(Rating.valueOf(bookInput.getRating().name()));
			book.setPublisher(bookInput.getPublisher());
			if (author != null)
				book.setAuthor(author);
			return book;
		}).toList();
	}


	public Flux<Author> problemzFlux() {

		return authorSink.asFlux();
	}

}
