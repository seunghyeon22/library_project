package com.library.project.domain.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnACommentRepository {
	public List<QnACommentMst> qnACommentList() throws Exception;
	public List<QnACommentMst> qnACommentListByPage(int page) throws Exception;
	public List<QnACommentMst> getQnABoardByBoardcode(int page, int qna_code) throws Exception;
	public int insertQnAComment(QnACommentMst acommentMst) throws Exception;
	public int deleteQnAComment(int qnacode) throws Exception;
}
