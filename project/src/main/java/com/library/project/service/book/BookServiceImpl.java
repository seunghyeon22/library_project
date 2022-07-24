package com.library.project.service.book;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.library.project.domain.book.Book;
import com.library.project.domain.book.BookAndRental;
import com.library.project.domain.book.BookRepository;
import com.library.project.domain.book.RentalBook;
import com.library.project.domain.book.SearchBook;
import com.library.project.handler.ex.NotRentalBookException;
import com.library.project.handler.ex.RentalExtensionException;
import com.library.project.web.dto.book.BookInsertDto;
import com.library.project.web.dto.book.BookcodeCheckDto;
import com.library.project.web.dto.book.BookupdateDto;
import com.library.project.web.dto.book.ExtensionBookDto;
import com.library.project.web.dto.book.RentalBookDto;
import com.library.project.web.dto.book.SearchBookDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
	
	@Value("${file.path}")
	private String filePath;
	
	private final BookRepository bookRepository;

	//도서코드 중복검사
	@Override
	public boolean bookcodeCheck(BookcodeCheckDto bookcodeCheckDto) throws Exception {	 
		return bookRepository.bookcodeCheckByBookcode(bookcodeCheckDto)==null;
	}
	//도서 추가
	@Override
	public boolean bookInsert(BookInsertDto bookInsertDto) throws Exception {
		String book_file_img = null;
		if(bookInsertDto.getFile()!=null) {
			
			String originalFileName = bookInsertDto.getFile().getOriginalFilename();
			System.out.println(originalFileName);
			book_file_img = UUID.randomUUID().toString().replaceAll("_","")+"_"+originalFileName;
			Path uploadPath=Paths.get(filePath,"book_img/"+book_file_img);
			
			File f = new File(filePath+"book_img");
			if(!f.exists()) {
				f.mkdirs();
			}
			Files.write(uploadPath, bookInsertDto.getFile().getBytes());
		}
				
		return bookRepository.bookInsert(bookInsertDto.bookinsertEntity(book_file_img))>0;
	}
	//도서 수정
	@Override
	public int updateBook(String bookcode, BookupdateDto bookupdateDto)throws Exception {
		String book_file_img=null;
		Book book = null;
		if(!bookupdateDto.getFile().getOriginalFilename().equals("")) {
			String originalFileName = bookupdateDto.getFile().getOriginalFilename();
			book_file_img = UUID.randomUUID().toString().replaceAll("_","")+"_"+originalFileName;
			Path uploadPath=Paths.get(filePath,"book_img/"+book_file_img);
			File f = new File(filePath+"book_img");
			if(!f.exists()) {
				f.mkdirs();
			}
			Files.write(uploadPath, bookupdateDto.getFile().getBytes());
			book = bookupdateDto.toBookupdateEntity(book_file_img, bookcode);
		}else{
			book = bookupdateDto.toBookupdateEntity(null, bookcode);
		}
		int result = bookRepository.updateBook(book);
		return result;
	}
	//도서 삭제
	@Override
	public int deleteBook(String bookcode) throws Exception {
		return bookRepository.deleteBook(bookcode);
	}
	//도서 상세 확인
	@Override
	public BookAndRental getBookDtl(String bookcode) throws Exception {
		
		return bookRepository.getBookAndRental(bookcode);
	}
	
	//도서 대여
	@Override
	public int rentalBookInsert(RentalBookDto rentalBookDto) throws Exception {
		RentalBook rentalBook = rentalBookDto.RentalBookToEntity();
		
		Map<String, Long> map = bookRepository.rentalOverlep(rentalBook);
		if(map.get("rental_status")==1) {	
			throw new NotRentalBookException("이미 대여중인 도서 입니다.");
		}else if(map.get("user_count") == 5) {
			throw new NotRentalBookException("대여 횟수를 초과하였습니다.");
		}
		return bookRepository.rentalBookInsert(rentalBook);
	}
	//도서 반납
	@Override
	public int returnBookUpdate(int rental_code) throws Exception {
		
		return bookRepository.returnBookUpdate(RentalBook.builder()
				.rental_code(rental_code)
				.rental_status(0)
				.build());
	}
	//도서 검색
	@Override
	public List<Book> searchBook(int page, int contentCount, SearchBookDto searchBookDto) throws Exception {
		SearchBook searchBook = searchBookDto.toEntity((page - 1) * 20, contentCount);
		return bookRepository.searchBook(searchBook);
	}
	//인기도서 검색
	@Override
	public List<Book> searchBestBook(int page, int contentCount, SearchBookDto searchBookDto) throws Exception {
		SearchBook searchBook = searchBookDto.toEntity((page - 1) * 10, contentCount);
		return bookRepository.searchBestBook(searchBook);
	}
	//신간도서
	@Override
	public List<Book> newBook(int page) throws Exception {
		return bookRepository.newBook((page - 1) * 10);
	}
	//대여기간 연장
	@Override
	public int extensionBook(ExtensionBookDto extensionBookDto) throws Exception {
		RentalBook rentalBook = bookRepository.getRentalBook(extensionBookDto.getRental_code());
		
		LocalDate ret_date= rentalBook.getReturn_date();
		LocalDate ren_date = rentalBook.getRental_date();
		
		if(ChronoUnit.DAYS.between(ren_date, ret_date)>9) {
			throw new RentalExtensionException("연장 횟수를 초과하였습니다.");
		}else {
			return bookRepository.extensionBook(rentalBook);
		}
	}
	
}
