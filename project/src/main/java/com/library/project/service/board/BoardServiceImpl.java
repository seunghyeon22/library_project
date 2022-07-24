package com.library.project.service.board;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.domain.board.BoardMst;
import com.library.project.domain.board.BoardRepository;
import com.library.project.domain.board.SearchBoard;
import com.library.project.web.dto.board.BoardInsertReqDto;
import com.library.project.web.dto.board.BoardRespDto;
import com.library.project.web.dto.board.BoardUpdateReqDto;
import com.library.project.web.dto.board.SearchBoardDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardRepository boardRepository;
	
	@Override
	public List<BoardRespDto> boardListAll() throws Exception {
		List<BoardRespDto> list = new ArrayList<BoardRespDto>();
		boardRepository.boardList().forEach(r -> {list.add(r.toBoardList());});
		return list;
	}
	
	@Override
	public List<BoardRespDto> getBoardListByPage(int page) throws Exception {
		List<BoardRespDto> boardRespDto = new ArrayList<BoardRespDto>();
		List<BoardMst> boardList = boardRepository.getBoardListByPage((page - 1) * 10);
		
		boardList.forEach(board -> {
			boardRespDto.add(board.toBoardList());
		});
		
		return boardRespDto;
	}
	
	@Override
	public List<BoardRespDto> getBoard(int boardcode) throws Exception {
		List<BoardRespDto> list = new ArrayList<BoardRespDto>();
		boardRepository.getBoardByBoardCode(boardcode).forEach(r -> {list.add(r.toBoardList());});
		return list;
	}
	
	@Override
	public List<BoardMst> search(int page, SearchBoardDto searchBoardDto) throws Exception {
		SearchBoard searchBoard = searchBoardDto.toEntity((page-1)*10);
		return boardRepository.search(searchBoard);
	}
	
	@Override
	public int createBoard(BoardInsertReqDto boardInsertReqDto) throws Exception {
		
		BoardMst boardMst = boardInsertReqDto.toBoardEntity();
		
		int result = boardRepository.insertBoard(boardMst);
		
		if (result > 0) {
			return boardMst.getBoardcode();
		}
		
		return 0;
	}

	@Override
	public int updateBoard(int boardCode, BoardUpdateReqDto boardUpdateReqDto) throws Exception {
		BoardMst boardMst = boardUpdateReqDto.toBoardEntity(boardCode);
		return boardRepository.updateBoard(boardMst) > 0 ? boardCode : 0;
	}

	@Override
	public int deleteBoard(int boardCode) throws Exception {
		return boardRepository.deleteBoard(boardCode) > 0 ? boardCode : 0;
	}
	
}
