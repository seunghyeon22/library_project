package com.library.project.service.rentalBookTotal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.domain.rantalBookTotal.RentalBookTotalRepository;
import com.library.project.web.dto.rentalbookTotal.RentalBookTotalRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalBookTotalServiceImpl implements RentalBookTotalService{
	
	private final RentalBookTotalRepository rentalBookRepository;
	
	@Override
	public List<RentalBookTotalRespDto> getRentalBookTotalListAll() throws Exception {
		List<RentalBookTotalRespDto> list = new ArrayList<RentalBookTotalRespDto>();
		rentalBookRepository.getRentalBookTotalListAll().forEach(r -> {
			if((r.getReturn_date().isAfter(LocalDate.now()) || r.getReturn_date().isEqual(LocalDate.now())) && r.getRental_status() == 1)
	         list.add(r.toEntity());

		});
		return list;
	}
	
	@Override
	public List<RentalBookTotalRespDto> getLateBookTotalListAll() throws Exception {
		List<RentalBookTotalRespDto> list = new ArrayList<RentalBookTotalRespDto>();
		rentalBookRepository.getLateBookTotalListAll().forEach(r -> {
	         if(r.getReturn_date().isBefore(LocalDate.now()) && r.getRental_status() == 1)
	             list.add(r.toEntity());
	       });
		return list;
	}
	
}
