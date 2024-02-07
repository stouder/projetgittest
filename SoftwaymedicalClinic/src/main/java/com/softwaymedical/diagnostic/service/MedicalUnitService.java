package com.softwaymedical.diagnostic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.softwaymedical.diagnostic.model.MedicalUnit;
import com.softwaymedical.diagnostic.model.MedicalUnitEnum;

@Component
public class MedicalUnitService {

	public List<MedicalUnit> getAvailableMedicalUnit() {
		List<MedicalUnit> units = Stream.of(MedicalUnitEnum.values()).map(mu -> new MedicalUnit(mu, mu.getName()))
				.collect(Collectors.toCollection(ArrayList::new));
		return units;
		
	}
}
