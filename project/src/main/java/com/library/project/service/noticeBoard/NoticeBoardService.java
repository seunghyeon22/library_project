package com.library.project.service.noticeBoard;

import java.util.List;

import com.library.project.domain.noticeBoard.NoticeMst;
import com.library.project.web.dto.noticeBoard.NoticeInsertReqDto;
import com.library.project.web.dto.noticeBoard.NoticeRespDto;
import com.library.project.web.dto.noticeBoard.NoticeUpdateReqDto;
import com.library.project.web.dto.noticeBoard.SearchNoticeBoardDto;

public interface NoticeBoardService {
	public List<NoticeRespDto> noticeListAll() throws Exception;
	public List<NoticeRespDto> noticeListByPage(int page) throws Exception;
	public List<NoticeRespDto> getBoard(int qnacode) throws Exception;
	public List<NoticeMst> search(int page, SearchNoticeBoardDto searchNoticeBoardDto) throws Exception; 
	public int createNoticeBoard(NoticeInsertReqDto noticeInsertReqDto) throws Exception;
	public int updateNoticeBoard(int noticeCode, NoticeUpdateReqDto noticeUpdateReqDto) throws Exception;
	public int deleteNoticeBoard(int noticeCode) throws Exception;
}
