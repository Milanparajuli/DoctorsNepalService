package com.milan.doctorsNepal.controller;

import com.milan.doctorsNepal.entity.EmailMessage;
import com.milan.doctorsNepal.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {
    @Autowired
    MailService mailService;

    @RequestMapping("/email-send")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<String> sendEmail(@RequestBody EmailMessage emailMessage){
        this.mailService.mailSend(emailMessage , "milanparajuli2058@gmial.com", "Milan Parajuli", "abcd","milan2528");
//        this.mailService.mailSend(EmailConstant.Template.SEND, emailMessage);
        return ResponseEntity.ok("Success");
    }

}
