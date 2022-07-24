package com.library.project.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.project.config.auth.PrincipalDetails;
import com.library.project.domain.user.User;
import com.library.project.domain.user.UserRepository;
import com.library.project.web.dto.auth.user.DeletUserReqDto;
import com.library.project.web.dto.auth.user.MypageReqDto;
import com.library.project.web.dto.auth.user.NewPwdReqDto;
import com.library.project.web.dto.auth.user.SignupReqDto;
import com.library.project.web.dto.auth.user.UpdatePwdReqDto;
import com.library.project.web.dto.auth.user.UserCheckDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	
	//아이디 중복체크
	@Override
	public boolean userCheck(UserCheckDto userCheckDto) throws Exception {
		return userRepository.userCheck(userCheckDto.getUsername())==0;
	}
	
	//회원가입
	@Override
	public boolean signup(SignupReqDto signupReqDto) throws Exception {
		return userRepository.save(signupReqDto.toEntity()) > 0; 
	}

	//회원정보수정
	@Override
	public boolean update(MypageReqDto mypageReqDto) throws Exception {
		User user = mypageReqDto.toEntity();
		return userRepository.update(user) > 0;
	}

	//비밀	번호 변경
	@Override
	public int updatePwd(PrincipalDetails principalDetails, UpdatePwdReqDto updatePwdReqDto) throws Exception {
		
		User user = updatePwdReqDto.toEntity();
		if(new BCryptPasswordEncoder().matches(updatePwdReqDto.getNewPassword(), principalDetails.getPassword()) == true) {
			return 0; //기존 비밀번호와 중복
		}else if(new BCryptPasswordEncoder().matches(updatePwdReqDto.getNowPassword(), principalDetails.getPassword()) != true){
			return 2; // 기존 비밀번호와 맞지 않음
		}else {
			return userRepository.updatePwd(user);
		}
	};
	
	//회원탈퇴
	@Override
	public boolean delete(PrincipalDetails principalDetails, DeletUserReqDto deletUserReqDto) throws Exception {
		User user = deletUserReqDto.toEntity();
		if(new BCryptPasswordEncoder().matches(deletUserReqDto.getPassword(), principalDetails.getPassword())) {
			return userRepository.delete(user);
		}else {
			return false;
		}
	}

	//ID찾기
	@Override
	public String searchId(String phone) throws Exception {
		User user = userRepository.searchId(phone);
		if(user != null) {
			return user.getUsername();
		}
		return null;
	}
	
	@Override
	public boolean searchPassword(String username, String phone) throws Exception {
		User user = User.builder().username(username).phone(phone).build();
		return userRepository.searchPassword(user) > 0;
	}

	//비밀번호 찾기 - 새비밀번호로 변경
	@Override
	public boolean newPwd(NewPwdReqDto newPwdReqDto) throws Exception {
		User user = newPwdReqDto.toEntity();
		return userRepository.newPwd(user) > 0 ;
	}

	

	

	


	


	

}
