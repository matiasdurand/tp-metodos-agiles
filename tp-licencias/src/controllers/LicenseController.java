package controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import dao.LicenseDAO;
import dao.LicenseDAOSQL;
import domain.License;
import domain.LicenseType;
import domain.Titular;
import domain.TypeId;
import dto.LicenseDTO;
import dto.TitularDTO;
import useful.Combination;
import useful.ExpiricyDateCalculator;
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
	private ExpiricyDateCalculator expiricyDateCalculator = ExpiricyDateCalculator.getInstance();
	private LicenseDAO licenseDAO = new LicenseDAOSQL();
	
	
	private LicenseController () { 
	}
	
	public static LicenseController getInstance() { 
		return _INSTANCE;
	}	
	
	public void registerLicense(TitularDTO titularDTO, LicenseDTO licenseDTO, Boolean cameFrom) { 
		
		License license = new License.Builder()
				.setLicenseType(licenseDTO.getLicenseType())
				.setObservation(licenseDTO.getObservation())
				.setExpiricyDate(licenseDTO.getExpiricyDate())
				.build();
		
		saveLicense(license);
		
		if(cameFrom) {
			TitularController.getInstance().registerTitular(titularDTO, license);
		}
		else { 
			Integer id = titularDTO.getId();
			TitularController.getInstance().addTitularsLicense(id, license);
		}
	}
	
	public void loadLicenseTypeComboBox(JComboBox<LicenseType> comboBox, TitularDTO titularDTO, Boolean cameFrom) {
		//cameFrom es true si esta dando de alta al titular
		if(cameFrom) {
			
			comboBox.addItem(LicenseType.A);
			comboBox.addItem(LicenseType.B);
			comboBox.addItem(LicenseType.F);
			comboBox.addItem(LicenseType.G);
			
		}
		else {
		
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
		
		}	
		
	}
	
	public List<String> validate(LicenseDTO licenseDTO) { 
		List<Validator<String,LicenseDTO>> validators = new ArrayList<Validator<String,LicenseDTO>>();
		
		validators.add(new ObservationValidator());
	
		Validator<String,LicenseDTO> validator = new CompositeValidator<String,LicenseDTO>(validators);
		
		return validator.validate(licenseDTO);
	}
	
	public void loadTypeIdComboBox(JComboBox<TypeId> comboBox) {
		for(TypeId typeId: TypeId.values()) {
			comboBox.addItem(typeId);
		}
	}
	
	public void loadBloodTypeComboBox(JComboBox<String> comboBox) {
		comboBox.addItem("AB-");
		comboBox.addItem("AB+");
		comboBox.addItem("A-");
		comboBox.addItem("A+");
		comboBox.addItem("B-");
		comboBox.addItem("B+");
		comboBox.addItem("O-");
		comboBox.addItem("O+");
	}
	
	public License findLicense(Integer id) {
		return licenseDAO.find(id);
	}
	
	public void saveLicense(License license) {
		licenseDAO.save(license);
	}
	
	public Double calculateLicenseCost(LicenseType licenseType, Date expiricyDate) {
		
		LocalDate expiricy = expiricyDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate actual = LocalDate.now();
		Integer validity = (int) ChronoUnit.YEARS.between(expiricy, actual);
		
		Combination combination = new Combination(licenseType, validity);
		
		return licenseCostCalculator.getLicenseCost(combination);
	}
	
	@SuppressWarnings("unused")
	private Date calculateExpiricyDate(TitularDTO titularDTO) { 
		
		Date birthday = titularDTO.getBirthdate();
		Long personalId = Long.parseLong(titularDTO.getPersonalId());
		TypeId typeId = titularDTO.getTypeId();
		
		return expiricyDateCalculator.calculateExpiricyDate(typeId, personalId, birthday);
		
	}
	
}
