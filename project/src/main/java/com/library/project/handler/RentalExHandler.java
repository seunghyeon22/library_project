package com.library.project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.handler.ex.NotRentalBookException;
import com.library.project.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class RentalExHandler {
	
	@ExceptionHandler(NotRentalBookException.class)
	public ResponseEntity<?> rentalApiException(NotRentalBookException e){
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.toString()),HttpStatus.BAD_REQUEST);
		
	}

	
}
