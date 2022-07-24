package com.library.project.domain.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.library.project.web.dto.comment.CommentRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentMst {
	private int commentcode;
	private int boardcode;
	private String comment_username;
	private String comment_contents;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	private int total_count;
	
	public CommentRespDto toCommentList() {
		return CommentRespDto.builder()
				.commentcode(commentcode)
				.boardcode(boardcode)
				.comment_username(comment_username)
				.comment_contents(comment_contents)
				.create_date(create_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
				.commentTotalCount(total_count)
				.build();
	}
}
