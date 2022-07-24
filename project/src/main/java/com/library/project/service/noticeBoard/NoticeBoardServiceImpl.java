package com.library.project.service.noticeBoard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.domain.noticeBoard.NoticeBoardRepository;
import com.library.project.domain.noticeBoard.NoticeMst;
import com.library.project.domain.noticeBoard.SearchNoticeBoard;
import com.library.project.web.dto.noticeBoard.NoticeInsertReqDto;
import com.library.project.web.dto.noticeBoard.NoticeRespDto;
import com.library.project.web.dto.noticeBoard.NoticeUpdateReqDto;
import com.library.project.web.dto.noticeBoard.SearchNoticeBoardDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl implements NoticeBoardService{
	
	private final NoticeBoardRepository noticeBoardRepository;
	
	@Override
	public List<NoticeRespDto> noticeListAll() throws Exception {
		List<NoticeRespDto> list = new ArrayList<NoticeRespDto>();
		noticeBoardRepository.noticeBoardList().forEach(r -> {list.add(r.toNoticeBoardList());});
		return list;
	}
	
	@Override
	public List<NoticeRespDto> noticeListByPage(int page) throws Exception {
		List<NoticeRespDto> noticeRespDtos = new ArrayList<NoticeRespDto>();
		List<NoticeMst> noticeBoardList = noticeBoardRepository.noticeBoardListByPage((page - 1) * 10);
		
		noticeBoardList.forEach(notice -> {
			noticeRespDtos.add(notice.toNoticeBoardList());
		});
		return noticeRespDtos;
	}
	
	@Override
	public List<NoticeRespDto> getBoard(int noticecode) throws Exception {
		List<NoticeRespDto> list = new ArrayList<NoticeRespDto>();
		noticeBoardRepository.getNoticeBoardByNoticeCode(noticecode).forEach(r -> {list.add(r.toNoticeBoardList());});
		return list;
	}
	
	@Override
	public List<NoticeMst> search(int page, SearchNoticeBoardDto searchNoticeBoardDto) throws Exception {
		SearchNoticeBoard searchNoticeBoard = searchNoticeBoardDto.toEntity((page-1)* 10) ;
		return noticeBoardRepository.search(searchNoticeBoard);
	}
	
	@Override
	public int createNoticeBoard(NoticeInsertReqDto noticeInsertReqDto) throws Exception{
		NoticeMst noticeMst = noticeInsertReqDto.toNoticeBoardEntity();
		
		int result = noticeBoardRepository.insertNoticeBoard(noticeMst);
		
		if(result > 0) {
			return noticeMst.getNoticecode();
		}
		
		return 0;
	}

	@Override
	public int updateNoticeBoard(int noticeCode, NoticeUpdateReqDto noticeUpdateReqDto) throws Exception{
		NoticeMst noticeMst = noticeUpdateReqDto.toNoticeEntity(noticeCode);
		return noticeBoardRepository.updateNoticeBoard(noticeMst) > 0 ? noticeCode : 0;
	}

	@Override
	public int deleteNoticeBoard(int noticeCode) throws Exception{
		return noticeBoardRepository.deleteNoticeBoard(noticeCode) > 0 ? noticeCode : 0;
	}
}
