package com.library.project.web.dto.book;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookcodeCheckDto {
	@NotBlank
	private String bookcode;
}
