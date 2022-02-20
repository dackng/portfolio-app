package com.webapp.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@PropertySource("classpath:config/configuration.properties")
public class TwitterConfig {
	
	@Value("${twitter.apiKey}")
    private String apiKey;
	
    @Value("${twitter.secretKey}")
    private String secretKey;
    
    @Value("${twitter.accessToken}")
    private String accessToken;
    
    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;
	
	@Bean
	public Twitter twitterTemplate() {
		return new TwitterTemplate(
				apiKey
				, secretKey
				, accessToken
				, accessTokenSecret);
	}
}
