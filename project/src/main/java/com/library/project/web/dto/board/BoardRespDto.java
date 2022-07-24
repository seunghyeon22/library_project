package com.library.project.web.dto.board;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardRespDto {
	private int boardTotalCount;
	private int boardcode;
	private String board_title;
	private String board_username;
	private String board_contents;
	private LocalDate create_date;
	

}
