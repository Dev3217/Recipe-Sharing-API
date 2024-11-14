package com.dev.service;

import com.dev.model.User;

public interface UserService {
	public User findUserById(Long userId) throws Exception;
}
