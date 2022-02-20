package com.backend.challenge.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.challenge.assembler.PortfolioAssembler;
import com.backend.challenge.entity.Portfolio;
import com.backend.challenge.error.PortfolioNotAvailableException;
import com.backend.challenge.repository.PortfolioRepository;
import com.backend.challenge.resource.PortfolioRequest;
import com.backend.challenge.resource.PortfolioResource;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioService {
	
	final PortfolioRepository portfolioRepository;

	public PortfolioResource getByUser(String userId) {
		Portfolio portfolio = portfolioRepository.findByUserId(userId)
			.orElseThrow(()-> new PortfolioNotAvailableException("Portfolio does not exist"));
		
		return PortfolioAssembler.toResource(portfolio);
	}
	
	@Transactional(readOnly = false)
	public void update(String userId, PortfolioRequest request) {
		portfolioRepository.update(userId, request);
	}

}
