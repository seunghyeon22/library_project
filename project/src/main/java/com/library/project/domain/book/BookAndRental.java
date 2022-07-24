package com.library.project.domain.book;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookAndRental {
	private String book_file_img;
	private String bookcode;
	private String call_sign;
	private String bookname;
	private String author;
	private String publisher;
	private int year_of_publication;
	private String summary;
	private int rentals;
	private int rental_code;
	private int usercode;
	private int rental_status;
	private LocalDate rental_date;
	private LocalDate return_date;
}
