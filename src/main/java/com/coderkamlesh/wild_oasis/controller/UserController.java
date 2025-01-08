package com.coderkamlesh.wild_oasis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coderkamlesh.wild_oasis.entity.User;
import com.coderkamlesh.wild_oasis.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/public")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		
		return userService.register(user);
	}
	
	@PostMapping("login")
	public String login(@RequestBody  User user) {

		return userService.verify(user);
	}
	
	@GetMapping("/user")
	public User findSingleUseByEmail(@RequestParam("email") String email ) {
		return userService.getUserByEmail(email);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody User user){
		User updatedUser=userService.updateUser(id,user);
		
		return ResponseEntity.ok("User Updated successfully"+updatedUser);
	}
}
