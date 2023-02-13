package com.gsc.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apollographql.apollo3.ApolloCall;
import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Optional;
import com.apollographql.apollo3.rx3.Rx3Apollo;
import com.gsc.demo.apollo.client.client.operation.AddAuthorMutation;
import com.gsc.demo.apollo.client.client.operation.AddAuthorMutation.AddAuthor;
import com.gsc.demo.apollo.client.client.operation.AllAuthorsQuery;
import com.gsc.demo.apollo.client.client.operation.AllAuthorsQuery.AllAuthor;
import com.gsc.demo.apollo.client.client.operation.GetAuthorByIdQuery;
import com.gsc.demo.apollo.client.client.operation.GetAuthorByIdQuery.AuthorById;
import com.gsc.demo.apollo.client.client.schema.type.BookInput;
import com.gsc.demo.apollo.client.client.schema.type.RatingInput;
import com.gsc.demo.domain.AuthorInput;

import io.reactivex.rxjava3.core.Single;

@RestController
public class ClientController {

	@Autowired
	private ApolloClient apolloClient;
	
	private static final Logger LOG =LoggerFactory.getLogger(ClientController.class);

	@GetMapping("/authors")
	public List<AllAuthor> getAuthors(@RequestParam(name="minRating",defaultValue = "0",required = false) int minRating) {
		//If in quy.graphql you don't mention =<> like query allAuthors($minRating: Int!=0){ then not required
		Optional<Integer> rating=Optional.present(minRating);
		ApolloCall<AllAuthorsQuery.Data> query = apolloClient.query(new AllAuthorsQuery(rating));
		Single<ApolloResponse<AllAuthorsQuery.Data>> queryResponse = Rx3Apollo.single(query);
		List<AllAuthor> data = queryResponse.blockingGet().data.allAuthors;
		
		LOG.info("Authors Count: {}",data.size());
		return data;

	}

	@GetMapping("/authors/{id}")
	public AuthorById getAuthorsById(@PathVariable Long id,@RequestParam(name="minRating",defaultValue = "0",required = false) int minRating) {
		Optional<Integer> rating=Optional.present(minRating);
		ApolloCall<GetAuthorByIdQuery.Data> query = apolloClient.query(new GetAuthorByIdQuery(id.toString(),rating));
		Single<ApolloResponse<GetAuthorByIdQuery.Data>> queryResponse = Rx3Apollo.single(query);

		AuthorById data = queryResponse.blockingGet().data.authorById;
		LOG.info("response received for get author: {}",data);
		return data;

	}

	@PostMapping("/add-author")
	public AddAuthor addAuthor(@RequestBody AuthorInput authorInput) {
		com.gsc.demo.apollo.client.client.schema.type.AuthorInput author = transformAuthor(authorInput);
		LOG.info("Transformed author: {}",author);
		
		ApolloCall<AddAuthorMutation.Data> mutation = apolloClient.mutation(new AddAuthorMutation(author));
		Single<ApolloResponse<AddAuthorMutation.Data>> mutationResponse = Rx3Apollo.single(mutation);
		
		AddAuthor data = mutationResponse.blockingGet().data.addAuthor;
		LOG.info("response received for add author: {}",data);
		return data;

	}

	private com.gsc.demo.apollo.client.client.schema.type.AuthorInput transformAuthor(AuthorInput authorInput) {
		Optional<List<BookInput>> books = Optional.present(authorInput.getBooks().stream().map(book->{
			Optional<RatingInput> ratingInput=Optional.present(book.getRating());
			return new BookInput(book.getName(), book.getPages(), book.getPrice(), ratingInput,Optional.present(book.getPublisher()));
		}).toList());
		return  new com.gsc.demo.apollo.client.client.schema.type.AuthorInput(authorInput.getName(), authorInput.getDob().toString(), books);
	}
}
