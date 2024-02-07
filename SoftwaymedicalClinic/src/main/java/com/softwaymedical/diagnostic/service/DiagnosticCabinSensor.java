package com.softwaymedical.diagnostic.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.softwaymedical.diagnostic.model.MedicalUnit;

@Component
public class DiagnosticCabinSensor {

	private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticCabinSensor.class);
	
	@Autowired
	private MedicalUnitService medicalUnitService;
	
	public String processCabinProbeSignalAndDisplay(Integer healthIndex) throws Exception{
		
		if(healthIndex == null || healthIndex.intValue() == 0) {
			LOGGER.error("L'index de santé n'est pas renseigné");
			throw new Exception();
		}
		
		StringBuilder screenDisplay = new StringBuilder();
		List<MedicalUnit> medicalUnitList = this.processCabinProbeSignal(healthIndex);
		
		//List<MedicalUnit> medicalUnitList = medicalUnits.stream().sorted((o1, o2)->o1.getName().
        //        compareTo(o2.getName())).
         //       collect(Collectors.toList());
		

		if(medicalUnitList != null  && !medicalUnitList.isEmpty()) {
			for(MedicalUnit medicalUnit:medicalUnitList) {
				if(screenDisplay.length()>0) {
					screenDisplay.append(", ");
				}
				
				String medicalUnitName = medicalUnit.getName();
				screenDisplay.append(StringUtils.capitalize(medicalUnitName));
			}
		}
		
		return screenDisplay.toString();
	}
	
	public MedicalUnit processCabinProbeSignalAndDisplayApi(Integer healthIndex) throws Exception{
		
		if(healthIndex == null || healthIndex.intValue() == 0) {
			LOGGER.error("L'index de santé n'est pas renseigné");
			throw new Exception();
		}
		
		StringBuilder screenDisplay = new StringBuilder();
		List<MedicalUnit> medicalUnitList = this.processCabinProbeSignal(healthIndex);
		
		//List<MedicalUnit> medicalUnitList = medicalUnits.stream().sorted((o1, o2)->o1.getName().
        //        compareTo(o2.getName())).
         //       collect(Collectors.toList());
		
		if(medicalUnitList != null  && !medicalUnitList.isEmpty()) {
			for(MedicalUnit medicalUnit:medicalUnitList) {
				if(screenDisplay.length()>0) {
					screenDisplay.append(", ");
				}
				else {
					throw new Exception();
				}
				String medicalUnitName = medicalUnit.getName();
				screenDisplay.append(StringUtils.capitalize(medicalUnitName));
			}
		}
		
		return null;
	}
	
	public List<MedicalUnit> processCabinProbeSignal(Integer healthIndex) throws Exception{
		
		if(healthIndex == null || healthIndex.intValue() == 0) {
			LOGGER.error("L'index de santé n'est pas renseigné");
			return new ArrayList<MedicalUnit>();
		}
		
		List<MedicalUnit> concernedUnits = new ArrayList<MedicalUnit>();
		
		List<MedicalUnit> units = medicalUnitService.getAvailableMedicalUnit();
		if(units == null || units.isEmpty()) {
			LOGGER.error("Pas d'unité medical renseigné");
		}else {
			for(MedicalUnit medicalUnit:units) {
				if(healthIndex % medicalUnit.getKey().getCoefficient() == 0) {
					concernedUnits.add(medicalUnit);
				}
			}
		}
		
		return concernedUnits;
	}
}
