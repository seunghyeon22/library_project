package com.library.project.service.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.domain.comment.CommentMst;
import com.library.project.domain.comment.CommentRepository;
import com.library.project.web.dto.comment.CommentInsertReqDto;
import com.library.project.web.dto.comment.CommentRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentRepository commentRepository;
	
	@Override
	public List<CommentRespDto> CommentListAll() throws Exception {
		List<CommentRespDto> list = new ArrayList<CommentRespDto>();
		commentRepository.CommentList().forEach(r -> {list.add(r.toCommentList());});
		return list;
	}
	
	@Override
	public List<CommentMst> CommentListByPage(int page) throws Exception {
		List<CommentMst> commentList = commentRepository.CommentListByPage((page - 1) * 10);
		
		return commentList;
	}
	
	@Override
	public List<CommentRespDto> getList(int page, int boardcode) throws Exception {
		List<CommentRespDto> list = new ArrayList<CommentRespDto>();
		commentRepository.getBoardByBoardcode((page - 1) * 5 ,boardcode).forEach(r -> {list.add(r.toCommentList());});
		return list;
	}
	
	@Override
	public int createComment(CommentInsertReqDto commentInsertReqDto) throws Exception {
		CommentMst commentMst = commentInsertReqDto.toCommentEntity();
		int result = commentRepository.insertComment(commentMst);
		
		if(result > 0) {
			return commentMst.getCommentcode();
		}
		
		return 0;
	}

	@Override
	public int deleteComment(int commentcode) throws Exception {
		return commentRepository.deleteComment(commentcode) > 0 ? commentcode : 0;
	}

	
}
