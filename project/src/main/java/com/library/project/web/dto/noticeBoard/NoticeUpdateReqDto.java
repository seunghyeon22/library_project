package com.library.project.web.dto.noticeBoard;

import javax.validation.constraints.NotBlank;

import com.library.project.domain.noticeBoard.NoticeMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeUpdateReqDto {
	@NotBlank
	private String notice_title;
	@NotBlank
	private String notice_contents;
	
	public NoticeMst toNoticeEntity(int noticeCode) {
		return NoticeMst.builder()
				.noticecode(noticeCode)
				.notice_title(notice_title)
				.notice_contents(notice_contents)
				.build();
	}
}
