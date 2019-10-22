package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import DAOs.LicenseDAO;
import DAOs.LicenseDAOSQL;
import DTOs.LicenseDTO;
import DTOs.TitularDTO;
import domain.License;
import domain.LicenseType;
import domain.Titular;
import useful.Combination;
import useful.LicenseCostCalculator;
import validators.ClassAValidator;
import validators.ClassBValidator;
import validators.ClassCValidator;
import validators.ClassDValidator;
import validators.ClassEValidator;
import validators.ClassFValidator;
import validators.ClassGValidator;
import validators.CompositeValidator;
import validators.ObservationValidator;
import validators.Validator;

public class LicenseController {
	
	private static LicenseController _INSTANCE = new LicenseController();
	private LicenseCostCalculator licenseCostCalculator = LicenseCostCalculator.getInstance();
	private LicenseDAO licenseDAO = new LicenseDAOSQL();
	
	
	private LicenseController () { 
	}
	
	public static LicenseController getInstance() { 
		return _INSTANCE;
	}	
	
	public void registerLicense(TitularDTO titularDTO, LicenseDTO licenseDTO, Integer cameFrom) { 
		
		License license = new License();
		Titular titular;
		
		switch(cameFrom) {
		case 1: 
			titular = TitularController.getInstance().registerTitular(titularDTO);
			license.setTitular(titular);
			break;
		case 2:
			titular = TitularController.getInstance().findTitular(titularDTO.getId());
			license.setTitular(titular);
			break;
		}
		
		license.setLicenseType(licenseDTO.getLicenseType());
		license.setObservation(licenseDTO.getObservation());
		license.setEmisionDate(new Date());
		calculateValidity(license);
		
		Runnable r = () -> {
			licenseDAO.save(license);
		};
		Thread thread = new Thread(r);
		thread.start();
		
		
		
		Double licenseCost = calculateLicenseCost(license);
		
		// TODO mostrarPopUp Tuneado (licenseCost) 
		
	}
	
	public void loadLicenseTypeComboBox(JComboBox<LicenseType> comboBox, TitularDTO titularDTO, Integer cameFrom) {
		// TODO Se podria ejecutar en hilo secundario.. 
		switch(cameFrom) {
			case 1:
				comboBox.addItem(LicenseType.A);
				comboBox.addItem(LicenseType.B);
				comboBox.addItem(LicenseType.F);
				comboBox.addItem(LicenseType.G);
				break; 
			case 2: 
				Titular titular = TitularController.getInstance().findTitular(titularDTO.getId());
				List<Validator<LicenseType,Titular>> validators = new ArrayList<Validator<LicenseType,Titular>>();
				validators.add(new ClassAValidator());
				validators.add(new ClassBValidator());
				validators.add(new ClassCValidator());
				validators.add(new ClassDValidator());
				validators.add(new ClassEValidator());
				validators.add(new ClassFValidator());
				validators.add(new ClassGValidator());
				
				Validator<LicenseType,Titular> validator = new CompositeValidator<LicenseType,Titular>(validators);
		
				for(LicenseType licenseType: validator.validate(titular)) {
					comboBox.addItem(licenseType);
				}
				break;
				
		}
		
	}
	
	public List<String> validate(LicenseDTO licenseDTO) { 
		List<Validator<String,LicenseDTO>> validators = new ArrayList<Validator<String,LicenseDTO>>();
		
		validators.add(new ObservationValidator());
	
		Validator<String,LicenseDTO> validator = new CompositeValidator<String,LicenseDTO>(validators);
		
		return validator.validate(licenseDTO);
	}
	
	public Double calculateLicenseCost(License license) {
		
		Combination combination = new Combination(license.getLicenseType(), license.getValidity());
		
		return licenseCostCalculator.getLicenseCost(combination);
	}
	
	private void calculateValidity(License license){ 
		// TODO Implementar metodo calcular vigencia
	}

}
