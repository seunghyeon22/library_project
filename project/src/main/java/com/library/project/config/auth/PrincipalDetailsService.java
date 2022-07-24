package com.library.project.config.auth;

import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.project.domain.user.User;
import com.library.project.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		User userEntity = null;
		try {
			userEntity = userRepository.findUserByUsername(username);
			System.out.println(userEntity);
			if(userEntity == null) {
				throw new UsernameNotFoundException(username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("로그인 요청");
		
		if(userEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new PrincipalDetails(userEntity, new HashMap<String, Object>());

	}
}
