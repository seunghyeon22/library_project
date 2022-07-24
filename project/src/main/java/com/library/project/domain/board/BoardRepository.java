package com.library.project.domain.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {
	public List<BoardMst> boardList() throws Exception;
	public List<BoardMst> getBoardListByPage(int index) throws Exception;
	public List<BoardMst> getBoardByBoardCode(int boardcode) throws Exception;
	public List<BoardMst> search(SearchBoard searchBoard) throws Exception;
	public int insertBoard(BoardMst boardMst) throws Exception;
	public int updateBoard(BoardMst boardMst) throws Exception;
	public int deleteBoard(int boardcode) throws Exception;
}
