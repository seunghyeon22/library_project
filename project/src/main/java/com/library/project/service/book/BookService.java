package com.library.project.service.book;

import java.util.List;
import java.util.Map;

import com.library.project.domain.book.Book;
import com.library.project.domain.book.BookAndRental;
import com.library.project.web.dto.book.BookInsertDto;
import com.library.project.web.dto.book.BookcodeCheckDto;
import com.library.project.web.dto.book.BookupdateDto;
import com.library.project.web.dto.book.ExtensionBookDto;
import com.library.project.web.dto.book.RentalBookDto;
import com.library.project.web.dto.book.SearchBookDto;

public interface BookService {
	//도서코드 중복검사
	public boolean bookcodeCheck(BookcodeCheckDto bookcodeCheckDto) throws Exception;
	//도서 추가
	public boolean bookInsert(BookInsertDto bookInsertDto)throws Exception;
	//도서 수정
	public int updateBook(String bookcode, BookupdateDto bookupdateDto) throws Exception;
	//도서 삭제
	public int deleteBook(String bookcode) throws Exception;
	//도서 상세 확인
	public BookAndRental getBookDtl(String bookcode) throws Exception;
	//도서 대여
	public int rentalBookInsert(RentalBookDto rentalBookDto) throws Exception;
	//도서 반납
	public int returnBookUpdate(int Rental_code)throws Exception;
	//도서 검색
	public List<Book> searchBook(int page, int contentCount, SearchBookDto searchBookDto)throws Exception;
	//인기도서 검색
	public List<Book> searchBestBook(int page, int contentCount, SearchBookDto searchBookDto)throws Exception;
	//신간도서
	public List<Book> newBook(int page)throws Exception;
	//도서 대여 연장
	public int extensionBook(ExtensionBookDto extensionBookDto)throws Exception;
	
}
