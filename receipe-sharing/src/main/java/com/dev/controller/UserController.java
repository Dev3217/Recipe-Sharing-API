package com.dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.dev.model.User;
import com.dev.repository.UserRepository;
import com.dev.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) throws Exception {
		User isExist = userRepository.findByEmail(user.getEmail());
		if(isExist!=null) {
			throw new Exception("User already exist with the Email: " + user.getEmail());
		}
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable Long userId) throws Exception {
		userRepository.deleteById(userId);
		return "User Deleted Successfully";
	}
	
	@GetMapping("/users")
	public List<User> getAllUser() throws Exception {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public Optional<User> getUserById(@PathVariable Long userId) throws Exception {
		return userRepository.findById(userId);
	}
	
	
}
