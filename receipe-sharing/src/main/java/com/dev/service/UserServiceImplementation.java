package com.dev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.dev.config.JwtProvider;
import com.dev.model.User;
import com.dev.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	@Autowired
	private UserRepository userRepository;


	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> opt = userRepository.findById(userId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("User does not exist with the provided id: " + userId);
	}
}
