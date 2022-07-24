package com.library.project.domain.rantalBookTotal;

import java.time.LocalDate;

import com.library.project.web.dto.rentalbookTotal.RentalBookTotalRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentalBookTotal {
	private int rental_code;
	private int usercode;
	private String bookcode;
	private int rental_status;
	private LocalDate rental_date;
	private LocalDate return_date;
	
	private String bookname;
	
	public RentalBookTotalRespDto toEntity() {
		return RentalBookTotalRespDto.builder()
				.rentalCode(rental_code)
				.bookcode(bookcode)
				.bookname(bookname)
				.rentalStatus(rental_status)
				.rentalDate(rental_date)
				.returnDate(return_date)
				.build();
	}
}
