package com.library.project.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.library.project.domain.comment.QnACommentMst;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnACommentInsertReqDto {
	@NotNull
	private int qnacomment_code;
	private int qna_code;
	@NotBlank
	private String qnacomment_username;
	@NotBlank
	private String qnacomment_contents;
	
	public QnACommentMst toQnACommentEntity() {
		return QnACommentMst.builder()
				.qnacomment_code(qnacomment_code)
				.qna_code(qna_code)
				.qnacomment_username(qnacomment_username)
				.qnacomment_contents(qnacomment_contents)
				.build();
	}
}
