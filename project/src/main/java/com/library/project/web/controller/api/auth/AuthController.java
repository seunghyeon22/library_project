package com.library.project.web.controller.api.auth;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.service.user.AuthService;
import com.library.project.web.dto.CMRespDto;
import com.library.project.web.dto.auth.user.NewPwdReqDto;
import com.library.project.web.dto.auth.user.SignupReqDto;
import com.library.project.web.dto.auth.user.UserCheckDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	//중복검사
	
	@GetMapping("/signup/check")
	public ResponseEntity<?> checkUsername(@Valid UserCheckDto userCheckDto)throws Exception{
		
		return new ResponseEntity<>(new CMRespDto<>(1, "사용가능",authService.userCheck(userCheckDto)),HttpStatus.OK);
	}
	
	//회원가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult) throws Exception {
		return new ResponseEntity<>(new CMRespDto<Boolean>(1, "회원가입 완료", authService.signup(signupReqDto)), HttpStatus.OK);
	}
	
	//id찾기
	@GetMapping("/searchId")
	public ResponseEntity<?> searchId(String phone) throws Exception {
		return new ResponseEntity<>(new CMRespDto<String>(1, "아이디찾기 성공.", authService.searchId(phone)), HttpStatus.OK);
	}
	
	//password찾기
	@GetMapping("/searchPassword")
	public ResponseEntity<?> searchPassword(String username, String phone) throws Exception {
		return new ResponseEntity<>(new CMRespDto<Boolean>(1, "아이디찾기 성공.", authService.searchPassword(username, phone)), HttpStatus.OK);
	}
	
	//비밀번호 찾기 - 새비밀번호로 변경
	@PutMapping("/newPwd")
	public ResponseEntity<?> newPwd(@Valid @RequestBody NewPwdReqDto newPwdReqDto, BindingResult bindingResult) throws Exception{
		return new ResponseEntity<>(new CMRespDto<Boolean>(1, "비밀번호변경완료.", authService.newPwd(newPwdReqDto)), HttpStatus.OK);
	}
		
	//user
	@GetMapping("/user")
	public ResponseEntity<?> user() {
		return new ResponseEntity<>(new CMRespDto<String>(1, "유저권한", "role_user"), HttpStatus.OK);
	}
	//admin
	@GetMapping("/admin")
	public ResponseEntity<?> admin() {
		return new ResponseEntity<>(new CMRespDto<String>(1, "관리자권한", "role_admin"), HttpStatus.OK);
	}
	
}
