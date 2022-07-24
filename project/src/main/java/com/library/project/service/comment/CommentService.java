package com.library.project.service.comment;

import java.util.List;

import com.library.project.domain.comment.CommentMst;
import com.library.project.web.dto.comment.CommentInsertReqDto;
import com.library.project.web.dto.comment.CommentRespDto;

public interface CommentService {
	public List<CommentRespDto> CommentListAll() throws Exception;
	public List<CommentMst> CommentListByPage(int page) throws Exception;
	public List<CommentRespDto> getList(int page, int boardcode) throws Exception;
	public int createComment(CommentInsertReqDto commentInsertReqDto) throws Exception;
	public int deleteComment(int commentCode) throws Exception;
}
