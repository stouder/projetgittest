package com.jwtapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtapi.model.LogCall;

@Repository
public interface LogCallRepository extends JpaRepository<LogCall, Integer> {

}