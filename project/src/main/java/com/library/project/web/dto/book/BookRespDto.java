package com.library.project.web.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRespDto {
	private String book_file_img;
	private String bookname;
	private String bookcode;
	private String call_sign;
	private String author;
	private String publisher;
	private int year_of_publication;
	private String summary;

	
}
