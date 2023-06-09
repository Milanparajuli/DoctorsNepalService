package com.milan.doctorsNepal.dto;

import lombok.Data;

@Data
public class DoctorResponseDto {
    private Long id;
    private String name;

    private String address;

    private String phone;

    private String profile;

    private String speacialon;

    private String description;
}
