package com.library.project.web.dto.auth.user;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.library.project.domain.user.User;

import lombok.Data;

@Data
public class SignupReqDto {
	@NotBlank(message = "아이디를 입력해주세요.")
	@Pattern(regexp = "^[A-za-z0-9]{6,20}$",
	message = "아이디는 영문 또는 숫자만 가능하며 6자 이상 20자 이내만 가능합니다.")
	private String username;
	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\w).{9,20}",
	message = "비밀번호는 영문 대, 소문자와 숫자, 특수문자를 포함하여 9자 이상이어야 합니다.")
	private String password;
	@NotBlank(message = "이름을 입력해주세요.")
	private String name;
	@NotBlank(message = "휴대폰을 입력해주세요.")
	private String phone;
	@NotNull(message = "둘 중 하나를 선택해주세요.")
	private int gender;
	@NotNull(message = "생년월일을 입력해주세요.")
	private String birth;
	@NotBlank(message = "주소를 입력해주세요.")
	private String address1;
	private String address2;
	private String address3;
	private String email;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(new BCryptPasswordEncoder().encode(password))
				.name(name)
				.phone(phone)
				.gender(gender)
				.birth(Date.valueOf(birth).toLocalDate())
				.address1(address1)
				.address2(address2)
				.address3(address3)
				.email(email)
				.roles("ROLE_USER")
				.build();
	}
	
	
	
}
