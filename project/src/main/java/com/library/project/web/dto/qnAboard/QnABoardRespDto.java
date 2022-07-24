package com.library.project.web.dto.qnAboard;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnABoardRespDto {
	private int qnaTotalCount;
	private int qna_code;
	private String qna_title;
	private String qna_username;
	private String qna_contents;
	private LocalDate create_date;

}
