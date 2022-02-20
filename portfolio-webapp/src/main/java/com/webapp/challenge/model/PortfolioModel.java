package com.webapp.challenge.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.social.twitter.api.Tweet;

import com.webapp.challenge.resource.PortfolioResource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String description;
	private String title;
	private String name;
	private String lastName;
	private List<TweetModel> tweets;
	
	public PortfolioModel() {
		tweets = new ArrayList<>();
	}
	
	public void buildInfo(PortfolioResource resource) {
		description = resource.getDescription();
		title = resource.getTitle();
		name = resource.getUser().getName();
		lastName = resource.getUser().getLastName();
	}
	
	public void buildTweets(List<Tweet> tweets) {
		this.tweets = tweets.stream()
				.map(t -> TweetModel.builder()
						.fromUser(t.getFromUser())
						.text(t.getText())
						.build())
				.collect(Collectors.toList());
	}
}
