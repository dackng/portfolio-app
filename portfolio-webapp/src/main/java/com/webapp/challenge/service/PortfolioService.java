package com.webapp.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.webapp.challenge.error.TechError;
import com.webapp.challenge.resource.PortfolioResource;
import com.webapp.challenge.util.APIProperties;

@Component
public class PortfolioService {
	
	private RestTemplate restTemplate;
	private APIProperties apiProperties;
	
	@Autowired
	public PortfolioService(RestTemplate restTemplate, APIProperties apiProperties) {
		this.restTemplate = restTemplate;
		this.apiProperties = apiProperties;
	}
	
	public PortfolioResource getPortfolioByUser() {
		PortfolioResource portfolio;
		try {
			portfolio = restTemplate.getForObject(apiProperties.getGetPortfolioByUser().replace("{userId}", "666"), PortfolioResource.class);
		} catch (RestClientException e) {
			throw new TechError("Tech error in Portfolio API");
		}
		return portfolio;
	}
}
