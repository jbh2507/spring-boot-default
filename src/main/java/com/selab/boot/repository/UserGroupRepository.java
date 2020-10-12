package com.selab.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selab.boot.model.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long>{
	
	public UserGroup findByName(String name);
	
}
