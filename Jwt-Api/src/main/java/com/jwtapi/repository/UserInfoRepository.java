package com.jwtapi.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jwtapi.model.UserInfo; 

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	
	@Query("SELECT u FROM UserInfo u JOIN FETCH u.roles WHERE u.name = :username")
	Optional<UserInfo> findUserByName(@Param("username") String username);
	
	Optional<UserInfo> findByName(@Param("username") String username);
	
	Optional<UserInfo> findById(@Param("id") UUID id);
}