package com.milan.doctorsNepal.dto;

import lombok.Data;

@Data
public class IdentityResponseDto {
    private Long id;
    private String name;

    private String address;

    private String phone;

    private String profile;

    private String speacialon;

    private String description;
    private String degree;
    private String nmcNo;
}
