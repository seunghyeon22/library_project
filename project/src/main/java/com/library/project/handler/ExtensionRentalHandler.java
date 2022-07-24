package com.library.project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.handler.ex.RentalExtensionException;
import com.library.project.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ExtensionRentalHandler {

	@ExceptionHandler(RentalExtensionException.class)
	public ResponseEntity<?> extensionApiException(RentalExtensionException e){
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.toString()),HttpStatus.BAD_REQUEST);
	}
}
