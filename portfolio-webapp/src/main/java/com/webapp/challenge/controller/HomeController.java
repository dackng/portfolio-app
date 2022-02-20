package com.webapp.challenge.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import com.webapp.challenge.model.PortfolioModel;
import com.webapp.challenge.service.PortfolioService;
import com.webapp.challenge.service.TwitterService;
import com.webapp.challenge.util.ViewScoped;

import lombok.RequiredArgsConstructor;

@Controller("homeController")
@ViewScoped
@RequiredArgsConstructor
public class HomeController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final TwitterService twitterService;
	private final PortfolioService portfolioService;
	
	private PortfolioModel model;
	
	@PostConstruct
	public void init() {
		model = new PortfolioModel();
		model.buildInfo(portfolioService.getPortfolioByUser());
		model.buildTweets(twitterService.getTweets());
		
	}

	public PortfolioModel getModel() {
		return model;
	}

	public void setModel(PortfolioModel model) {
		this.model = model;
	}
}
