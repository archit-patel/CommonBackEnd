package com.common.backend.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.backend.common.GeneralMethods;
import com.common.backend.role.models.Privilege;
import com.common.backend.role.models.Role;
import com.common.backend.role.repository.RoleRepository;
import com.common.backend.role.services.RoleService;

@RestController
public class RoleRestController {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	HttpServletRequest request;

	@PostMapping("/api/roles")
	public ResponseEntity<Map<String, Object>> getRoles(
			@RequestParam(value = "pagination[page]", required = false) int pageno,
			@RequestParam(value = "pagination[perpage]", required = false) int perpage,
			@RequestParam(value = "sort[sort]", required = false) String sort,
			@RequestParam(value = "sort[field]", required = false) String title,
			@RequestParam(value = "query[generalSearch]", required = false) String search) {
		Map<String, Object> responseMap = new HashMap<>();

		Page<Role> roleList = roleService.getRoleList(GeneralMethods.createPageRequest(pageno, perpage, sort, title),
				search);

		HashMap<String, String> meta = new HashMap<String, String>();
		meta.put("page", String.valueOf(pageno));
		meta.put("pages", String.valueOf(roleList.getTotalPages()));
		meta.put("perpage", String.valueOf(perpage));
		meta.put("total", String.valueOf(roleList.getTotalElements()));

		responseMap.put("meta", meta);
		responseMap.put("data", roleList.getContent());

		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@PostMapping("/api/roles/all")
	public ResponseEntity<Map<String, Object>> getRoles() {
		Map<String, Object> responseMap = new HashMap<>();

		List<Role> roleList = roleService.getRoleList();
		responseMap.put("data", roleList);

		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@PostMapping("/api/role/add")
	public ResponseEntity<Map<String, Object>> addRole(@RequestBody Role role) {
		Map<String, Object> responseMap = new HashMap<>();

		role = roleRepository.save(role);

		responseMap.put("Role", role);
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@PostMapping("api/role/update")
	public ResponseEntity<Map<String, Object>> updateRole(@RequestBody Role role) {
		Map<String, Object> responseMap = new HashMap<>();

		role = roleRepository.save(role);

		responseMap.put("Role", role);
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@PostMapping("api/role/delete")
	public ResponseEntity<Map<String, Object>> updateRole(@RequestParam(value = "id") Long id) {
		Map<String, Object> responseMap = new HashMap<>();

		roleRepository.deleteById(id);

		responseMap.put("Deleted", "Yes");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@PostMapping("/api/privileges")
	public ResponseEntity<Map<String, Object>> getPrivileges() {
		Map<String, Object> responseMap = new HashMap<>();

		List<Privilege> privilegeList = roleService.getPrivilegesList();
		responseMap.put("data", privilegeList);

		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@PostMapping("/api/privileges/role")
	public ResponseEntity<Map<String, Object>> getPrivilegesByRole(@RequestParam(value = "roleId") Long roleId) {
		Map<String, Object> responseMap = new HashMap<>();

		Optional<Role> role = roleRepository.findById(roleId);// (roleId);
		responseMap.put("data", role);

		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@PostMapping("/api/privileges/remove")
	public ResponseEntity<Map<String, Object>> removePrivilegeFromRole(
			@RequestParam(value = "privilegeId") Long privilegeId, @RequestParam(value = "roleId") Long roleId) {
		Map<String, Object> responseMap = new HashMap<>();

		boolean success = roleService.removePrivilegeFromRole(privilegeId,roleId);

		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@PostMapping("/api/privileges/add")
	public ResponseEntity<Map<String, Object>> addPrivilegeToRole(@RequestParam(value = "privilegeId") Long privilegeId,
			@RequestParam(value = "roleId") Long roleId) {
		Map<String, Object> responseMap = new HashMap<>();

		boolean success = roleService.addPrivilegeToRole(privilegeId,roleId);

		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

}
