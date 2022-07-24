package com.library.project.domain.rentalAndLateBook;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentalAndLateBookRepository {
	//대여도서 목록
	public List<RentalAndLateBook> getRentalBookListAll(int usercode) throws Exception;
	//연체도서 목록
	public List<RentalAndLateBook> getLateBookListAll(int usercode) throws Exception;
}
