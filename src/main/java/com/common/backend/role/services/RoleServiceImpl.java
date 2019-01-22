package com.common.backend.role.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.common.backend.role.models.Privilege;
import com.common.backend.role.models.Role;
import com.common.backend.role.repository.PrivilegeRepository;
import com.common.backend.role.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PrivilegeRepository privilegeRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Role> getRoleList(Pageable pageable, String search) {

		if (search == null || search.equals(""))
			return roleRepository.findAll(pageable);
		else
			return roleRepository.findAll(pageable, search);
	}

	@Override
	public List<Role> getRoleList() {
		return roleRepository.findAll();
	}

	@Override
	public List<Privilege> getPrivilegesList() {
		return privilegeRepository.findAll();
	}

	@Override
	public boolean removePrivilegeFromRole(Long privilegeId, Long roleId) {
		try {
			
			entityManager.createNativeQuery("delete from ROLES_PRIVILEGES where role_id="+roleId+" and privilege_id="+privilegeId+"").executeUpdate();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addPrivilegeToRole(Long privilegeId, Long roleId) {
		try {
			
			entityManager.createNativeQuery("insert into ROLES_PRIVILEGES (role_id,privilege_id)  VALUES  ("+roleId+","+privilegeId+")").executeUpdate();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Privilege> getPrivilegesListByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
}
