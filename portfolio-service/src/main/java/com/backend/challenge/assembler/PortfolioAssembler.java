package com.backend.challenge.assembler;

import com.backend.challenge.entity.Portfolio;
import com.backend.challenge.resource.PortfolioResource;
import com.backend.challenge.resource.UserResource;

public class PortfolioAssembler {
	
	public static PortfolioResource toResource(Portfolio portfolio) {
		return PortfolioResource
                .builder()
                .description(portfolio.getDescription())
                .experienceSummary(portfolio.getExperienceSummary())
                .title(portfolio.getTitle())
                .twitterUser(portfolio.getTwitterUser())
                .user(toUserResource(portfolio))
                .build();
    } 
	
	public static UserResource toUserResource(Portfolio portfolio) {
		return UserResource
                .builder()
                .name(portfolio.getNames())
                .lastName(portfolio.getLastNames())
                .imageUrl(portfolio.getImageUrl())
                .address(portfolio.getAddress())
                .email(portfolio.getEmail())
                .phone(portfolio.getPhone())
                .zipCode(portfolio.getZipCode())
                .build();
    }
}
