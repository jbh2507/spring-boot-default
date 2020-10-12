package com.selab.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.selab.boot.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByName(String name);
	
	@Query("SELECT CASE WHEN COUNT(u)> 0 THEN true ELSE false END FROM User u WHERE u.name = ?1")
	public boolean existByName(String name);
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userGroup WHERE u.id = :id")
	public User findByIdWithUserGroup(Long id);
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userGroup WHERE u.name = :name")
	public User findByNameWithUserGroup(String name);
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userGroup")
	public List<User> findAllWithGroup();
	
}
