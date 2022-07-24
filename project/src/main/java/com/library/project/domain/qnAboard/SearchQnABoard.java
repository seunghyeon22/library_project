package com.library.project.domain.qnAboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchQnABoard {
	private int index;
	private String keyword;
	
}
