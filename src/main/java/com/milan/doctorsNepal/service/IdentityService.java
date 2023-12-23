package com.milan.doctorsNepal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milan.doctorsNepal.Security.BCrypt;
import com.milan.doctorsNepal.dto.IdentityResponseDto;
import com.milan.doctorsNepal.dto.IdentityResponseListDto;
import com.milan.doctorsNepal.entity.EmailMessage;
import com.milan.doctorsNepal.entity.Identity;
import com.milan.doctorsNepal.entity.User;
import com.milan.doctorsNepal.repository.IdentityRepository;
import com.milan.doctorsNepal.utils.PasswordGenerator;
import com.milan.doctorsNepal.utils.RoleType;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class IdentityService {

    private IdentityRepository identityRepository;

    ResourceLoader resourceLoader;

    private final MailService mailService;


    PasswordGenerator passwordGenerator;

    UserService userService;

//    private JavaMailSenderImpl


//    public IdentityService(IdentityRepository identityRepository, PasswordGenerator passwordGenerator,
//                           UserService userService) {
//        this.identityRepository = identityRepository;
//        this.userService = userService;
//        this.passwordGenerator = passwordGenerator;
//
//    }


    public List<Identity> saveIdentityListToDb(List<Identity> identityList) throws IOException {
//        Resource resource = resourceLoader.getResource("src/main/resources/static/identitysList.json");
        FileInputStream f = new FileInputStream(new File("D:\\BCA\\Six Semester\\project-ii\\Doctors Nepal\\Service\\doctorsNepal\\doctorsList.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        List<Identity> savedIdentitys = new ArrayList<>();
        try (InputStream inputStream = f) {
            List<Identity> identity = List.of(objectMapper.readValue(inputStream, Identity[].class));
            identity.stream().forEach(o -> {
                Identity existingIdentity = identityRepository.findByNameAndEmail(o.getName(), o.getEmail());
                if (!Objects.isNull(existingIdentity)) o.setId(existingIdentity.getId());
            });
            savedIdentitys = identityRepository.saveAll(identity);
            savedIdentitys.stream().forEach(o -> {
//                MailService mailService = new MailService();
                EmailMessage emailMessage = new EmailMessage();
                String originalPassword = passwordGenerator.generateRandomPassword();
                String encryptedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt());
                String[] username = o.getEmail().split("@");
                //                mailService.mailSend(emailMessage, o.getEmail(), o.getName(), originalPassword);
                new Thread(() -> {
                    this.mailService.mailSend(emailMessage, o.getEmail(), o.getName(), originalPassword, username[0]);
                }).start();
//                javamailSender.send(f.email)
                emailMessage.setName(o.getName());
                emailMessage.setEmail(o.getEmail());
                emailMessage.setSubject("Username and Password");
                User user = new User();
                user.setPassword(encryptedPassword);
                user.setUsername(username[0]);
                user.setFullName(o.getName());
                user.setEmail(o.getEmail());
                user.setRoleType(RoleType.DOCTOR);if (!Objects.isNull(userService.getByNameAndEmail(o.getName(), o.getEmail()))) {
                    User existingUser = userService.getByNameAndEmail(o.getName(), o.getEmail());
                    user.setUserId(existingUser.getUserId());
                }
                User savedUser = userService.saveUser(user);
                o.setUser(savedUser);
                identityRepository.save(o);
            });
        } catch (Exception e) {
            System.err.print(e);
        }
        return savedIdentitys;
    }


    private IdentityResponseDto getIdentityResponse(Identity saveIdentity) {
        IdentityResponseDto response = new IdentityResponseDto();
        response.setId(saveIdentity.getId());
        response.setName(saveIdentity.getName());
        response.setAddress(saveIdentity.getAddress());
        response.setDescription(saveIdentity.getDescription());
        response.setProfile(saveIdentity.getProfile());
        response.setSpeacialon(saveIdentity.getSpecialOn());
        response.setPhone(saveIdentity.getPhone());
        response.setDegree(saveIdentity.getDegree());
        response.setNmcNo(saveIdentity.getNmcNo());
        response.setLatitude(saveIdentity.getLatitude());
        response.setLongitude(saveIdentity.getLongitude());
        return response;

    }

    public IdentityResponseListDto getAll() {
        List<IdentityResponseDto> identityResponseList = new ArrayList<>();
        List<Identity> identitys = (List<Identity>) identityRepository.findAll();
        for (Identity identity : identitys) {
            identityResponseList.add(getIdentityResponse(identity));
        }
        IdentityResponseListDto response = new IdentityResponseListDto();
        response.setIdentity(identityResponseList);
        response.setTotalIdentity(identityResponseList.size());
        return response;
    }

    public IdentityResponseListDto getIdentityByName(String name) {
        List<IdentityResponseDto> identityResponseList = new ArrayList<>();
        List<Identity> identitys = (List<Identity>) identityRepository.findByNameContaining(name);

        for (Identity identity : identitys) {
            identityResponseList.add(getIdentityResponse(identity));
        }

        System.out.println("Identitys {}" + identitys);
        IdentityResponseListDto response = new IdentityResponseListDto();
        response.setIdentity(identityResponseList);
        response.setTotalIdentity(identityResponseList.size());

        return response;

    }
    public IdentityResponseListDto getIdentityBySpecialOn(String specialOn) {
        List<IdentityResponseDto> identityResponseList = new ArrayList<>();
        List<Identity> identitys = (List<Identity>) identityRepository.findBySpecialOnContaining(specialOn);

        for (Identity identity : identitys) {
            identityResponseList.add(getIdentityResponse(identity));
        }

        System.out.println("Identitys {}" + identitys);
        IdentityResponseListDto response = new IdentityResponseListDto();
        response.setIdentity(identityResponseList);
        response.setTotalIdentity(identityResponseList.size());

        return response;

    }

    public IdentityResponseListDto getIdentityByAddress(String address) {
        List<IdentityResponseDto> identityResponseList = new ArrayList<>();
        List<Identity> identitys = (List<Identity>) identityRepository.findByAddressContaining(address);

        for (Identity identity : identitys) {
            identityResponseList.add(getIdentityResponse(identity));
        }

        System.out.println("Identitys {}" + identitys);
        IdentityResponseListDto response = new IdentityResponseListDto();
        response.setIdentity(identityResponseList);
        response.setTotalIdentity(identityResponseList.size());

        return response;
    }

    public IdentityResponseListDto getIdentityByNameOrAddress(String name, String address) {
        List<IdentityResponseDto> identityResponseList = new ArrayList<>();
        List<Identity> identitys = (List<Identity>) identityRepository.getIdentityByNameOrAddressContaining(name, address);

        for (Identity identity : identitys) {
            identityResponseList.add(getIdentityResponse(identity));
        }

        System.out.println("Identitys {}" + identitys);
        IdentityResponseListDto response = new IdentityResponseListDto();
        response.setIdentity(identityResponseList);
        response.setTotalIdentity(identityResponseList.size());

        return response;
    }

    //    public Page<Identity> getPageableIdentity() {
//        return  identityRepository.findAll()
//    }
}

