package com.webapp.challenge;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.webapp.challenge.util.ViewScope;

@SpringBootApplication
public class PortfolioWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioWebappApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate(){
    	return new RestTemplate();
    }
	
	@Bean(name = "scopes")
	public Map<String, Object> scopes() {
		Map<String, Object> scopes = new HashMap<String, Object>();
		scopes.put(ViewScope.NAME, new ViewScope());
		return scopes;
	}
	
	@Bean(name="customScopeConfigurer")
	public CustomScopeConfigurer customScopeConfigurer() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.setScopes(scopes());
		return configurer;
	}

}
