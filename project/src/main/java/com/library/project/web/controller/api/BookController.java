package com.library.project.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.config.auth.PrincipalDetails;
import com.library.project.domain.book.Book;
import com.library.project.domain.book.BookAndRental;
import com.library.project.service.book.BookService;
import com.library.project.web.dto.CMRespDto;
import com.library.project.web.dto.book.BookInsertDto;
import com.library.project.web.dto.book.BookcodeCheckDto;
import com.library.project.web.dto.book.BookupdateDto;
import com.library.project.web.dto.book.ExtensionBookDto;
import com.library.project.web.dto.book.RentalBookDto;
import com.library.project.web.dto.book.SearchBookDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
	//도서코드 중복검사 
	@GetMapping("/admain/code/check")
	public ResponseEntity<?> bookcodeCheckByBookcode(@Valid BookcodeCheckDto bookcodeCheckDto)throws Exception{
		return new ResponseEntity<>(new CMRespDto<>(1, "사용가능", bookService.bookcodeCheck(bookcodeCheckDto)),HttpStatus.OK);
	}
	//도서 추가
	@PostMapping("/admain")
	public ResponseEntity<?> bookinsert(@Valid BookInsertDto bookInsertDto)throws Exception{
		return new ResponseEntity<>(new CMRespDto<>(1, "추가완료",bookService.bookInsert(bookInsertDto)),HttpStatus.OK);
	}
	//도서 수정
	@PostMapping("/admain/{bookcode}")
	public ResponseEntity<?> updateBook(@PathVariable String bookcode, BookupdateDto bookupdateDto )throws Exception{
		return new ResponseEntity<>(new CMRespDto<>(1, "수정완료", bookService.updateBook(bookcode, bookupdateDto)),HttpStatus.OK);
	}
	//도서 삭제
	@DeleteMapping("/admain/{bookcode}")
	public ResponseEntity<?> deleteBook(@PathVariable String bookcode)throws Exception{
		return new ResponseEntity<>(new CMRespDto<>(1, "삭제왼료", bookService.deleteBook(bookcode)),HttpStatus.OK);
	}
	//도서 상세 확인
	@GetMapping("/all/{bookcode}")
	public ResponseEntity<?> getBookDtl(@PathVariable String bookcode)throws Exception{
		return new ResponseEntity<>(new CMRespDto<BookAndRental>(1, "도서 조회 완료", bookService.getBookDtl(bookcode)),HttpStatus.OK);
	}
	//도서 대여
	@PostMapping("/user/rental")
	public ResponseEntity<?> rentalBook(@Valid RentalBookDto rentalBookDto)throws Exception{
		return new ResponseEntity<>(new CMRespDto<>(1, "도서 대여 ",bookService.rentalBookInsert(rentalBookDto)),HttpStatus.OK);
	}
	//도서 반납
	@PutMapping("/user/return")
	public ResponseEntity<?> returnBook(@Valid int rental_code)throws Exception{		
		return new ResponseEntity<>(new CMRespDto<>(1, "도서 반납 ",bookService.returnBookUpdate(rental_code)),HttpStatus.OK);
	}
	//도서 검색
	@GetMapping("/all/main/search/{page}")
	public ResponseEntity<?> searchBook(@PathVariable int page, @Valid SearchBookDto searchBookDto, int contentCount)throws Exception{
		
		return new ResponseEntity<>(new CMRespDto<>(1, "도서 검색 ", bookService.searchBook(page, contentCount, searchBookDto)),HttpStatus.OK);
	}
	//인기도서 검색
	@GetMapping("/all/main/search/best/{page}")
	public ResponseEntity<?> searchBestBook(@PathVariable int page, @Valid SearchBookDto searchBookDto, int contentCount)throws Exception{	
		return new ResponseEntity<>(new CMRespDto<>(1, "도서 검색 ", bookService.searchBestBook(page, contentCount, searchBookDto)),HttpStatus.OK);
		}
	//신간도서
	@GetMapping("/all/list/new/{page}")
	public ResponseEntity<?> newBookList(@PathVariable int page)throws Exception{
		return new ResponseEntity<>(new CMRespDto<>(1,"신간도서",bookService.newBook(page)),HttpStatus.OK);
	}
	//대여기간 연장
	@PutMapping("/user/extension")
	public ResponseEntity<?> extensionBook(@Valid ExtensionBookDto extensionBookDto)throws Exception{
		return new ResponseEntity<>(new CMRespDto<>(1,"대여기간 연장",bookService.extensionBook(extensionBookDto)),HttpStatus.OK);
	}
	
}
