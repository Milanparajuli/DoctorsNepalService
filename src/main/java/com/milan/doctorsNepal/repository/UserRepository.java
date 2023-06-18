package com.milan.doctorsNepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milan.doctorsNepal.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	// delete from user where fullName=?
		void deleteByFullName(String name);

		User findByUsername(String username);
		User findByUsernameOrEmail(String username, String email);

		User findByFullNameAndEmail(String name,String email);
}
