package com.library.project.web.controller.api.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.config.auth.PrincipalDetails;
import com.library.project.web.dto.CMRespDto;

@RestController
@RequestMapping("/api/v1/auth")
public class PrincipalController {
	@GetMapping("/principal")
	public ResponseEntity<?> getPrincipal(@AuthenticationPrincipal PrincipalDetails principalDetails){
		return new ResponseEntity<>(new CMRespDto<PrincipalDetails>(1, "get Principal success", principalDetails), HttpStatus.OK);
	}
}
