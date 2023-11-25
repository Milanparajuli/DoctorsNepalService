package com.milan.doctorsNepal.service;

import com.milan.doctorsNepal.entity.EmailMessage;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSenderImpl javaMailSender;

//    private final String toEmail = "milanparajuli2058@gmail.com";

    public void mailSend(EmailMessage emailMesage, String toEmail, String name, String password, String userName){
        emailMesage.setEmail(toEmail);
        emailMesage.setName(name);
        emailMesage.setSubject("Username or password");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailMesage.getEmail());
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("User name or password,");
        simpleMailMessage.setText("Namaste,\nDr. "+name + " ji"+" Your user name is: " + userName + " and password is: " + password);
        this.javaMailSender.send(simpleMailMessage);
    }

}
