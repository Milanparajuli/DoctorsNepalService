package com.milan.doctorsNepal.service;

import com.milan.doctorsNepal.Security.BCrypt;
import com.milan.doctorsNepal.dto.LoginRequestDto;
import com.milan.doctorsNepal.dto.LoginResponseDto;
import com.milan.doctorsNepal.dto.LogoutRequestDto;
import com.milan.doctorsNepal.dto.LogoutResponseDto;
import com.milan.doctorsNepal.dto.UserCreateDto;
import com.milan.doctorsNepal.dto.UserListResponseDto;
import com.milan.doctorsNepal.dto.UserResponseDto;
import com.milan.doctorsNepal.dto.UserUpdateDto;
import com.milan.doctorsNepal.entity.User;
import com.milan.doctorsNepal.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.milan.doctorsNepal.utils.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDto addUser(UserCreateDto request) {
        User user = new User();
        String originalPassword = request.getPassword();
        String encryptedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt());

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(encryptedPassword);
        user.setRoleType(RoleType.PATIENT);
//		user.setOtp(request.getOtp());
        User savedUser = userRepository.save(user);

        UserResponseDto response = new UserResponseDto();
        response.setEmail(savedUser.getEmail());
        response.setFullName(savedUser.getFullName());
        response.setUsername(savedUser.getUsername());
        response.setPassword(savedUser.getPassword());
//		response.setOtp(savedUser.getOtp());
        response.setId(savedUser.getUserId());
        return response;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getByNameAndEmail(String name, String email) {
        return userRepository.findByFullNameAndEmail(name, email);
    }

    public UserResponseDto getUserResponse(User saveUser) {
        UserResponseDto response = new UserResponseDto();
        response.setEmail(saveUser.getEmail());
        response.setUsername(saveUser.getUsername());
        response.setFullName(saveUser.getFullName());
        response.setPassword(saveUser.getPassword());
        response.setId(saveUser.getUserId());
        return response;

    }

    public com.milan.doctorsNepal.dto.UserListResponseDto getAll() {
        List<UserResponseDto> userResponseList = new ArrayList<>();
        List<User> users = (List<User>) userRepository.findAll();
        for (User user : users) {
            userResponseList.add(getUserResponse(user));
        }
        UserListResponseDto response = new UserListResponseDto();
        response.setUsers(userResponseList);
        response.setTotal(userResponseList.size());
        return response;
    }

    public User update(Long id, UserUpdateDto request) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();

            String originalPassword = user.getPassword();
            String encryptedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt());

            user.setEmail(request.getEmail());
            user.setFullName(request.getFullName());
            user.setUsername(request.getUsername());
            user.setPassword(encryptedPassword);
//			user.setOtp(request.getOtp());
            return userRepository.save(user);
        }
        return null;

    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name) {
        userRepository.deleteByFullName(name);
    }

    public UserResponseDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return getUserResponse(optionalUser.get());
        }
        return null;
    }

    public LoginResponseDto login(LoginRequestDto request) {

        LoginResponseDto response = new LoginResponseDto();
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new RuntimeException("User Dosent exist");
        }

        boolean checkpw = BCrypt.checkpw(request.getPassword(), user.getPassword());
        if (!checkpw) {
            throw new RuntimeException("Invalid Password. please type your correct password.");
        }
        user.setLoggedIn(true);
        userRepository.save(user);


        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());

        return response;

    }

    public LogoutResponseDto logout(LogoutRequestDto request) {
        User user = userRepository.findByUsername(request.getUsername());

        LogoutResponseDto response = new LogoutResponseDto();
        if (user == null) {
            response.setMessage("User Dose not exist. please register first.");
            return response;
        }
        user.setLoggedIn(false);
        userRepository.save(user);
        return response;
    }
}
