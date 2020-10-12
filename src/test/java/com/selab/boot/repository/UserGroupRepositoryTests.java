package com.selab.boot.repository;

import com.selab.boot.model.entity.UserGroup;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserGroupRepositoryTests{

	@Setter(onMethod_ = @Autowired)
	private UserGroupRepository repo;
	
	@Test
	public void selectByName() {
		List<UserGroup> groupList = repo.findAll();
		String existName = groupList.get(0).getName();
		
		repo.findByName(existName);
	}
	
}
