package com.common.backend.role.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.common.backend.role.models.Privilege;
import com.common.backend.role.models.Role;

public interface RoleService {

	Page<Role> getRoleList(Pageable pageable,String search);

	List<Role> getRoleList();

	List<Privilege> getPrivilegesList();

	boolean removePrivilegeFromRole(Long privilegeId, Long roleId);

	boolean addPrivilegeToRole(Long privilegeId, Long roleId);

	List<Privilege> getPrivilegesListByRoleId(Long roleId);
	
}
