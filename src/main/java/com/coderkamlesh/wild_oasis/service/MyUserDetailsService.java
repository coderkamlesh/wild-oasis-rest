package com.coderkamlesh.wild_oasis.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coderkamlesh.wild_oasis.Repository.UserRepository;
import com.coderkamlesh.wild_oasis.entity.User;
import com.coderkamlesh.wild_oasis.entity.UserPrinciple;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(username);
		if(user==null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("User Not Found");
		}
		return new UserPrinciple(user);
	}

	
}
