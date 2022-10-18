package com.devbits.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devbits.dto.UserDto;
import com.devbits.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers(){
		return ResponseEntity.ok(userService.getUsers());
	}
}
