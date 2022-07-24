package com.library.project.service.rentalBookTotal;

import java.util.List;

import com.library.project.web.dto.rentalbookTotal.RentalBookTotalRespDto;

public interface RentalBookTotalService {
	public List<RentalBookTotalRespDto> getRentalBookTotalListAll() throws Exception;
	public List<RentalBookTotalRespDto> getLateBookTotalListAll() throws Exception;
}
