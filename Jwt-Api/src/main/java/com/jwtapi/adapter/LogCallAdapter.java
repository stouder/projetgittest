package com.jwtapi.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jwtapi.common.LogCallMapper;
import com.jwtapi.dto.LogCallDTO;
import com.jwtapi.model.LogCall;

@Component
public class LogCallAdapter {

	@Autowired
	LogCallMapper logCallMapper;

	public LogCall transform(LogCallDTO logCallDTO) {
		return logCallMapper.mapToLogCall(logCallDTO);
	}
}
