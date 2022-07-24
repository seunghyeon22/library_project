package com.library.project.service.board;

import java.util.List;

import com.library.project.domain.board.BoardMst;
import com.library.project.web.dto.board.BoardInsertReqDto;
import com.library.project.web.dto.board.BoardRespDto;
import com.library.project.web.dto.board.BoardUpdateReqDto;
import com.library.project.web.dto.board.SearchBoardDto;

public interface BoardService {
	public List<BoardRespDto> boardListAll() throws Exception;
	public List<BoardRespDto> getBoardListByPage(int page) throws Exception;
	public List<BoardRespDto> getBoard(int boardcode) throws Exception;
	public List<BoardMst> search(int page, SearchBoardDto searchBoardDto) throws Exception;
	public int createBoard(BoardInsertReqDto boardInsertReqDto) throws Exception;
	public int updateBoard(int boardCode,BoardUpdateReqDto boardUpdateReqDto) throws Exception;
	public int deleteBoard(int boardCode) throws Exception;
}
