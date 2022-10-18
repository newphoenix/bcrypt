package com.devbits.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devbits.dto.UserDto;
import com.devbits.repository.AuthorityRepository;
import com.devbits.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	private AuthorityRepository authorityRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails userDetails = userRepository.findByUsername(username)
				.map(user -> new org.springframework.security.core.userdetails.User(//
						user.getUsername(), //
						user.getPassword(), //
						user.isEnabled(),
						true,
						true,
						true,
						authorityRepository.getAuthoritiesByUserName(user.getUsername()).stream()
								.map(a -> new SimpleGrantedAuthority(a)).toList()

				)).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		
		return userDetails;
	}

	public List<UserDto> getUsers() {
		return userRepository.findAll().stream().map(user -> UserDto.builder().email(user.getEmail())
				.username(user.getUsername()).enabled(user.isEnabled()).build()).toList();

	}

}
