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

import com.library.project.service.qnAboard.QnABoardService;
import com.library.project.web.dto.CMRespDto;
import com.library.project.web.dto.qnAboard.QnABoardInsertReqDto;
import com.library.project.web.dto.qnAboard.QnABoardUpdateReqDto;
import com.library.project.web.dto.qnAboard.SearchQnABoardDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qnaboard")
public class QnABoardController {
	
	private final QnABoardService qnABoardService;
	
	@GetMapping("/list/{page}")
	public ResponseEntity<?> qnABoardAll(@PathVariable int page) throws Exception{
		return new ResponseEntity<>(qnABoardService.qnABoardListByPage(page), HttpStatus.OK);
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> createQnABoard(@Valid @RequestBody QnABoardInsertReqDto qnABoardInsertReqDto, BindingResult bindingResult) throws Exception{
		int qnacode = qnABoardService.createQnABoard(qnABoardInsertReqDto);
		return new ResponseEntity<>(qnacode, HttpStatus.OK);
	}
	
	@GetMapping("/{boardCode}")
	public ResponseEntity<?> getQnABoard(@PathVariable int boardCode) throws Exception {
		return new ResponseEntity<>(qnABoardService.getBoard(boardCode), HttpStatus.OK);
	}
	
	@PutMapping("/{qnaCode}")
	public ResponseEntity<?> updateQnABoard(@PathVariable int qnaCode, @Valid @RequestBody QnABoardUpdateReqDto qnABoardupdateReqDto, BindingResult bindingResult) throws Exception {
		int resultQnACode = qnABoardService.updateQnABoard(qnaCode, qnABoardupdateReqDto);
		return new ResponseEntity<>(resultQnACode, HttpStatus.OK);
	}
	
	@DeleteMapping("/{qnaCode}")
	public ResponseEntity<?> deleteBoard(@PathVariable int qnaCode) throws Exception{
		int resultQnACode = qnABoardService.deleteQnAboard(qnaCode);
		return new ResponseEntity<>(resultQnACode, HttpStatus.OK);
	}
	
	@GetMapping("/search/{page}")
	public ResponseEntity<?> search(@PathVariable int page, SearchQnABoardDto searchQnABoardDto) throws Exception {
		return new ResponseEntity<>(new CMRespDto<>(1, "", qnABoardService.search(page, searchQnABoardDto)), HttpStatus.OK);
	}
}
