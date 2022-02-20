package com.backend.challenge.error;

public class PortfolioNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PortfolioNotAvailableException(String message) {
	    super(message);
	}
}
