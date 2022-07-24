package com.library.project.handler.ex;

public class NotRentalBookException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	//도서 중복 대여
	public NotRentalBookException(String message) {
		super(message);
	}
}
