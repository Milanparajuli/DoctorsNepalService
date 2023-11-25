package com.milan.doctorsNepal.dto;

import com.milan.doctorsNepal.entity.Identity;
import com.milan.doctorsNepal.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AppointmentDto {
    private Date appointmentDate;
    private Long appointmentId;
    private User patient;
    private Identity doctor;
}
