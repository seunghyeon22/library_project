package com.library.project.web.dto.qnAboard;

import javax.validation.constraints.NotBlank;

import com.library.project.domain.qnAboard.QnABoardMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnABoardUpdateReqDto {
	
	@NotBlank
	private String qna_title;
	@NotBlank
	private String qna_contents;
	
	public QnABoardMst toQnABoardEntity(int qnaCode){
		return QnABoardMst.builder()
				.qna_code(qnaCode)
				.qna_title(qna_title)
				.qna_contents(qna_contents)
				.build();
	}

}
