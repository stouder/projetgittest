package com.jwtapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtapi.adapter.LogCallAdapter;
import com.jwtapi.dto.LogCallDTO;
import com.jwtapi.model.LogCall;
import com.jwtapi.repository.LogCallRepository;

@Service
public class LogCallService {

	@Autowired
	LogCallRepository logCallRepository;

	@Autowired
	LogCallAdapter logCallAdapter;

	public void save(LogCallDTO logCallDTO) {
		LogCall logCall = logCallAdapter.transform(logCallDTO);
		logCallRepository.save(logCall);
	}
}
