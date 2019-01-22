package com.common.backend.role.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.common.backend.role.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Page<Role> findAll(Pageable pageable);

	@Query("select r from Role r where LOWER(r.name) like LOWER(CONCAT('%',:search,'%'))")
	Page<Role> findAll(Pageable pageable,@Param("search") String search);
}
