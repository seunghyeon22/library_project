package com.library.project.domain.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentRepository {
	public List<CommentMst> CommentList() throws Exception;
	public List<CommentMst> CommentListByPage(int page) throws Exception;
	public List<CommentMst> getBoardByBoardcode(int page, int boardcode) throws Exception;
	public int insertComment(CommentMst commentMst) throws Exception;
	public int deleteComment(int commentcode) throws Exception;
}
