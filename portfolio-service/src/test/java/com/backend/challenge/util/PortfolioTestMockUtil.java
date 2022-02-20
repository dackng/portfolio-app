package com.backend.challenge.util;

import com.backend.challenge.entity.Portfolio;
import com.backend.challenge.resource.PortfolioRequest;
import com.backend.challenge.resource.PortfolioResource;
import com.backend.challenge.resource.UserRequest;
import com.backend.challenge.resource.UserResource;

public class PortfolioTestMockUtil {

	public static PortfolioResource getPortfolioResourceMock() {
		return PortfolioResource.builder()
				.description("I am Jon Snow and work as Software Engineer at Winterfell")
				.experienceSummary("I have 10 years in software development")
				.title("Java developer")
				.twitterUser("@kitharingtoncom")
				.user(getUserResourceMock())
				.build();
	}
	
	private static UserResource getUserResourceMock() {
		return UserResource.builder()
				.name("Jon")
				.lastName("Targaryen Stark")
				.imageUrl("opt/jon.jpg")
				.address("Londres Avenue #2009")
				.email("jonsnow@gmail.com")
				.phone("054999888777")
				.zipCode("02002")
				.build();
	}

	public static PortfolioRequest getPortfolioRequestMock() {
		return PortfolioRequest.builder()
				.description("I am Jon Snow and work as Software Engineer at Winterfell")
				.experienceSummary("I have 10 years in software development")
				.title("Java developer")
				.twitterUser("@kitharingtoncom")
				.user(getUserRequestMock())
				.build();
	}

	private static UserRequest getUserRequestMock() {
		return UserRequest.builder()
				.name("Jon")
				.lastName("Targaryen Stark")
				.imageUrl("opt/jon.jpg")
				.address("Londres Avenue #2009")
				.email("jonsnow@gmail.com")
				.phone("054999888777")
				.zipCode("02002")
				.build();
	}
	
	public static Portfolio getPortfolioMock(String userId) {
		return Portfolio.builder()
				.description("I am Jon Snow and work as Software Engineer at Winterfell")
				.experienceSummary("I have 10 years in software development")
				.imageUrl("opt/jon.jpg")
				.lastNames("Targaryen Stark")
				.names("Jon")
				.title("Java developer")
				.twitterUser("@kitharingtoncom")
				.userId(userId)
				.address("Londres Avenue #2009")
				.phone("054999888777")
				.email("jonsnow@gmail.com")
				.zipCode("02002")
				.build();
	}
}
