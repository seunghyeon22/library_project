package com.library.project.web.dto.rentalbookTotal;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalBookTotalRespDto {
	private int rentalCode;
	private String bookcode;
	private String bookname;
	private int rentalStatus;
	private LocalDate rentalDate;
	private LocalDate returnDate;
}
