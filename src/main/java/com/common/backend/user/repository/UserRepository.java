package com.common.backend.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.common.backend.user.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	Page<User> findAll(Pageable pageable);

	@Query("select u from User u where LOWER(u.firstName) like LOWER(CONCAT('%',:search,'%')) "
			+ "OR LOWER(u.lastName) like LOWER(CONCAT('%',:search,'%')) "
			+ "OR LOWER(u.email) like LOWER(CONCAT('%',:search,'%')) "
			+ "OR LOWER(u.role.name) like LOWER(CONCAT('%',:search,'%'))")
	Page<User> findAll(Pageable pageable,@Param("search") String search);
}
