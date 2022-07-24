package com.library.project.domain.book;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.library.project.web.dto.book.BookcodeCheckDto;

@Mapper
public interface BookRepository {
	//도서코드 중복검사
	public Book bookcodeCheckByBookcode(BookcodeCheckDto bookcodeCheckDto) throws Exception;
	//도서 추가
	public int bookInsert(Book book)throws Exception;
	//도서 수정
	public int updateBook(Book book)throws Exception;
	//도서 삭제
	public int deleteBook(String bookcode)throws Exception;
	//도서 상세 확인
	public Book getBookByBookcode(String bookcode)throws Exception;
	//도서 대여
	public int rentalBookInsert(RentalBook rentalBook)throws Exception;
	//도서 반납
	public int returnBookUpdate(RentalBook rentalBook)throws Exception;
	//도서 검색
	public List<Book> searchBook(SearchBook searchBook) throws Exception;
	//인기도서 검색
	public List<Book> searchBestBook(SearchBook searchBook) throws Exception;
	//신간도서
	public List<Book> newBook(int page)throws Exception;
	//대여기간 연장
	public int extensionBook(RentalBook rentalBook)throws Exception;
	// 중복 대여 및 대여횟수 제한
	public Map<String, Long> rentalOverlep(RentalBook rentalBook)throws Exception;
	// 대여도서 확인
	public RentalBook getRentalBook(int rental_code)throws Exception;
	
	public BookAndRental getBookAndRental(String bookcode)throws Exception;
	
	
	
}
