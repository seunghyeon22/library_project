package com.library.project.service.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.domain.comment.QnACommentMst;
import com.library.project.domain.comment.QnACommentRepository;
import com.library.project.web.dto.comment.QnACommentInsertReqDto;
import com.library.project.web.dto.comment.QnACommentRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnACommentServiceImpl implements QnACommentService{
	
	private final QnACommentRepository qnACommentRepository;
	
	@Override
	public List<QnACommentRespDto> qnACommentListAll() throws Exception {
		List<QnACommentRespDto> list = new ArrayList<QnACommentRespDto>();
		qnACommentRepository.qnACommentList().forEach(r -> {list.add(r.toQnACommentList());});
		return list;
	}
	
	@Override
	public List<QnACommentMst> qnACommentListByPage(int page) throws Exception {
		List<QnACommentMst> commentList = qnACommentRepository.qnACommentListByPage((page - 1) * 10);
		return commentList;
	}
	
	@Override
	public List<QnACommentRespDto> getList(int page, int qna_code) throws Exception {
		List<QnACommentRespDto> list = new ArrayList<QnACommentRespDto>();
		qnACommentRepository.getQnABoardByBoardcode((page - 1) * 5,qna_code).forEach(r -> {list.add(r.toQnACommentList());});
		return list;
	}

	@Override
	public int createQnAComment(QnACommentInsertReqDto qnAcommentInsertReqDto) throws Exception {
		QnACommentMst qnACommentMst = qnAcommentInsertReqDto.toQnACommentEntity();
		int result = qnACommentRepository.insertQnAComment(qnACommentMst);
		
		if(result > 0) {
			return qnACommentMst.getQnacomment_code();
		}
		return 0;
	}

	@Override
	public int deleteQnAComment(int qnacode) throws Exception {
		return qnACommentRepository.deleteQnAComment(qnacode) > 0 ? qnacode : 0;
	}
	
}
