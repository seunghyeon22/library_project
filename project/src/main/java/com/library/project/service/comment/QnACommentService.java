package com.library.project.service.comment;

import java.util.List;

import com.library.project.domain.comment.QnACommentMst;
import com.library.project.web.dto.comment.QnACommentInsertReqDto;
import com.library.project.web.dto.comment.QnACommentRespDto;

public interface QnACommentService {
	public List<QnACommentRespDto> qnACommentListAll() throws Exception;
	public List<QnACommentMst> qnACommentListByPage(int page) throws Exception;
	public List<QnACommentRespDto> getList(int page, int qna_code) throws Exception;
	public int createQnAComment(QnACommentInsertReqDto commentInsertReqDto) throws Exception;
	public int deleteQnAComment(int qnacode) throws Exception;
}
