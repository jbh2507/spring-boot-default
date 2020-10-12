package com.selab.boot.model.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserVO {

    private Long id;

    private String password;

    private String userGroupName;

}
