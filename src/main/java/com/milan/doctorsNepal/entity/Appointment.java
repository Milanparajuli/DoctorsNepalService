package com.milan.doctorsNepal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private Date appointmentDate;

    @OneToOne(cascade=CascadeType.ALL)
    private User patient;

    @OneToOne(cascade=CascadeType.ALL)
    private Identity doctor;

}
