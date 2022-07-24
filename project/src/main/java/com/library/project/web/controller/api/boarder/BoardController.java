package com.library.project.web.controller.api.boarder;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.service.board.BoardService;
import com.library.project.web.dto.CMRespDto;
import com.library.project.web.dto.board.BoardInsertReqDto;
import com.library.project.web.dto.board.BoardRespDto;
import com.library.project.web.dto.board.BoardUpdateReqDto;
import com.library.project.web.dto.board.SearchBoardDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/list/{page}")
	public ResponseEntity<?> boardListAll(@PathVariable int page) throws Exception{
		return new ResponseEntity<>(boardService.getBoardListByPage(page), HttpStatus.OK);
	}
	
	//게시판 추가
	@PostMapping("/post")
	public ResponseEntity<?> createBoard(@Valid @RequestBody BoardInsertReqDto boardInsertReqDto, BindingResult bindingResult) throws Exception{
		int boardCode = boardService.createBoard(boardInsertReqDto);
		return new ResponseEntity<>(boardCode, HttpStatus.OK);
	}
	
	//게시판 상세내용
	@GetMapping("/{boardCode}")
	public ResponseEntity<?> getBoard(@PathVariable int boardCode) throws Exception {
		return new ResponseEntity<>(boardService.getBoard(boardCode), HttpStatus.OK);
	}
	
	//게시판 내용 수정
	@PutMapping("/{boardCode}")
	public ResponseEntity<?> updateBoard(@PathVariable int boardCode , @Valid @RequestBody BoardUpdateReqDto boardUpdateReqDto, BindingResult bindingResult) throws Exception{
		int resultBoardCode = boardService.updateBoard(boardCode, boardUpdateReqDto);
		return new ResponseEntity<>(resultBoardCode, HttpStatus.OK);
	}
	
	//게시판 삭제
	@DeleteMapping("/{boardCode}")
	public ResponseEntity<?> deleteBoard(@PathVariable int boardCode) throws Exception{
		int resultBoardCode = boardService.deleteBoard(boardCode);
		return new ResponseEntity<>(resultBoardCode, HttpStatus.OK);
	}
	
	//게시판 검색
	@GetMapping("/search/{page}")
	public ResponseEntity<?> search(@PathVariable int page, @Valid SearchBoardDto searchBoardDto) throws Exception{	
		return new ResponseEntity<>(new CMRespDto<>(1, "", boardService.search(page, searchBoardDto)), HttpStatus.OK);
	}
}
