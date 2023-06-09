package com.milan.doctorsNepal.controller;



import com.milan.doctorsNepal.dto.DoctorResponseListDto;
import com.milan.doctorsNepal.entity.Doctor;
import com.milan.doctorsNepal.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class DoctorController {


    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping(value = "/saveDoctor")
    public List<Doctor> saveDoctor() throws IOException {

        List<Doctor> doctor = new ArrayList<>();
        return doctorService.saveDoctorListToDb(doctor);
    }


    @GetMapping("/get-doctor")
    @ResponseStatus(code = HttpStatus.OK)
    public DoctorResponseListDto getAll() {
//		System.out.println("Get all method called");
        return doctorService.getAll();
    }

    @GetMapping("/doctor-by-name/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public DoctorResponseListDto getByName(@PathVariable String name) {
        return doctorService.getDoctorByName(name);
    }

//    @GetMapping(value = "/get-doctors/{size}/{page}")
//    public Page<Doctor> getPageableDoctor(@PathVariable("size") int size, @PathVariable("page") int page) {
//        Pageable pageable = Page
//        return doctorService.getPageableDoctor();
//    }

}
