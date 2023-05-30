package com.milan.doctorsNepal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDto extends UserCreateDto{
	protected Long id;
}
