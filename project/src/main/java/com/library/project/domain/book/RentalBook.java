package com.library.project.domain.book;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RentalBook {
	private int rental_code;
	private int usercode;
	private String bookcode;
	private int rental_status;
	private LocalDate rental_date;
	private LocalDate return_date;
	
}
