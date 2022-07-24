package com.library.project.web.dto.qnAboard;

import com.library.project.domain.qnAboard.SearchQnABoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchQnABoardDto {
	private String keyword;

	
	public SearchQnABoard toEntity(int page) {
		return SearchQnABoard.builder()
				.index(page)
				.keyword(keyword)
				.build();
		
	}
}
