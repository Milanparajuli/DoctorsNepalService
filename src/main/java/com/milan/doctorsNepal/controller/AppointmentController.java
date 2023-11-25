package com.milan.doctorsNepal.controller;

import com.milan.doctorsNepal.dto.AppointmentDto;
import com.milan.doctorsNepal.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppointmentDto addAppointment(@RequestBody AppointmentDto request) {
        return appointmentService.addAppointment(request);
    }

    @GetMapping("/by-id/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AppointmentDto getAppoinmentById(@PathVariable Long id){
        return appointmentService.getAppoinmentById(id);
    }
}
