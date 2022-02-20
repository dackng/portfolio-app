package com.webapp.challenge.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TweetModel {

	private String fromUser;
	private String text;
}
