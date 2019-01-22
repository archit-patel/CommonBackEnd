package com.common.backend.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.common.backend.role.models.Privilege;

@Repository
public interface PrivilegeRepository  extends JpaRepository<Privilege, Long> {

}
