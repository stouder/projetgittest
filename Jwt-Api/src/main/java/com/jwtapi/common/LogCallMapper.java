package com.jwtapi.common;

import org.mapstruct.Mapper;

import com.jwtapi.dto.LogCallDTO;
import com.jwtapi.model.LogCall;

@Mapper(componentModel = "spring")
public interface LogCallMapper {

	LogCallDTO mapToLogCallDTO(LogCall logCall);

	LogCall mapToLogCall(LogCallDTO logCallDTO);
}
