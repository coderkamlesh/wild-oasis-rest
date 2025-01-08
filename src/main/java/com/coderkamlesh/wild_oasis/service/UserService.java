package com.coderkamlesh.wild_oasis.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coderkamlesh.wild_oasis.Repository.UserRepository;
import com.coderkamlesh.wild_oasis.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder encoder;
	private final AuthenticationManager authManager;
	private final JwtService jwtService;

	public User register(User user) {

		String password = user.getPassword() != null ? user.getPassword() : "test1234";
		user.setPassword(encoder.encode(password));

		User savedUser = userRepository.save(user);
		return savedUser;
	}

	public String verify(User user) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getEmail());
		}
		
		return "Fail";
	}

	public User getUserByEmail(String Email) {
		return userRepository.findByEmail(Email);
	}

	public User updateUser(Long id, User user) {
		
		User getUser=userRepository.findById(id).orElseThrow(()->new RuntimeException("user not exist"));
		getUser.setCountryFlag(user.getCountryFlag());
		getUser.setNationalId(user.getNationalId());
		getUser.setNationality(user.getNationality());
		
		return userRepository.save(getUser);
	}
}
