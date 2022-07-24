package com.library.project.web.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnACommentRespDto {
	private int qnacommentTotalCount;
	private int qnacomment_code;
	private int qna_code;
	private String qnacomment_username;
	private String qnacomment_contents;
	private String create_date;
}
