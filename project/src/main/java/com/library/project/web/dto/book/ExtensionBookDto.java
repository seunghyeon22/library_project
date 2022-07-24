package com.library.project.web.dto.book;

import com.library.project.domain.book.RentalBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtensionBookDto {
	private int rental_code;
	
	public RentalBook ExtensionBookToEntity() {
		return RentalBook.builder()
				.rental_code(rental_code)
				.build();
	}
}
