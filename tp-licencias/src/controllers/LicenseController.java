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
import utils.Combination;
import utils.ExpiryDateCalculator;
import utils.LicenseCostCalculator;
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

/**
 * Esta es la clase controladora de Licencia. Singleton.
 * Implementa los metodos que seran llamados desde la GUI.
 * Se comunica con la capa DAO para recuperar, persistir o actualizar datos. 
 * @author Juan Suppicich & Matias Durand
 * 
 */
public class LicenseController {
	
	private static LicenseController _INSTANCE = new LicenseController();
	private LicenseCostCalculator licenseCostCalculator = LicenseCostCalculator.getInstance();
	private ExpiryDateCalculator expiryDateCalculator = ExpiryDateCalculator.getInstance();
	private LicenseDAO licenseDAO = new LicenseDAOSQL();
	
	
	private LicenseController () { 
	}
	
	public static LicenseController getInstance() { 
		return _INSTANCE;
	}	
	
	/**
	 * Este metodo instancia una nueva licencia y delega la perduracion en base de datos.
	 * @param titularDTO datos del titular, provenientes de GUI.
	 * @param licenseDTO datos de la licencia, proveniente de GUI.
	 * @param registerTitular valor booleano que indica si hay o no que dar de alta un titular. 
	 */
	public void registerLicense(TitularDTO titularDTO, LicenseDTO licenseDTO, Boolean registerTitular) { 
		
		License license = new License.Builder()
				.setLicenseType(licenseDTO.getLicenseType())
				.setObservation(licenseDTO.getObservation())
				.setExpiryDate(licenseDTO.getExpiryDate())
				.build();
		
		saveLicense(license);
		
		if(registerTitular) {
			TitularController.getInstance().registerTitular(titularDTO, license);
		}
		else { 
			Integer id = titularDTO.getId();
			TitularController.getInstance().addTitularsLicense(id, license);
		}
	}
	
	/**
	 * Este metodo carga el Combo Box de tipos de licencia, solo aquellos tipos que el titular 
	 * esta habilido a poseer al momento de la emision.
	 * @param comboBox objeto del tipo ComboBox a ser cargado.
	 * @param titularDTO datos del titular. 
	 * @param registerTitular valor booleano que indica si hay o no que dar de alta un titular, 
	 * es utilizado para saber si es la primera licencia que va a obtener.
	 */
	public void loadLicenseTypeComboBox(JComboBox<LicenseType> comboBox, TitularDTO titularDTO, Boolean registerTitular) {
		
		if(registerTitular) {
			
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
	
	/**
	 * Este metodo valida los datos de una licencia ingresados por pantalla.
	 * @param licenseDTO datos de una licencia.
	 * @return Lista de string con los errores encontrados.
	 */
	public List<String> validate(LicenseDTO licenseDTO) { 
		List<Validator<String,LicenseDTO>> validators = new ArrayList<Validator<String,LicenseDTO>>();
		
		validators.add(new ObservationValidator());
	
		Validator<String,LicenseDTO> validator = new CompositeValidator<String,LicenseDTO>(validators);
		
		return validator.validate(licenseDTO);
	}
	
	/**
	 * Este metodo carga el Combo Box de tipos de documentos.
	 * @param comboBox objeto del tipo ComboBox a ser cargado.
	 */
	public void loadTypeIdComboBox(JComboBox<TypeId> comboBox) {
		for(TypeId typeId: TypeId.values()) {
			comboBox.addItem(typeId);
		}
	}
	
	/**
	 * Este metodo carga el Combo Box de tipos de sangre.
	 * @param comboBox objeto del tipo ComboBox a ser cargado.
	 */
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
	
	/**
	 * Este metodo se comunica con la capa DAO para recuperar una licencia de la base de datos.
	 * @param id entero que corresponde a la primary key de una licencia.
	 * @return objeto de tipo Licencia o null en el caso que no exista.
	 */
	public License findLicense(Integer id) {
		return licenseDAO.find(id);
	}
	
	/**
	 * Este metodo se comunica con la capa DAO para guardar una licencia en la base de datos.
	 * @param license licencia a ser perdurada en base de datos.
	 */
	public void saveLicense(License license) {
		licenseDAO.save(license);
	}
	
	/**
	 * Este metodo delega el calculo del costo de una licencia a la clase calculadora de costo. 
	 * @param licenseType tipo de licencia.
	 * @param expiryDate fecha de vencimiento de la licencia.
	 * @return costo de la licencia.
	 */
	public Double calculateLicenseCost(LicenseType licenseType, Date expiryDate) {
		
		LocalDate expiry = expiryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate actual = LocalDate.now();
		Integer validity = (int) ChronoUnit.YEARS.between(actual, expiry);
		
		Combination combination = new Combination(licenseType, validity);
		
		return licenseCostCalculator.getLicenseCost(combination);
	}
	
	/**
	 * Este metodo delega el calculo de la fecha de expiracion de una licencia a la clase 
	 * calculadora de fecha de expiracion. 
	 * @param titularDTO datos del titular que obtendra la licencia.
	 * @return fecha de expiracion de la licencia, donde el dia y mes coinciden con el dia 
	 * y mes de nacimiento del titular.
	 */
	public Date calculateExpiryDate(TitularDTO titularDTO) { 
		
		Date birthday = titularDTO.getBirthdate();
		Long personalId = Long.parseLong(titularDTO.getPersonalId());
		TypeId typeId = titularDTO.getTypeId();
		
		return expiryDateCalculator.calculateExpiryDate(typeId, personalId, birthday);
		
	}
	
}
