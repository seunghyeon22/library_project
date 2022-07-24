package com.library.project.domain.qnAboard;

import java.time.LocalDate;

import com.library.project.web.dto.qnAboard.QnABoardRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnABoardMst {
	private int qna_code;
	private String qna_title;
	private String qna_username;
	private String qna_contents;
	private LocalDate create_date;
	private LocalDate update_date;
	
	private int total_count;
	
	public QnABoardRespDto toQanABoard() {
		return QnABoardRespDto.builder()
				.qna_code(qna_code)
				.qna_title(qna_title)
				.qna_username(qna_username)
				.qna_contents(qna_contents)
				.create_date(create_date)
				.qnaTotalCount(total_count)
				.build();
	}
}
