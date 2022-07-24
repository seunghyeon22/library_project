package com.library.project.domain.noticeBoard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface NoticeBoardRepository {
	public List<NoticeMst> noticeBoardList() throws Exception;
	public List<NoticeMst> noticeBoardListByPage(int page) throws Exception;
	public List<NoticeMst> getNoticeBoardByNoticeCode(int noticecode) throws Exception;
	public List<NoticeMst> search(SearchNoticeBoard searchNoticeBoard) throws Exception;
	public int insertNoticeBoard(NoticeMst noticeMst) throws Exception;
	public int updateNoticeBoard(NoticeMst noticeMst) throws Exception;
	public int deleteNoticeBoard(int noticecode) throws Exception;
}
