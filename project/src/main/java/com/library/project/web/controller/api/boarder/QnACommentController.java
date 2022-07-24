package com.library.project.web.controller.api.boarder;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.service.comment.QnACommentService;
import com.library.project.web.dto.comment.QnACommentInsertReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qnacomment")
public class QnACommentController {
	
	private final QnACommentService qnACommentService;
	
	@GetMapping("/list/{qna_code}")
	public ResponseEntity<?> commentList(@PathVariable int qna_code, int page) throws Exception{
		return new ResponseEntity<>(qnACommentService.getList(page, qna_code), HttpStatus.OK);
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> createQnAComment(@Valid @RequestBody QnACommentInsertReqDto qnACommentInsertReqDto, BindingResult bindingResult) throws Exception {
		int qnACommentCode = qnACommentService.createQnAComment(qnACommentInsertReqDto);
		return new ResponseEntity<>(qnACommentCode, HttpStatus.OK);
	}
	
	@DeleteMapping("/{qnaCode}")
	public ResponseEntity<?> deleteQnAComment(@PathVariable int qnaCode) throws Exception {
		int resultQnACommentCode = qnACommentService.deleteQnAComment(qnaCode);
		return new ResponseEntity<>(resultQnACommentCode, HttpStatus.OK);
	}
}
