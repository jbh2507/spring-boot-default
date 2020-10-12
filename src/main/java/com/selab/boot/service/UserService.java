package com.selab.boot.service;

import com.selab.boot.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.ValidationException;
import java.util.List;

public interface UserService extends UserDetailsService{
	
	public void addUser(String name, String password, String groupName) throws ValidationException;
	
	public User getUser(long id);
	
	public User getUserByName(String name);
	
	public boolean isExist(String name);
	
	public List<User> getAllUserList();
	
	public void update(User user) throws ValidationException;
	
	public void removeUser(User user);
	
	public void removeUserById(long id);
}
