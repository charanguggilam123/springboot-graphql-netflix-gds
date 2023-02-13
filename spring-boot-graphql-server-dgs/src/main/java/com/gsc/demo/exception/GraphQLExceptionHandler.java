package com.gsc.demo.exception;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;

@Component
public class GraphQLExceptionHandler implements DataFetcherExceptionHandler {

	@Override
	public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(
			DataFetcherExceptionHandlerParameters handlerParameters) {

		if (handlerParameters.getException() instanceof GraphQLError error) {
			/*
			 * GraphQLError error = (GraphQLError) handlerParameters.getException();
			 * TypedGraphQLError result = TypedGraphQLError.newBuilder()
			 * .message(handlerParameters.getException().getMessage())
			 * .path(handlerParameters.getPath()) // .errorType(ErrorType.UNAUTHENTICATED)
			 * .errorDetail(error) .build();
			 */

			return CompletableFuture
					.completedFuture((DataFetcherExceptionHandlerResult.newResult().error(error).build()));

		} else
			return DataFetcherExceptionHandler.super.handleException(handlerParameters);
	}

}
