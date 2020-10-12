package com.selab.boot.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class UserServiceTests{
	
	@Setter(onMethod_ = @Autowired)
	private UserService userService;
	
	@Test
	public void isExist() {
		assertNotNull(userService);
	}
	
	@Test
	public void add() {
//		User firstUser = userService.getAllUserList().get(0);
//
//		log.info(firstUser.toString());
//
//		User user = new User();
//		UserGroup group = firstUser.getUserGroup();
//
//		user.setName("tester_"+LocalTime.now());
//		user.setPassword("tester");
//		user.setUserGroup(group);
//
//		userService.addUser(user);
	}

}
