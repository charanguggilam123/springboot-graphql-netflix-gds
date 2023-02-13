package com.gsc.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apollographql.apollo3.ApolloClient;

@Configuration
public class ApolloConfig {
	
	@Value("${graphql.server.url}")
	private String serverUrl;
	
	@Bean
	ApolloClient apolloCleint() {
		
		ApolloClient apolloClient = new ApolloClient.Builder()
		        .serverUrl(serverUrl)
//		        .addHttpHeader("Authorization","Basic YWRtaW46cGFzc3dvcmQ=")
		        .build();
		    apolloClient.getCanBeBatched();
		    return apolloClient;
		
	}

}
