package com.library.project.domain.rantalBookTotal;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentalBookTotalRepository {
	public List<RentalBookTotal> getRentalBookTotalListAll() throws Exception;
	public List<RentalBookTotal> getLateBookTotalListAll() throws Exception;
}
