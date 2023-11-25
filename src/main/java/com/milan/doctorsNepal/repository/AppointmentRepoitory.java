package com.milan.doctorsNepal.repository;

import com.milan.doctorsNepal.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepoitory extends JpaRepository<Appointment, Long> {

}
