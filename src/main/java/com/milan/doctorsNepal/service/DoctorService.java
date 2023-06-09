package com.milan.doctorsNepal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milan.doctorsNepal.dto.DoctorRequestDto;
import com.milan.doctorsNepal.dto.DoctorResponseDto;
import com.milan.doctorsNepal.dto.DoctorResponseListDto;
import com.milan.doctorsNepal.entity.Doctor;
import com.milan.doctorsNepal.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    ResourceLoader resourceLoader;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    public List<Doctor> saveDoctorListToDb(List<Doctor> doctorList) throws IOException {
//        Resource resource = resourceLoader.getResource("src/main/resources/static/doctorsList.json");
        FileInputStream f = new FileInputStream(new File("D:/BCA/Six Semester/project-ii/Doctors Nepal/Service/doctorsNepal/doctorsList.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        List<Doctor> savedDoctors = new ArrayList<>();
        try (InputStream inputStream = f) {
            List<Doctor> doctor = List.of(objectMapper.readValue(inputStream, Doctor[].class));
            System.out.println("doctor list is:" + doctor);
            savedDoctors =  doctorRepository.saveAll(doctor);
        } catch (Exception e) {
            System.err.print(e);
        }
        return  savedDoctors;
    }


    private DoctorResponseDto getDoctorResponse(Doctor saveDoctor) {
        DoctorResponseDto response = new DoctorResponseDto();
        response.setName(saveDoctor.getName());
        response.setAddress(saveDoctor.getAddress());
        response.setDescription(saveDoctor.getDescription());
        response.setProfile(saveDoctor.getProfile());
        response.setSpeacialon(saveDoctor.getSpeacialon());
        response.setPhone(saveDoctor.getPhone());
        return response;

    }

    public DoctorResponseListDto getAll() {
        List<DoctorResponseDto> doctorResponseList = new ArrayList<>();
        List<Doctor> doctors = (List<Doctor>) doctorRepository.findAll();
        for (Doctor doctor : doctors) {
            doctorResponseList.add(getDoctorResponse(doctor));
        }
        DoctorResponseListDto response = new DoctorResponseListDto();
        response.setDoctor(doctorResponseList);
        response.setTotalDoctor(doctorResponseList.size());
        return response;
    }

    public DoctorResponseListDto getDoctorByName(String name) {
        List <DoctorResponseDto> doctorResponseList = new ArrayList<>();
        List<Doctor> doctors = (List<Doctor>) doctorRepository.findByNameContaining(name);

        for (Doctor doctor : doctors) {
            doctorResponseList.add(getDoctorResponse(doctor));
        }

        System.out.println("Doctors {}" + doctors);
        DoctorResponseListDto response = new DoctorResponseListDto();
        response.setDoctor(doctorResponseList);
        response.setTotalDoctor(doctorResponseList.size());

        return response;

    }
//    public Page<Doctor> getPageableDoctor() {
//        return  doctorRepository.findAll()
//    }
}

