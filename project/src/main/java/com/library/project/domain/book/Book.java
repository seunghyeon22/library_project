package com.library.project.domain.book;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {
	private String book_file_img;
	private String bookcode;
	private String call_sign;
	private String bookname;
	private String author;
	private String publisher;
	private int year_of_publication;
	private String summary;
	private int rentals;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	private int total_count;
}
