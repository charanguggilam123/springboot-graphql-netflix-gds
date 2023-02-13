package com.gsc.demo;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gsc.demo.domain.Author;
import com.gsc.demo.domain.Book;
import com.gsc.demo.domain.Rating;
import com.gsc.demo.repo.AuthorRepo;
import com.gsc.demo.repo.BookRepo;

@SpringBootApplication
public class SpringBootGraphqlServerDgsApplication implements CommandLineRunner {

	@Autowired
	private AuthorRepo authorRepo;
	
	@Autowired
	private BookRepo bookRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraphqlServerDgsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Author author1 = new Author("George R.R Martin", LocalDate.now());
		Author author2 = new Author("Stephen King",LocalDate.of(1992,10,20));
		Author author3 = new Author("J.k Rowling",LocalDate.of(1902,10,20));

		Book book1 = new Book("House Of Dragon","PaperBack",Rating.FOUR_STARS, 1200, 100);
		Book book2 = new Book("Game Of Thrones","King",Rating.TWO_STARS, 567, 899);
		Book book3 = new Book("CastleRock",null,Rating.THREE_STARS, 455, 499);
		book1.setAuthor(author1);
		book2.setAuthor(author1);
		book3.setAuthor(author2);
		
		bookRepo.saveAll(Arrays.asList(book1, book2, book3));
		authorRepo.save(author3);

	}

}
