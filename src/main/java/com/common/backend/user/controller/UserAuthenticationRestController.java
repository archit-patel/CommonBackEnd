package com.common.backend.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.backend.user.models.User;
import com.common.backend.user.repository.UserRepository;
import com.common.backend.user.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserAuthenticationRestController {

	@Autowired
	UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/api/registration")
	public ResponseEntity<Map<String, Object>> userRegistration(@RequestBody User user) {
		Map<String, Object> responseMap = new HashMap<>();
		
		user = userService.registerNewUserAccount(user);
		
		responseMap.put("User",user);
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	

	@PostMapping("/api/login")
	public ResponseEntity<Map<String, Object>> userLogin(@RequestParam String username, @RequestParam String password)
			throws ServletException {
		Map<String, Object> responseMap = new HashMap<>();

		String jwtToken = "";
		
		if (username == null || password == null) {
			throw new ServletException("Please fill in username and password");
		}

		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new ServletException("User email not found.");
		}

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new ServletException("Invalid login. Please check your name and password.");
		}

		jwtToken = Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		
		responseMap.put("token", jwtToken);
		responseMap.put("user", user);
		
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
}
