package com.library.project.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.library.project.domain.comment.CommentMst;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentInsertReqDto {
	@NotNull
	private int commentcode;
	private int boardcode;
	@NotBlank
	private String comment_username;
	@NotBlank
	private String comment_contents;
	
	public CommentMst toCommentEntity() {
		return CommentMst.builder()
				.commentcode(commentcode)
				.boardcode(boardcode)
				.comment_username(comment_username)
				.comment_contents(comment_contents)
				.build();
	}

}
