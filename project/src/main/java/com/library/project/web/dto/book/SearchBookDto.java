package com.library.project.web.dto.book;

import com.library.project.domain.book.SearchBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchBookDto {
	private String select;
	private String keyword;
	
	public SearchBook toEntity(int page, int contentCount) {
		return SearchBook.builder()
				.page(page)
				.contentCount(contentCount) // 몇개씩 나눌것인가(일반검색 페이지 =20개, 인기= 10개)
				.select(select)
				.keyword(keyword)
				.build();
	}

}
