package com.milan.doctorsNepal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "identities")

public class Identity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;
    @Column(unique = true)
    private String email;

    private String profile;

    private String specialOn;
    private String degree;
    private String nmcNo;
    private String longitude;
    private  String latitude;

//    @Column(columnDefinition = "NVARCHAR(MAX)")
    @Lob
    private String description;

    @OneToOne
    private User user;


}
