package com.library.project.web.dto.board;

import javax.validation.constraints.NotBlank;

import com.library.project.domain.board.BoardMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardUpdateReqDto {
	@NotBlank
	private String board_title;
	@NotBlank
	private String board_contents;
	
	public BoardMst toBoardEntity(int boardcode) {
	 return	BoardMst.builder()
			.boardcode(boardcode)
			.board_title(board_title)
			.board_contents(board_contents)
			.build();
	}
}
