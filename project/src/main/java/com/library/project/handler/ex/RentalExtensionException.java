package com.library.project.handler.ex;

public class RentalExtensionException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	//대여 연장 횟수 제한
	public RentalExtensionException(String message) {
		super(message);
		
	}
}
