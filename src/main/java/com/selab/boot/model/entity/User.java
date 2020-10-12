package com.selab.boot.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.selab.boot.util.ValidationPattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "User")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Enabled")
	private boolean enabled;

	@Column(name = "AccountNonExpired")
	private boolean accountNonExpired;

	@Column(name = "AccountNonLocked")
	private boolean accountNonLocked;

	@Column(name = "CredentialsNonExpired")
	private boolean credentialsNonExpired;

	@ManyToOne
	@JoinColumn(name = "UserGroupId")
	private UserGroup userGroup;
	
	@Column(name = "RegisterDate")
	@CreationTimestamp
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private ZonedDateTime registerDate;

	@Transient
	@JsonIgnore
	private static final int MAX_NAME_LENGTH = 15;
	
	@Transient
	@JsonIgnore
	private static final int MIN_NAME_LENGTH = 5;
	
	@Transient
	@JsonIgnore
	private static final int MAX_PASSWORD_LENGTH = 21;
	
	@Transient
	@JsonIgnore
	private static final int MIN_PASSWORD_LENGTH = 5;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<? extends GrantedAuthority> result = new ArrayList<>();

		if(userGroup != null) result = userGroup.getUserGroupAuthList();

		return result;
	}

	public boolean isValid() {

		return isValidName(this.username) &&
				isValidPassword(this.password);
	}
	
	public boolean isValidName(String name) {

		int nameLength = name.length();

		return nameLength >= MIN_NAME_LENGTH &&
				nameLength <= MAX_NAME_LENGTH &&
				ValidationPattern.NON_WORLD_CHARACTER.matcher(name).matches();
	}
	
	public boolean isValidPassword(String password) {

		int passwordLength = password.length();

		return passwordLength >= MIN_PASSWORD_LENGTH &&
				passwordLength <= MAX_PASSWORD_LENGTH;
	}


}