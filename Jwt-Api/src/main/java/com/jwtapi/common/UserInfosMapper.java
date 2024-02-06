package com.jwtapi.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jwtapi.dto.UserInfosDTO;
import com.jwtapi.model.UserInfo;
import com.jwtapi.model.UserRole;

@Mapper(componentModel = "spring")
public interface UserInfosMapper {

//	UserInfosMapper MAPPER = Mappers.getMapper(UserInfosMapper.class);

	@Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
	UserInfosDTO mapToUserDto(UserInfo user);

	@Mapping(target = "roles", expression = "java(mapRolesDto(userDto.getRoles()))")
	UserInfo mapToUser(UserInfosDTO userDto);

	default String mapRoles(Set<UserRole> roles) {
		return roles.stream().map(UserRole::name).collect(Collectors.joining(", "));
		// .collect(Collectors.toList());
	}

	default Set<UserRole> mapRolesDto(String roles) {
		return Arrays.stream(roles.split(",")).map(UserRole::valueOf).collect(Collectors.toSet());

		/*
		 * return roles.stream() .map(UserRole::valueOf) .collect(Collectors.toSet());
		 */
	}

}
