package com.library.project.web.dto.board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.library.project.domain.board.BoardMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardInsertReqDto {
	@NotNull
	private int boardCode;
	@NotBlank
	private String board_username;
	@NotBlank
	private String board_title;
	@NotBlank
	private String board_contents;
	
	public BoardMst toBoardEntity() {
		return BoardMst.builder()
			.boardcode(boardCode)
			.board_username(board_username)
			.board_title(board_title)
			.board_contents(board_contents)
			.build();
	}
}
