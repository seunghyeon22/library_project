package com.library.project.service.qnAboard;

import java.util.List;

import com.library.project.domain.qnAboard.QnABoardMst;
import com.library.project.web.dto.qnAboard.QnABoardInsertReqDto;
import com.library.project.web.dto.qnAboard.QnABoardRespDto;
import com.library.project.web.dto.qnAboard.QnABoardUpdateReqDto;
import com.library.project.web.dto.qnAboard.SearchQnABoardDto;

public interface QnABoardService {
	public List<QnABoardRespDto> qnABoardListAll() throws Exception;
	public List<QnABoardRespDto> qnABoardListByPage(int page) throws Exception;
	public List<QnABoardRespDto> getBoard(int qnacode) throws Exception;
	public List<QnABoardMst> search(int page, SearchQnABoardDto searchQnABoardDto) throws Exception;
	public int createQnABoard(QnABoardInsertReqDto qnABoardInsertReqDto) throws Exception;
	public int updateQnABoard(int qnaCode ,QnABoardUpdateReqDto qnABoardupdateReqDto) throws Exception;
	public int deleteQnAboard(int qnaCode) throws Exception;
}
