package com.cycloop.service;

import org.springframework.stereotype.Service;

import com.cycloop.Exception.UserException;
import com.cycloop.model.User;

@Service
public interface UserService {

	public User findUserById(Long userId) throws UserException;

	public User findUserProfileByJwt(String jwt) throws UserException;

}
