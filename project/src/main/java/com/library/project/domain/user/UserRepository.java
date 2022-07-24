package com.library.project.domain.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	//아이디 중복체크
	public int userCheck(String username) throws Exception;
	//회원가입
	public int save(User user) throws Exception;
	//회원정보수정
	public int update(User user) throws Exception;
	//비밀번호 변경
	public int updatePwd(User user) throws Exception;
	//회원탈퇴
	public boolean delete(User user) throws Exception;
	
	//아이디 찾기
	public User searchId(String phone) throws Exception;
	//유저아이디로 비밀번호 찾기
	public int searchPassword(User user) throws Exception;
	//비밀번호 찾기 - 새비밀번호로 변경
	public int newPwd(User user) throws Exception;
	
	//유저정보 get
	public User findUserByUsername(String username) throws Exception;
	// oauth2 정보
	public User findOAuth2UserByOAuth2Username(String oAuth2Username);
}
