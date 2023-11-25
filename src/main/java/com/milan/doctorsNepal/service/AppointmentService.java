package com.milan.doctorsNepal.service;

import com.milan.doctorsNepal.dto.AppointmentDto;
import com.milan.doctorsNepal.entity.Appointment;
import com.milan.doctorsNepal.repository.AppointmentRepoitory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepoitory appointmentRepoitory;

    public AppointmentDto addAppointment(AppointmentDto reqquest) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(reqquest.getDoctor());
        appointment.setAppointmentDate(new Date(new Date().getTime() + 86400000));
        appointment.setPatient(reqquest.getPatient());

        appointmentRepoitory.save(appointment);
        return null;
    }

    public AppointmentDto getAppointmentResponse(Appointment appointment) {
        AppointmentDto response = new AppointmentDto();
        response.setAppointmentDate(appointment.getAppointmentDate());
        response.setAppointmentId(appointment.getAppointmentId());
        response.setDoctor(appointment.getDoctor());
        response.setPatient(appointment.getPatient());
        return response;
    }

    public AppointmentDto getAppoinmentById(Long id) {
        Optional<Appointment> optionalAppoinment = appointmentRepoitory.findById(id);
        if (optionalAppoinment.isPresent()) {
            return getAppointmentResponse(optionalAppoinment.get());
        }
        return null;
    }
}
