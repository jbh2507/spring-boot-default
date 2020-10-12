package com.selab.boot.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "UserGroupAuth")
@Data
@ToString(exclude = {"userGroup"})
public class UserGroupAuth implements GrantedAuthority {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "UserGroupId")
	@JsonIgnore
	private UserGroup userGroup;
	
	@Column(name = "Auth")
	private Auth auth;

	@Override
	public String getAuthority() {
		return "ROLE_"+this.auth.name();
	}

	enum Auth {
		ADMIN, MEMBER
	}
}
