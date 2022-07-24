package com.library.project.domain.noticeBoard;

import java.time.LocalDate;

import com.library.project.web.dto.noticeBoard.NoticeRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeMst {
	private int noticecode;
	private String notice_title;
	private String notice_username;
	private String notice_contents;
	private LocalDate create_date;
	private LocalDate update_date;
	
	private int total_count;
	
	public NoticeRespDto toNoticeBoardList() {
		return NoticeRespDto.builder()
				.noticecode(noticecode)
				.notice_title(notice_title)
				.notice_username(notice_username)
				.notice_contents(notice_contents)
				.create_date(create_date)
				.noticeTotalCount(total_count)
				.build();
	}
}
