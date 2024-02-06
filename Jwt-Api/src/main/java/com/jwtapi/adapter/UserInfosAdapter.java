package com.jwtapi.adapter;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jwtapi.common.UserInfosMapper;
import com.jwtapi.dto.UserInfosDTO;
import com.jwtapi.model.UserInfo;

@Component
public class UserInfosAdapter {

	@Autowired
	UserInfosMapper userInfosMapper;

	public UserInfosDTO transform(UserInfo userInfo) {
		return userInfosMapper.mapToUserDto(userInfo);
	}
}
