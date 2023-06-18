package com.milan.doctorsNepal.controller;


import com.milan.doctorsNepal.dto.IdentityResponseListDto;
import com.milan.doctorsNepal.entity.Identity;
import com.milan.doctorsNepal.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class IdentityController {


    private final IdentityService identityService;

    @Autowired
    public IdentityController(IdentityService identityService) {
        this.identityService = identityService;
    }

    @RequestMapping(value = "/saveIdentity")
    public List<Identity> saveIdentity () throws IOException {

        List<Identity> identity = new ArrayList<>();
        return identityService.saveIdentityListToDb(identity);
    }


    @GetMapping("/get-identity")
    @ResponseStatus(code = HttpStatus.OK)
    public IdentityResponseListDto getAll() {
//		System.out.println("Get all method called");
        return identityService.getAll();
    }

    @GetMapping("/identity-by-name/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public IdentityResponseListDto getByName(@PathVariable String name) {
        return identityService.getIdentityByName(name);
    }

//    @GetMapping(value = "/get-identitys/{size}/{page}")
//    public Page<Identity> getPageableIdentity (@PathVariable("size") int size, @PathVariable("page") int page) {
//        Pageable pageable = Page
//        return identityService.getPageableIdentity ();
//    }

}
