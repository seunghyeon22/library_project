package com.library.project.domain.qnAboard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.library.project.web.dto.qnAboard.QnABoardRespDto;

@Mapper
public interface QnABoardRepository {
	public List<QnABoardMst> qnABoardList() throws Exception;
	public List<QnABoardMst> qnaBoardListByPage(int page) throws Exception;
	public List<QnABoardMst> getqnaBoardByQnACode(int qnacode) throws Exception;
	public List<QnABoardMst> search(SearchQnABoard searchQnABoard) throws Exception;
	public int insertQnABoard(QnABoardMst qnABoardMst) throws Exception;
	public int updateQnABoard(QnABoardMst qnABoardMst) throws Exception;
	public int deleteQnABoard(int qnaCode) throws Exception;
}
