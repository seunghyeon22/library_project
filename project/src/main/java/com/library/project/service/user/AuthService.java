package com.library.project.service.user;

import com.library.project.config.auth.PrincipalDetails;
import com.library.project.web.dto.auth.user.DeletUserReqDto;
import com.library.project.web.dto.auth.user.MypageReqDto;
import com.library.project.web.dto.auth.user.NewPwdReqDto;
import com.library.project.web.dto.auth.user.SignupReqDto;
import com.library.project.web.dto.auth.user.UpdatePwdReqDto;
import com.library.project.web.dto.auth.user.UserCheckDto;

public interface AuthService {
	//아이디 중복검사
	public boolean userCheck(UserCheckDto userCheckDto) throws Exception;
	//회원가입
	public boolean signup(SignupReqDto signupReqDto) throws Exception;
	//회원정보수정
	public boolean update(MypageReqDto mypageReqDto) throws Exception;
	//비밀번호 변경
	public int updatePwd(PrincipalDetails principalDetails, UpdatePwdReqDto updatePwdReqDto) throws Exception;
	//회원탈퇴
	public boolean delete(PrincipalDetails principalDetails, DeletUserReqDto deletUserReqDto) throws Exception;
	
	//아이디 찾기
	public String searchId(String phone) throws Exception;
	//비밀번호 찾기 휴대폰 인증 찾기
	public boolean searchPassword(String username, String phone) throws Exception;
	//비밀번호 찾기 - 새비밀번호로 변경
	public boolean newPwd(NewPwdReqDto newPwdReqDto) throws Exception;
}
