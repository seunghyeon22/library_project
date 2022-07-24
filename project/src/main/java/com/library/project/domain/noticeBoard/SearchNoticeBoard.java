package com.library.project.domain.noticeBoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchNoticeBoard {
	private int index;
	private String keyword;
	
}
