package com.gsc.demo.exception;

import java.util.List;

import com.netflix.graphql.types.errors.ErrorType;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class DBException extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921762606036827888L;

	public DBException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

	@Override
	public List<SourceLocation> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorClassification getErrorType() {
		// TODO Auto-generated method stub
		return ErrorType.BAD_REQUEST;
	}

}
