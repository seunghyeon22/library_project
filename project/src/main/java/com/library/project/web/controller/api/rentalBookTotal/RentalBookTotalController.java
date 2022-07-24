package com.library.project.web.controller.api.rentalBookTotal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.service.rentalBookTotal.RentalBookTotalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booktotal")
public class RentalBookTotalController {
	
	private final RentalBookTotalService rentalBookService;
	
	@GetMapping("/rental")
	public ResponseEntity<?> getRentaTotallListAll() throws Exception{
		return new ResponseEntity<>(rentalBookService.getRentalBookTotalListAll(), HttpStatus.OK);
	}
	
	@GetMapping("/late")
	public ResponseEntity<?> getLateTotalsListAll() throws Exception{
		return new ResponseEntity<>(rentalBookService.getLateBookTotalListAll(), HttpStatus.OK);
	}
}
