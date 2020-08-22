package com.cleardebts.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cleardebts.model.User;
import com.cleardebts.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = userRepository.getUserByContactAndEmail(userName, userName);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username");
		}
		return new org.springframework.security.core.userdetails.User(user.getContactNumber(),
				encoder.encode(user.getPassword()), new ArrayList<>());
	}

}
