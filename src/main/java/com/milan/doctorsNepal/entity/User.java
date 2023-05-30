package com.milan.doctorsNepal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;



@Data
@NoArgsConstructor
@Entity 
@Table(name= "user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
//	@Column(nullable=false)
	private String fullName;
	private String email;
	private String username;
	private String password;
	private boolean loggedIn;
//	private int otp;
    //	private int otp;

}
