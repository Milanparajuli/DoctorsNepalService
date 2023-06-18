package com.milan.doctorsNepal.dto;

import lombok.Data;

import java.util.List;

@Data
public class IdentityResponseListDto {
    private List<IdentityResponseDto> identity;
    private int totalIdentity;
}
