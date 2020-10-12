package com.selab.boot.service.impl;

import com.selab.boot.model.entity.User;
import com.selab.boot.repository.UserGroupRepository;
import com.selab.boot.repository.UserRepository;
import com.selab.boot.service.UserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

	@Setter(onMethod_ = @Autowired)
	private UserRepository userRepository;
	
	@Setter(onMethod_ = @Autowired)
	private UserGroupRepository groupRepository;

	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByNameWithUserGroup(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username+" is not found");
		}
		
		return user;
	}

	@Override
	public void addUser(String name, String password, String groupName) throws ValidationException {

	}

	@Override
	@Transactional(readOnly = true)
	public User getUser(long id) {

		return  userRepository.findById(id).orElseThrow(null);
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByName(String name) {

		return userRepository.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUserList() {

		return userRepository.findAllWithGroup();
	}

	@Override
	public void update(User user) throws ValidationException {

		if(!user.isValidPassword(user.getPassword())) {
			throw new ValidationException("User's password is not valid (user.password: "+user.getPassword());
		}

		if(user.getId() == null) {
			throw new IllegalArgumentException("User id is null\tuser: "+user);
		}

		userRepository.save(user);
	}

	@Override
	public void removeUser(User user) {
		userRepository.delete(user);
		
	}

	@Override
	public void removeUserById(long id) {
		userRepository.deleteById(id);

	}

	@Override
	public boolean isExist(String name) {

		return userRepository.existByName(name);
	}

}
