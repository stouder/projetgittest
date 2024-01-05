package com.example.demo;

/**
 * Medical Unit Enumeration, useful in the context of a test (prove of concept).
 */
public enum MedicalUnitEnum {

	CARDIOLOGY(3, "Cardiologie"),

	TRAUMATOLOGY(5, "Traumatologie");

	/**
	 * Interpretation coefficient of the health index.
	 */
	private Integer coefficient;

	/**
	 * Display value (French)
	 */
	private String name;

	public Integer getCoefficient() {
		return coefficient;
	}

	private MedicalUnitEnum(Integer coefficient, String display) {
		this.coefficient = coefficient;
		this.name = display;
	}

	public String getName() {
		return name;
	}

}