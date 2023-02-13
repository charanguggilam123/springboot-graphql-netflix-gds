package com.gsc.demo.dataloaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.MappedBatchLoaderWithContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsc.demo.domain.Book;
import com.gsc.demo.domain.BookFilter;
import com.gsc.demo.repo.BookRepo;
import com.netflix.graphql.dgs.DgsDataLoader;

@DgsDataLoader(name = "books")
public class BooksDataLoader implements MappedBatchLoaderWithContext<Long, List<Book>> {

	@Autowired
	private BookRepo bookRepo;

	@Override
	public CompletionStage<Map<Long, List<Book>>> load(Set<Long> keys, BatchLoaderEnvironment environment) {
		List<Long> vals = new ArrayList<>(keys);
		System.out.println("In data loader");

//		System.out.println(environment.getKeyContexts());
		environment.getKeyContextsList().stream().forEach(context->{
//			System.out.println(context.getClass());
			System.out.println(context);
		});
		int rating = getRating(environment);
		List<Book> allBooks = bookRepo.findBookByAuthorIds(vals).stream().filter(book -> {
			return book.getRating().getValue().length() >= rating;
		}).toList();

		Map<Long, List<Book>> answer = allBooks.stream()
				.collect(Collectors.groupingBy(book -> book.getAuthor().getId()));

		return CompletableFuture.completedStage(answer);
	}

	private int getRating(BatchLoaderEnvironment environment) {
		int rating=0;
		if (environment.getKeyContextsList().size() > 0) {
			Map<String, Object> map = new ObjectMapper().convertValue(environment.getKeyContextsList().get(0),
					Map.class);
			if (map.get("bookFilter") != null) {
				BookFilter filter = new ObjectMapper().convertValue(map.get("bookFilter"), BookFilter.class);
				rating = filter.getMinRating();
			}

		}

		System.out.println("rating:::"+rating);
		return rating;
	}

}
