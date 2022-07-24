package com.library.project.domain.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.library.project.web.dto.comment.QnACommentRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnACommentMst {
	private int qnacomment_code;
	private int qna_code;
	private String qnacomment_username;
	private String qnacomment_contents;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	private int total_count;
	
	public QnACommentRespDto toQnACommentList() {
		return QnACommentRespDto.builder()
				.qnacomment_code(qnacomment_code)
				.qna_code(qna_code)
				.qnacomment_username(qnacomment_username)
				.qnacomment_contents(qnacomment_contents)
				.create_date(create_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
				.qnacommentTotalCount(total_count)
				.build();
	}

}
