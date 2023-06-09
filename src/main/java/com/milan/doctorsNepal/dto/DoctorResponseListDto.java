package com.milan.doctorsNepal.dto;

import lombok.Data;

import java.util.List;

@Data
public class DoctorResponseListDto {
    private List<DoctorResponseDto> doctor;
    private int totalDoctor;
}
