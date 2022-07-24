package com.library.project.domain.rentalAndLateBook;

import java.time.LocalDate;

import com.library.project.web.dto.rentalAndLateBook.RentalAndLateBookRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalAndLateBook {
	private int rental_code;
	private int usercode;
	private String bookcode;
	private String bookname;
	private int rental_status;
	private LocalDate rental_date;
	private LocalDate return_date;
	
	public RentalAndLateBookRespDto toEntity() {
		return RentalAndLateBookRespDto.builder()
				            .usercode(usercode)
							.rental_code(rental_code)
							.bookcode(bookcode)
							.bookname(bookname)
							.rental_date(rental_date)
							.return_date(return_date)
							.rental_status(rental_status)
							.build();
	}
}

