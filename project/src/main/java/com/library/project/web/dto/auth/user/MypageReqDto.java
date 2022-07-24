package com.library.project.web.dto.auth.user;

import javax.validation.constraints.NotBlank;

import com.library.project.domain.user.User;

import lombok.Data;

@Data
public class MypageReqDto {
	@NotBlank(message = "휴대폰을 입력해주세요.")
	private String phone;
	@NotBlank(message = "주소를 입력해주세요.")
	private String address1;
	private String address2;
	private String address3;
	private String email;
	private int usercode;
	
	public User toEntity() {
		return User.builder()
				.usercode(usercode)
				.phone(phone)
				.address1(address1)
				.address2(address2)
				.address3(address3)
				.email(email)
				.build();
	}
}
