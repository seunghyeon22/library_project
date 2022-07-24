package com.library.project.web.dto.noticeBoard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.library.project.domain.noticeBoard.NoticeMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeInsertReqDto {
	@NotNull
	private int noticecode;
	@NotBlank
	private String notice_title;
	@NotBlank
	private String notice_username;
	@NotBlank
	private String notice_contents;
	
	public NoticeMst toNoticeBoardEntity() {
		return NoticeMst.builder()
				.noticecode(noticecode)
				.notice_title(notice_title)
				.notice_username(notice_username)
				.notice_contents(notice_contents)
				.build();
	}
	
}
