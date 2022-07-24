package com.library.project.service.rentalAndLateBook;

import java.util.List;

import com.library.project.web.dto.rentalAndLateBook.RentalAndLateBookRespDto;

public interface RentalAndLateBookService {
	//대여도서목록
	public List<RentalAndLateBookRespDto> getRentalBookListAll(int usercode) throws Exception;
	//연체도서목록
	public List<RentalAndLateBookRespDto> getLateBookListAll(int usercode) throws Exception;
}
