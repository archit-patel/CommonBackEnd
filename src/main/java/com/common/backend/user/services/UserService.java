package com.common.backend.user.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.common.backend.user.errors.UserAlreadyExistException;
import com.common.backend.user.models.User;

public interface UserService {

	User registerNewUserAccount(User user) throws UserAlreadyExistException;

	Page<User> getUserList(Pageable pageable, String search);

}
