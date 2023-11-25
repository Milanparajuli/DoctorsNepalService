package com.milan.doctorsNepal.controller;

import com.milan.doctorsNepal.dto.LoginRequestDto;
import com.milan.doctorsNepal.dto.LoginResponseDto;
import com.milan.doctorsNepal.dto.LogoutRequestDto;
import com.milan.doctorsNepal.dto.LogoutResponseDto;
import com.milan.doctorsNepal.dto.UserCreateDto;
import com.milan.doctorsNepal.dto.UserListResponseDto;
import com.milan.doctorsNepal.dto.UserResponseDto;
import com.milan.doctorsNepal.dto.UserUpdateDto;
import com.milan.doctorsNepal.entity.User;
import com.milan.doctorsNepal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserResponseDto addUser(@RequestBody @Validated UserCreateDto request) {
		return userService.addUser(request);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public UserListResponseDto getAll() {
//		System.out.println("Get all method called");
		return userService.getAll();
	}

	@PutMapping("by-id/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public User update(@PathVariable Long id, @RequestBody UserUpdateDto request) {

		return userService.update(id, request);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		userService.delete(id);

	}

	@DeleteMapping("/by-name/{name}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteByName(@PathVariable String name) {
		userService.deleteByName(name);

	}

	@GetMapping("/by-id/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserResponseDto getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PostMapping("/login")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LoginResponseDto login(@RequestBody LoginRequestDto request) {
		return userService.login(request);

	}
	@PostMapping("logout")
	public LogoutResponseDto logout(@RequestBody LogoutRequestDto request) {
		return userService.logout(request);
	}

}
