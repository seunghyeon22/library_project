package com.library.project.service.rentalAndLateBook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.domain.rentalAndLateBook.RentalAndLateBookRepository;
import com.library.project.web.dto.rentalAndLateBook.RentalAndLateBookRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalAndLateBookServiceImpl implements RentalAndLateBookService{
	
	private final RentalAndLateBookRepository rentalAndLateBookRepository;

	//대여도서목록
	@Override
	public List<RentalAndLateBookRespDto> getRentalBookListAll(int usercode) throws Exception {
		List<RentalAndLateBookRespDto> rentalBook = new ArrayList<RentalAndLateBookRespDto>();
		rentalAndLateBookRepository.getRentalBookListAll(usercode).forEach(r -> {
			if((r.getReturn_date().isAfter(LocalDate.now()) || r.getReturn_date().isEqual(LocalDate.now())) && r.getRental_status() == 1)
			rentalBook.add(r.toEntity());
		});
		return rentalBook;
	}

	//연체도서목록
	@Override
	public List<RentalAndLateBookRespDto> getLateBookListAll(int usercode) throws Exception {
		List<RentalAndLateBookRespDto> lateBook = new ArrayList<RentalAndLateBookRespDto>();
		rentalAndLateBookRepository.getLateBookListAll(usercode).forEach(r -> {
			if(r.getReturn_date().isBefore(LocalDate.now()) && r.getRental_status() == 1)
				lateBook.add(r.toEntity());
		});
		return lateBook;
	}

}
