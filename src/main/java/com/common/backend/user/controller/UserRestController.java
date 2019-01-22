package com.common.backend.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.backend.common.GeneralMethods;
import com.common.backend.user.models.User;
import com.common.backend.user.repository.UserRepository;
import com.common.backend.user.services.UserService;

@RestController
public class UserRestController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/users")
	public ResponseEntity<Map<String, Object>> getUsers(
			@RequestParam(value = "pagination[page]", required = false) int pageno,
			@RequestParam(value = "pagination[perpage]", required = false) int perpage,
			@RequestParam(value = "sort[sort]", required = false) String sort,
			@RequestParam(value = "sort[field]", required = false) String title,
			@RequestParam(value = "query[generalSearch]", required = false) String search) {
		Map<String, Object> responseMap = new HashMap<>();

		Page<User> userList = userService.getUserList(GeneralMethods.createPageRequest(pageno, perpage, sort, title), search);
		
		HashMap<String,String> meta=new HashMap<String,String>();
		meta.put("page",String.valueOf(pageno));
		meta.put("pages",String.valueOf(userList.getTotalPages()));
		meta.put("perpage",String.valueOf(perpage));
		meta.put("total",String.valueOf(userList.getTotalElements()));
		
		responseMap.put("meta", meta);
		responseMap.put("data", userList.getContent());
		
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@PostMapping("/api/user/delete")
	public ResponseEntity<Map<String, Object>> deleteUser(@RequestParam(value = "id") Long id) {
		Map<String, Object> responseMap = new HashMap<>();
		
		userRepository.deleteById(id);
		
		responseMap.put("deleted", "Yes");
		
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@PostMapping("/api/user/update")
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody User user) {
		Map<String, Object> responseMap = new HashMap<>();
		
		user.setLastUpdationDate(new Date());
		user.setUpdatedBy("ADMIN");
		
		userRepository.save(user);
		
		responseMap.put("deleted", "Yes");
		
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@PostMapping("/api/user/id")
	public ResponseEntity<Map<String, Object>> getUserById(@RequestParam(value = "id") Long id) {
		Map<String, Object> responseMap = new HashMap<>();
		
		Optional<User> user = userRepository.findById(id);
		
		responseMap.put("user", user);
		
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@GetMapping("/xx")
	public String testUser2() {
		return "Test In Filter 2";
	}
}
