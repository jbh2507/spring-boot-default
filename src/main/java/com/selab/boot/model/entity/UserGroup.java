package com.selab.boot.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserGroup")
@Data
@ToString(exclude = {"userList"})
public class UserGroup {

	@Id
	@Column(name = "Id")
	private String name;

	@OneToMany(mappedBy = "userGroup")
	@JsonIgnore
	private List<User> userList;
	
	@OneToMany(mappedBy = "userGroup", fetch = FetchType.LAZY)
	private List<UserGroupAuth> userGroupAuthList;
}
