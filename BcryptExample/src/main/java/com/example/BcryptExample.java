package com.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BcryptExample {

	public static void main(String[] args) {


		PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(BCryptVersion.$2A, 12);
		
		String password = "123";
		
		System.out.println(bcryptEncoder.encode(password));

	}

}
