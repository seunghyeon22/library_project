package com.library.project.config.auth.oauth2;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public class OAuth2User {
	private String oauth2_username;
	private String name;
	private String phone;
	private String email;
	private int gender;
	private LocalDate birth;
	
}
