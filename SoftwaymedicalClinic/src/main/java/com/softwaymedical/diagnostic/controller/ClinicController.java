package com.softwaymedical.diagnostic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.softwaymedical.diagnostic.service.DiagnosticCabinSensor;

@RestController
public class ClinicController {

	@Autowired
	private DiagnosticCabinSensor diagnosticCabinSensor;

	@GetMapping("/diagnostic/{id}")
	public String getMedicalUnit(@PathVariable("id") Integer id) throws Exception{
		return diagnosticCabinSensor.processCabinProbeSignalAndDisplay(id);
	}

}
