package com.milan.doctorsNepal.dto;

import com.milan.doctorsNepal.utils.Disease;
import com.milan.doctorsNepal.utils.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDto {
	private String fullName;
	private String email;
	private String password;
	private String username;

	private RoleType roleType;
//	private Disease disease;
//	private int otp;
}
