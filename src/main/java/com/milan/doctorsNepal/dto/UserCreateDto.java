package com.milan.doctorsNepal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDto {
	private String fullName;
	private String email;
	private String password;
	private String username;
//	private int otp;
}