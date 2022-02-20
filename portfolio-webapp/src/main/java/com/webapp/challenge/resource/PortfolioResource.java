package com.webapp.challenge.resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioResource{

	private UserResource user;
	private String description;
	private String experienceSummary;
	private String title;
	private String twitterUser;
}
