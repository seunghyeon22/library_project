package com.library.project.web.dto.auth.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.library.project.domain.user.User;

import lombok.Data;

@Data
public class UpdatePwdReqDto {
	private int usercode;
	private String nowPassword;
	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\w)).{9,20}",
	message = "비밀번호는 영문 대, 소문자와 숫자, 특수문자를 포함하여 9자 이상이어야 합니다.")
	private String newPassword;
	
	public User toEntity() {
		return User.builder()
				.usercode(usercode)
				.password(new BCryptPasswordEncoder().encode(newPassword))
				.build();
	}
}
