package com.milan.doctorsNepal.repository;

import com.milan.doctorsNepal.entity.Doctor;
import com.milan.doctorsNepal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByNameContaining(String name);
}
