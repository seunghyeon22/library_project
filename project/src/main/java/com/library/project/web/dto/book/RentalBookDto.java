package com.library.project.web.dto.book;

import com.library.project.domain.book.RentalBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalBookDto {
	private int usercode;
	private String bookcode;
	private int rental_stutas = 1;
	
	public RentalBook RentalBookToEntity() {
		return RentalBook.builder()
				.usercode(usercode)
				.bookcode(bookcode)
				.rental_status(rental_stutas)
				.build();
	}
}
