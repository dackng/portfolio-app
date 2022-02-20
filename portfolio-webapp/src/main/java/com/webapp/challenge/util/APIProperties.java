package com.webapp.challenge.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;


@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:config/api.properties")
@ConfigurationProperties(prefix="portfolio")
@Getter
@Setter
public class APIProperties {
	
	private String getPortfolioByUser;
	private String updatePortfolio;
}
