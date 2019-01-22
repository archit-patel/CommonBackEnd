package com.common.backend.user.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.common.backend.common.AppConstants;
import com.common.backend.user.errors.UserAlreadyExistException;
import com.common.backend.user.models.User;
import com.common.backend.user.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerNewUserAccount(User user) throws UserAlreadyExistException {
		if (emailExist(user.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email adress: " + user.getEmail());
		}
		User newUser = new User();
		newUser = user;
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setStatus(AppConstants.PENDING_STATUS);
		user.setCreatedBy("ADMIN");
		user.setCreationDate(new Date());
		return userRepository.save(user);
	}

	private boolean emailExist(final String email) {
		return userRepository.findByEmail(email) != null;

	}

	@Override
	public Page<User> getUserList(Pageable pageable, String search) {
		if(search == null || search.equals(""))
			return userRepository.findAll(pageable);
		else 
			return userRepository.findAll(pageable,search);
	}
}
