package com.Se.OnlineLibrary.service;


import java.util.List;

import com.Se.OnlineLibrary.entity.User;
import com.Se.OnlineLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	public User save(User user)
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public List<User> getAlluser() {
		return userRepository.findAll();
	}

	public User getuserById(long id)
	{
		return userRepository.findById(id).get();
	}
	public void deleteById(long id)
	{
		userRepository.deleteById(id);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
