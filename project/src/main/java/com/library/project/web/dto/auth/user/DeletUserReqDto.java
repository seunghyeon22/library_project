package com.library.project.web.dto.auth.user;

import com.library.project.domain.user.User;

import lombok.Data;

@Data
public class DeletUserReqDto {
	private int usercode;
	private String password;
	
	public User toEntity() {
		return User.builder()
				.usercode(usercode)
				.build();
	}
}
