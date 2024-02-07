package com.softwaymedical.diagnostic.model;

import lombok.Getter;

@Getter
public class MedicalUnit implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4361249450202663135L;

	private MedicalUnitEnum key;
	
	private String name;

	public MedicalUnit(MedicalUnitEnum key, String name) {
		super();
		this.key = key;
		this.name = name;
	}


}
