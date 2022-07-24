package com.library.project.config.auth;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority{
	private String role;
	
	@Override
	public String getAuthority() {
		return role;
	}
	

}
