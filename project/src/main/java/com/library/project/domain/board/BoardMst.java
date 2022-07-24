package com.library.project.domain.board;

import java.time.LocalDate;

import com.library.project.web.dto.board.BoardRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardMst {
	private int boardcode;
	private String board_title;
	private String board_username;
	private String board_contents;
	private LocalDate create_date;
	private LocalDate update_date;
	
	private int total_count;
	
	public BoardRespDto toBoardList() {
		return BoardRespDto.builder()
				.boardcode(boardcode)
				.board_title(board_title)
				.board_username(board_username)
				.board_contents(board_contents)
				.create_date(create_date)
				.boardTotalCount(total_count)
				.build();
	}
}
