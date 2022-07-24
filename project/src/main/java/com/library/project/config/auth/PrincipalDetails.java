package com.library.project.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.library.project.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Map<String, Object> attribues;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	
	public PrincipalDetails(User userEntity, Map<String, Object> attributes) {
		this.user = userEntity;
		this.attribues = attributes;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //계정의 권한 목록을 리턴
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		user.getRoleList().forEach(r -> {
			System.out.println("권한: " + r);
			authorities.add(() -> r);
		});
		authorities.forEach(r -> {System.out.println("리스트에 들어있는 권한: " + r.getAuthority());});
		return authorities;
	}

	@Override
	public String getPassword() { //계정의 비밀번호를 리턴
		return user.getPassword();
	}

	@Override
	public String getUsername() { //계정의 고유한 값을 리턴(중복이없는 값)
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //계정이 만료되었는지 확인 --> true(만료 안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 비밀번호가 지정한 횟수가 이상 틀리면 잠김 --> true(잠기지 않음)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //자격 증명이 만료가 되면 계정사용 불가 --> true(만료 안됨)
		return true;
	}

	@Override
	public boolean isEnabled() { //휴먼계정 --> true(활성화 됨)
		return true;
	}


	@Override
	public Map<String, Object> getAttributes() {
		return attribues;
	}


	@Override
	public String getName() {
		return (String)attribues.get("name");
	}

}
