package com.milan.doctorsNepal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milan.doctorsNepal.utils.Disease;
import com.milan.doctorsNepal.utils.RoleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



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
	@JsonIgnore
	private String password;
	private boolean loggedIn;
	private RoleType roleType;
//	private Disease disease;
//	private int otp;
    //	private int otp;

}
