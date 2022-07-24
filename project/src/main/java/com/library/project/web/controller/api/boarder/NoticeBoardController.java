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

import com.library.project.domain.noticeBoard.SearchNoticeBoard;
import com.library.project.service.noticeBoard.NoticeBoardService;
import com.library.project.web.dto.CMRespDto;
import com.library.project.web.dto.noticeBoard.NoticeInsertReqDto;
import com.library.project.web.dto.noticeBoard.NoticeUpdateReqDto;
import com.library.project.web.dto.noticeBoard.SearchNoticeBoardDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
public class NoticeBoardController {
	
	private final NoticeBoardService noticeBoardService;
	
	@GetMapping("/list/{page}")
	public ResponseEntity<?> noticeBoardList(@PathVariable int page) throws Exception{
		return new ResponseEntity<>(noticeBoardService.noticeListByPage(page), HttpStatus.OK);
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> createNoticeBoard(@Valid @RequestBody NoticeInsertReqDto noticeInsertReqDto, BindingResult bindingResult) throws Exception{
		int noticeCode = noticeBoardService.createNoticeBoard(noticeInsertReqDto);
		return new ResponseEntity<>(noticeCode, HttpStatus.OK);
	}
	
	@GetMapping("/{noticeCode}")
	public ResponseEntity<?> getNoticeBoard(@PathVariable int noticeCode) throws Exception {
		return new ResponseEntity<>(noticeBoardService.getBoard(noticeCode) , HttpStatus.OK);
	}
	
	@PutMapping("/{noticeCode}")
	public ResponseEntity<?> updateNoticeBoard(@PathVariable int noticeCode, @Valid @RequestBody NoticeUpdateReqDto noticeUpdateReqDto, BindingResult bindingResult ) throws Exception {
		int resultNoticeCode = noticeBoardService.updateNoticeBoard(noticeCode, noticeUpdateReqDto);
		return new ResponseEntity<>(resultNoticeCode, HttpStatus.OK);
	}
	
	@DeleteMapping("/{noticeCode}")
	public ResponseEntity<?> deleteNoticeBoard(@PathVariable int noticeCode) throws Exception{
		int resultNoticeCode = noticeBoardService.deleteNoticeBoard(noticeCode);
		return new ResponseEntity<>(resultNoticeCode, HttpStatus.OK);
	}
	
	@GetMapping("/search/{page}")
	public ResponseEntity<?> search(@PathVariable int page, SearchNoticeBoardDto searchNoticeBoardDto) throws Exception{
		return new ResponseEntity<>(new CMRespDto<>(1, "", noticeBoardService.search(page, searchNoticeBoardDto)), HttpStatus.OK);
	}
	
}
