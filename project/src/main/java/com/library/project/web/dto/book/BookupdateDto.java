package com.library.project.web.dto.book;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.library.project.domain.book.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookupdateDto {
	private MultipartFile file;
	@NotBlank
	private String call_sign;
	@NotBlank
	private String bookname;
	@NotBlank
	private String author;
	@NotBlank
	private String publisher;
	private int year_of_publication;
	private String summary;	
	
	public Book toBookupdateEntity(String book_file_img, String bookcode) {
		return Book.builder()
				.book_file_img(book_file_img)
				.bookcode(bookcode)
				.call_sign(call_sign)
				.bookname(bookname)
				.author(author)
				.publisher(publisher)
				.year_of_publication(year_of_publication)
				.summary(summary)
				.build();
	}
}
