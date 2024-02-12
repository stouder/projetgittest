package com.jwtapi.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LogCallDTO {

	private String url;
	private String parameter;
	private LocalDate calledDate;
}
