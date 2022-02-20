package com.webapp.challenge.service;

import java.util.List;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.webapp.challenge.error.TechError;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TwitterService {
	
	final private Twitter twitterTemplate;
		
	public List<Tweet> getTweets() {
		List<Tweet> tweets;
		try {
			tweets = twitterTemplate.timelineOperations().getUserTimeline();
		} catch (RestClientException e) {
			throw new TechError("Tech error in Twitter API");
		}
		return tweets;
	}
}
