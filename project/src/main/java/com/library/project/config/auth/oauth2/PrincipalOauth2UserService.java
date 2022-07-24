package com.library.project.config.auth.oauth2;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.library.project.config.auth.PrincipalDetails;
import com.library.project.domain.user.User;
import com.library.project.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("oAtu2User -> " + oAuth2User);
		System.out.println("Attributes -> " + oAuth2User.getAttributes());
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		Map<String, Object> attributes = null;
		
		attributes = (Map<String, Object>) oAuth2User.getAttributes().get("response");
		
		String oAuth2_username = createOAuth2Username(provider, attributes);
		User userEntity = userRepository.findOAuth2UserByOAuth2Username(oAuth2_username);
		if(userEntity == null) {
			
			String birthDay = (String)attributes.get("birthday");
			System.out.println(birthDay);
			int year = Integer.parseInt((String)attributes.get("birthyear"));
			int month = Integer.parseInt(birthDay.substring(0, birthDay.indexOf("-")));
			int day = Integer.parseInt(birthDay.substring(birthDay.indexOf("-") + 1));
			
			User user = User.builder()
					.username(oAuth2_username)
					.oauth2_username(oAuth2_username)
					.password(new BCryptPasswordEncoder().encode("1234"))
					.name((String)attributes.get("name"))
					.phone((String)attributes.get("mobile"))
					.email((String)attributes.get("email"))
					.gender(((String)attributes.get("gender")).equals("M") ? 0 : 1)
					.birth(LocalDate.of(year, month, day))
					.roles("ROLE_USER")
					.provider(provider)
					.build();
			
			try {
				System.out.println(user);
				if(userRepository.save(user) == 0) {
					throw new OAuth2AuthenticationException(new OAuth2Error("400", "회원가입실패", "/user/signup"), "회원가입 실패");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			userEntity = user;
		}
		
		return new PrincipalDetails(userEntity, attributes);
	}
	
	private String createOAuth2Username(String provider, Map<String, Object> attributes) {
		return provider + "_" + (String)attributes.get("id");
	}
}
