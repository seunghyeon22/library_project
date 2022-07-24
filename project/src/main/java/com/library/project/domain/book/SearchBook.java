package com.library.project.domain.book;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchBook {
	private int page;
	private int contentCount;	
	private String select;
	private String keyword;
}
