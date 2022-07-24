package com.library.project.web.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentRespDto {
	private int commentTotalCount;
	private int commentcode;
	private int boardcode;
	private String comment_username;
	private String comment_contents;
	private String create_date;
	
}
