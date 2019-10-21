package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import DAOs.LicenseDAO;
import DAOs.LicenseDAOSQL;
import DTOs.LicenseDTO;
import DTOs.TitularDTO;
import domain.License;
import domain.LicenseType;
import domain.Titular;
import useful.LicenseCostCalculator;
import validators.ClassAValidator;
import validators.ClassBValidator;
import validators.ClassCValidator;
import validators.ClassDValidator;
import validators.ClassEValidator;
import validators.ClassFValidator;
import validators.ClassGValidator;
import validators.CompositeValidator;
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
	
	public void registerLicense(LicenseDTO licenseDTO) { 
		
	}
	
	public void loadLicenseTypeComboBox(JComboBox<LicenseType> comboBox, TitularDTO titularDTO, Integer cameFrom) {

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
	
	public Double calculateLicenseCost(License license) {
		String[] key = new String[2];
		
		key[0] = license.getLicenseType().toString();
		key[1] = license.getValidity().toString();
		
		return licenseCostCalculator.getLicenseCost(key);
	}

}
