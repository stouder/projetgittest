package com.softwaymedical.diagnostic.model;

import lombok.Getter;

@Getter
public enum MedicalUnitEnum {

	CARDIOLOGY(3, "Cardiologie"),

	TRAUMATOLOGY(5, "Traumatologie");

	private MedicalUnitEnum(Integer coefficient,String name) {
		this.coefficient = coefficient;
		this.name = name ;
	}
	
	private Integer coefficient;
	
	private String name;

}
