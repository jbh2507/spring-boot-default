package com.selab.boot.repository;

import com.selab.boot.model.entity.User;
import com.selab.boot.model.entity.UserGroup;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@Rollback
public class UserRepositoryTests {
	
	@Setter(onMethod_ = @Autowired)
	private UserRepository userRepository;
	
	@Test
	public void isExist() {
		assertNotNull(userRepository);
	}
	
	@Test
	public void read() {
		userRepository.findAll().forEach(user -> log.info(user.toString()));
	}
	
	
	@Test
	public void select() {
		List<User> allUserList = userRepository.findAll();
		
		allUserList.forEach(element -> log.info(element.toString()));
	}
	
	@Test
	@Rollback
	public void update() {
//		List<User> allUserList = userRepository.findAll();
//
//		User noobUser = allUserList.get(allUserList.size()-1);
//
//		noobUser.setName("updated_"+LocalTime.now());
//
//		userRepository.save(noobUser);
	}
	
	@Test
	@Rollback
	public void delete() {
		List<User> allUserList = userRepository.findAll();
		
		long oldestUserId = allUserList.get(0).getId();
		
		userRepository.deleteById(oldestUserId);
		log.info("user("+oldestUserId+") is deleted");
		
		assertFalse(userRepository.existsById(oldestUserId));
	}
	
	@Test
	public void isExistName() {
//		String existName = userRepository.findAll().get(0).getName();
//
//		boolean isTrue = userRepository.existByName(existName);
//		boolean isFalse = userRepository.existByName("poo");
//
//		assertTrue(isTrue);
//		assertFalse(isFalse);
	}
	
	@Test
	@Transactional
	public void readWithGroup() {
		//List<User> allUserList = userRepository.findAll();
		
		//long oldestUserId = allUserList.get(0).getId();
		
		User user = userRepository.findByIdWithUserGroup(1L);
		UserGroup group = user.getUserGroup();
		
		log.info(group.getName());
	}
	
	@Test
	@Transactional
	public void readAllWithGroup() {
//		List<User> allUserList = userRepository.findAllWithGroup();
//
//		allUserList.forEach(user -> log.info(user.getName() + user.getUserGroup().getName()));
	}
	
}
