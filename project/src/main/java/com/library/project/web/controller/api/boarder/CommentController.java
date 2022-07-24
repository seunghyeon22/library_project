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

import com.library.project.service.comment.CommentService;
import com.library.project.web.dto.comment.CommentInsertReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
	
	private final CommentService commentService;
	
	@GetMapping("/list/{boardCode}")
	public ResponseEntity<?> commentList(@PathVariable int boardCode, int page) throws Exception{
		return new ResponseEntity<>(commentService.getList(page ,boardCode), HttpStatus.OK);
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> createComment(@Valid @RequestBody CommentInsertReqDto commentInsertReqDto, BindingResult bindingResult) throws Exception{
		int commentCode = commentService.createComment(commentInsertReqDto);
		return new ResponseEntity<>(commentCode, HttpStatus.OK);
	}
	
	@DeleteMapping("/{commentCode}")
	public ResponseEntity<?> deleteComment(@PathVariable int commentCode) throws Exception {
		int resultCommentCode = commentService.deleteComment(commentCode);
		return new ResponseEntity<>(resultCommentCode,HttpStatus.OK);
	}
	
}
