package com.library.project.web.dto.qnAboard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.library.project.domain.qnAboard.QnABoardMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnABoardInsertReqDto {
	@NotNull
	private int qna_code;
	@NotBlank
	private String qna_title;
	@NotBlank
	private String qna_username;
	@NotBlank
	private String qna_contents;
	
	public QnABoardMst toQnABoardEntity() {
		return QnABoardMst.builder()
				.qna_code(qna_code)
				.qna_username(qna_username)
				.qna_title(qna_title)
				.qna_contents(qna_contents)
				.build();
	}
}
