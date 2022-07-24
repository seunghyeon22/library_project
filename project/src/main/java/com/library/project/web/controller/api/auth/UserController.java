package com.library.project.web.controller.api.auth;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.config.auth.PrincipalDetails;
import com.library.project.service.user.AuthService;
import com.library.project.web.dto.CMRespDto;
import com.library.project.web.dto.auth.user.DeletUserReqDto;
import com.library.project.web.dto.auth.user.MypageReqDto;
import com.library.project.web.dto.auth.user.UpdatePwdReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final AuthService authService;
	
	//회원수정
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody MypageReqDto mypageReqDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
		principalDetails.getUser().setPhone(mypageReqDto.getPhone());
		principalDetails.getUser().setAddress1(mypageReqDto.getAddress1());
		principalDetails.getUser().setAddress2(mypageReqDto.getAddress2());
		principalDetails.getUser().setAddress3(mypageReqDto.getAddress3());
		principalDetails.getUser().setEmail(mypageReqDto.getEmail());
		return new ResponseEntity<>(new CMRespDto<Boolean>(1, "회원수정완료.", authService.update(mypageReqDto)), HttpStatus.OK);
	}
	
	//비밀번호변경
	@PutMapping("/updatePwd")
	public ResponseEntity<?> updatePwd(@Valid @RequestBody UpdatePwdReqDto updatePwdReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails, BindingResult bindingResult) throws Exception{
		return new ResponseEntity<>(new CMRespDto<>(1, "비밀번호변경완료.", authService.updatePwd(principalDetails, updatePwdReqDto)), HttpStatus.OK);
	}
	
	//회원탈퇴
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@Valid @RequestBody DeletUserReqDto deletUserReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails, BindingResult bindingResult) throws Exception {
		return new ResponseEntity<>(new CMRespDto<>(1, "회원탈퇴 성공.", authService.delete(principalDetails, deletUserReqDto)), HttpStatus.OK);
	}
	
	//회원정보 불러오기
	@GetMapping("/authentication")
	public ResponseEntity<?> getAuthentication(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		return new ResponseEntity<>(new CMRespDto<PrincipalDetails>(1, "세션정보", principalDetails), HttpStatus.OK);
	}

}
