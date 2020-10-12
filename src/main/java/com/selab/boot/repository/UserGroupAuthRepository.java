package com.selab.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selab.boot.model.entity.UserGroupAuth;

public interface UserGroupAuthRepository extends JpaRepository<UserGroupAuth, Long>{
	
}
