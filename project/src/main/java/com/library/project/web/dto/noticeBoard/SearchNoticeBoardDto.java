package com.library.project.web.dto.noticeBoard;

import com.library.project.domain.noticeBoard.SearchNoticeBoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchNoticeBoardDto {
	private String keyword;

	
	public SearchNoticeBoard toEntity(int page) {
		return SearchNoticeBoard.builder()
				.index(page)
				.keyword(keyword)
				.build();
		
	}
}
