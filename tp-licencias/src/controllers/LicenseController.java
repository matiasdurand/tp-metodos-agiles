package controllers;

import javax.swing.JComboBox;

import DTOs.TitularDTO;

import domain.LicenseType;

public class LicenseController {
	
	private static LicenseController _INSTANCE = new LicenseController();
	
	
	private LicenseController () { 
	}
	
	public static LicenseController getInstance() { 
		return _INSTANCE;
	}	
	
	public void registerLicense(LicenseDTO licenseDTO) { 
		
		
		
	}
	
	public void loadLicenseTypeComboBox(JComboBox<LicenseType> comboBox, TitularDTO titularDTO, Integer cameFrom) {
		//validate
		switch(cameFrom) {
			case 1:
				comboBox.addItem(LicenseType.A);
				comboBox.addItem(LicenseType.B);
				comboBox.addItem(LicenseType.F);
				comboBox.addItem(LicenseType.G);
				break; 
			case 2: 
				
			
			
		}
	}

}
