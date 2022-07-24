package com.library.project.web.controller.api.rentalbook;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.service.rentalAndLateBook.RentalAndLateBookService;
import com.library.project.web.dto.CMRespDto;
import com.library.project.web.dto.rentalAndLateBook.RentalAndLateBookRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lib")
public class RentalAndLateBookController {
	
	private final RentalAndLateBookService rentalAndLateBookService;

	//대여도서 목록
	@GetMapping("/rentalbook/{usercode}")
	public ResponseEntity<?> getLentalBookListAll(@PathVariable int usercode) throws Exception{
		List<RentalAndLateBookRespDto> rentalRespDtos = rentalAndLateBookService.getRentalBookListAll(usercode);
		
		return new ResponseEntity<>(new CMRespDto<List<RentalAndLateBookRespDto>>(1, "대여 목록 로드", rentalRespDtos), HttpStatus.OK);
	}
	
	//연체도서 목록
	@GetMapping("/latebook/{usercode}")
	public ResponseEntity<?> getLateBookListAll(@PathVariable int usercode) throws Exception{
		List<RentalAndLateBookRespDto> lateRespDtos = rentalAndLateBookService.getLateBookListAll(usercode);
		
		return new ResponseEntity<>(new CMRespDto<List<RentalAndLateBookRespDto>>(1, "연체 목록 로드", lateRespDtos), HttpStatus.OK);
	}
}

