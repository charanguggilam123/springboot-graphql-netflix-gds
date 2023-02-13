package com.gsc.demo.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import com.gsc.demo.dataloaders.BooksDataLoader;
import com.gsc.demo.domain.Author;
import com.gsc.demo.domain.AuthorInput;
import com.gsc.demo.domain.Book;
import com.gsc.demo.repo.AuthorRepo;
import com.gsc.demo.service.AuthorService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.DgsSubscription;
import com.netflix.graphql.dgs.InputArgument;

import reactor.core.publisher.Flux;

@DgsComponent
public class AuthorComponent {

	@Autowired
	private AuthorRepo authorRepo;

	@Autowired
	private AuthorService authorService;

	@DgsQuery
	List<Author> allAuthors() {

		List<Author> data = authorRepo.findAll();
		System.out.println(data.size());
		System.out.println("returning....");
		return data;

	}

	@DgsData(parentType = "Query", field = "authorById")
	Author getAuthorById(@InputArgument("id") String authorId) {
		return authorService.findAuthorById(authorId);
	}

	@DgsData(parentType = "Author", field = "books")
	public CompletableFuture<List<Book>> books(DgsDataFetchingEnvironment dfe) {
		DataLoader<Long, List<Book>> dataLoader = dfe.getDataLoader(BooksDataLoader.class);
		Map<String, Object> contextMap = new HashMap<>();
		if (dfe.getArguments().get("bookFilter") != null) {
			contextMap.put("bookFilter", dfe.getArguments().get("bookFilter"));
			
//			filter = new ObjectMapper().convertValue(dfe.getArguments().get("bookFilter"), BookFilter.class);
		}

		Author author = dfe.getSource();
		return dataLoader.load(author.getId(), contextMap);
//		return dataLoaderResult;

	}

	@DgsMutation
	Author addAuthor(@InputArgument("author") AuthorInput authorInput) {
		return authorService.addAuthor(authorInput);

	}

	@DgsMutation
	Author deleteAuthor(String id) {
		Author author = authorService.findAuthorById(id);
		authorRepo.deleteById(Long.valueOf(id));
		return author;

	}

	@DgsSubscription(field = "authorAdded")
	public Flux<Author> subscribeAuthorAdded() {
		return authorService.problemzFlux();
	}

}
