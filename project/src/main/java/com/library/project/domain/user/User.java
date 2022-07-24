package com.library.project.domain.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private int usercode;
	private String username;
	private String oauth2_username;
	private String password;
	private String name;
	private String phone;
	private int gender;
	private LocalDate birth;
	private String address1;
	private String address2;
	private String address3;
	private String email;
	private String roles; //user, admin
	private String provider;
	
	public List<String> getRoleList(){
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<String>();
	}
}
