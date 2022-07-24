package com.library.project.service.qnAboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.domain.qnAboard.QnABoardMst;
import com.library.project.domain.qnAboard.QnABoardRepository;
import com.library.project.domain.qnAboard.SearchQnABoard;
import com.library.project.web.dto.qnAboard.QnABoardInsertReqDto;
import com.library.project.web.dto.qnAboard.QnABoardRespDto;
import com.library.project.web.dto.qnAboard.QnABoardUpdateReqDto;
import com.library.project.web.dto.qnAboard.SearchQnABoardDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnABoardServiceImpl implements QnABoardService{
	
	private final QnABoardRepository qnABoardRepository;
	
	@Override
	public List<QnABoardRespDto> qnABoardListAll() throws Exception {
		List<QnABoardRespDto> list = new ArrayList<QnABoardRespDto>();
		qnABoardRepository.qnABoardList().forEach(r -> {list.add(r.toQanABoard());});
		return list;
	}
	
	@Override
	public List<QnABoardRespDto> qnABoardListByPage(int page) throws Exception {
		List<QnABoardRespDto> qnABoardRespDtos = new ArrayList<QnABoardRespDto>();
		List<QnABoardMst> QnABoardList = qnABoardRepository.qnaBoardListByPage((page - 1) * 10);
		
		QnABoardList.forEach(qnaBoard -> {
			qnABoardRespDtos.add(qnaBoard.toQanABoard());
		});
		
		return qnABoardRespDtos;
	}
	
	@Override
	public List<QnABoardRespDto> getBoard(int qnacode) throws Exception {
		List<QnABoardRespDto> list = new ArrayList<QnABoardRespDto>();
		qnABoardRepository.getqnaBoardByQnACode(qnacode).forEach(r -> {list.add(r.toQanABoard());});
		
		return list;
	}
	
	@Override
	public List<QnABoardMst> search(int page, SearchQnABoardDto searchQnABoardDto) throws Exception {
		SearchQnABoard searchQnABoard = searchQnABoardDto.toEntity((page - 1)* 10);
		return qnABoardRepository.search(searchQnABoard);
	}
	
	@Override
	public int createQnABoard(QnABoardInsertReqDto qnABoardInsertReqDto) throws Exception{
		QnABoardMst qnABoardMst = qnABoardInsertReqDto.toQnABoardEntity();
		
		int result = qnABoardRepository.insertQnABoard(qnABoardMst);
		
		if(result > 0) {
			return qnABoardMst.getQna_code();
		}
		
		return 0;
	}
	
	@Override
	public int updateQnABoard(int qnaCode, QnABoardUpdateReqDto qnABoardupdateReqDto) throws Exception {
		QnABoardMst qnABoardMst = qnABoardupdateReqDto.toQnABoardEntity(qnaCode);
		return qnABoardRepository.updateQnABoard(qnABoardMst) > 0 ? qnaCode : 0;
	}
	
	@Override
	public int deleteQnAboard(int qnaCode) throws Exception {
		return qnABoardRepository.deleteQnABoard(qnaCode) > 0 ? qnaCode : 0;
	} 
	
}
