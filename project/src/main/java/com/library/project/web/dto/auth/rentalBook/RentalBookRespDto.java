package com.library.project.web.dto.auth.rentalBook;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalBookRespDto {
	private int usercode;
	private int rental_code;
	private String bookcode;
	private LocalDate rental_date;
	private LocalDate return_date;
	private int rental_status;
}
