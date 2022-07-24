package com.library.project.web.dto.board;

import com.library.project.domain.board.SearchBoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchBoardDto {
	private String keyword;

	
	public SearchBoard toEntity(int page) {
		return SearchBoard.builder()
				.index(page)
				.keyword(keyword)
				.build();
		
	}
}
