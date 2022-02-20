package com.webapp.challenge.error;

public class TechError extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public TechError(String message) {
	    super(message);
	}
}
