package com.library.project.web.dto.noticeBoard;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeRespDto {
	private int noticeTotalCount;
	private int noticecode;
	private String notice_title;
	private String notice_username;
	private String notice_contents;
	private LocalDate create_date;
	
}
