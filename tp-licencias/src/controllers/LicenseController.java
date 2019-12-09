package controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import audit.LicenseMovement;
import dao.LicenseDAO;
import dao.LicenseDAOSQL;
import dao.LicenseMovementDAO;
import dao.LicenseMovementDAOSQL;
import domain.License;
import domain.LicenseType;
import domain.Titular;
import domain.TypeId;
import domain.User;
import dto.LicenseDTO;
import dto.TitularDTO;
import dto.UserDTO;
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
	private LicenseDAO licenseDAO = new LicenseDAOSQL(License.class);
	private LicenseMovementDAO licenseMovementDAO = new LicenseMovementDAOSQL(LicenseMovement.class);
	
	
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
	public void registerLicense(TitularDTO titularDTO, LicenseDTO licenseDTO, UserDTO userDTO, Boolean cameFrom) { 
		
		License license = new License.Builder()
				.setLicenseType(licenseDTO.getLicenseType())
				.setObservation(licenseDTO.getObservation())
				.setExpiryDate(licenseDTO.getExpiryDate())
				.build();
		
		if(cameFrom) {
			TitularController.getInstance().registerTitular(titularDTO, license, userDTO);
		}
		else { 
			TitularController.getInstance().addTitularsLicense(titularDTO.getId(), license);
			
			User user = UserController.getInstance().buildUser(userDTO);
			user.setId(userDTO.getId());
			
			registerLicenseMovement(license, user, LicenseMovement.Action.ALTA);
		}
		
	}
	
	public void registerLicenseMovement(License license, User user, LicenseMovement.Action action) {
		
		LicenseMovement licenseMovement = new LicenseMovement.Builder()
				.setAction(action)
				.setUser(user)
				.setLicense(license)
				.build();
		
		licenseMovementDAO.save(licenseMovement);
		
	}
	
	/**
	 * Este metodo carga el Combo Box de tipos de licencia, solo aquellos tipos que el titular 
	 * esta habilido a poseer al momento de la emision.
	 * @param comboBox objeto del tipo ComboBox a ser cargado.
	 * @param titularDTO datos del titular. 
	 * @param registerTitular valor booleano que indica si hay o no que dar de alta un titular, 
	 * es utilizado para saber si es la primera licencia que va a obtener.
	 */
	public void loadLicenseTypeComboBox(JComboBox<LicenseType> comboBox, TitularDTO titularDTO, Boolean cameFrom) {

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
	 * Este metodo se comunica con la capa DAO para actualizar una licencia en la base de datos.
	 * @param license licencia a ser actualizada en base de datos. 
	 */
	public void updateLicense(License license) {
		licenseDAO.update(license);
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
		
		Date birthdate= titularDTO.getBirthdate();
		Long personalId = Long.parseLong(titularDTO.getPersonalId());
		TypeId typeId = titularDTO.getTypeId();
		
		return expiryDateCalculator.calculateExpiryDate(typeId, personalId, birthdate);
		
	}
	
	/**
	 * Este metodo se comunica con la capa DAO para recuperar una lista de las licencias 
	 * vigentes que posee un titular
	 * @param id entero que corresponde a la primary key de un titular.
	 * @return lista de licencias.
	 */
	public List<License> findValidLicensesOfTitular(Integer id) { 
		return licenseDAO.findValidLicensesOfTitular(id);
	}
	
	/**
	 * Este metodo crea una nueva instancia de licencia que sera renovada y delega la perduracion 
	 * en base de datos.
	 * @param titularDTO datos del titualr, provenientes de GUI.
	 * @param licenseDTO datos de la licencia, provenientes de GUI.
	 */
	public void renewLicense(TitularDTO titularDTO, LicenseDTO licenseDTO, UserDTO userDTO) {
		
		License license = new License.Builder()
				.setLicenseType(licenseDTO.getLicenseType())
				.setObservation(licenseDTO.getObservation())
				.setExpiryDate(licenseDTO.getExpiryDate())
				.build();
		
		TitularController.getInstance().addTitularsLicense(titularDTO.getId(), license);
		
		User user = UserController.getInstance().buildUser(userDTO);
		user.setId(userDTO.getId());
		
		registerLicenseMovement(license, user, LicenseMovement.Action.RENOVACION);
			
	}
	
	/**
	 * Este metodo recibe un JTable y carga los datos de las licencias que el titular esta 
	 * habilitado a renovar. Asigna el TableModel al JTable.
	 * @param titularDTO datos del titular que desea renovar una licencia.
	 * @param table JTable proveniente de GUI a la cual se cargaran los datos. 
	 */
	public void loadRenewLicenceTable(TitularDTO titularDTO, JTable table) {
		
		List<License> result = new ArrayList<License>();
		List<License> licenses = findLastLicensesOfTitular(titularDTO.getId());
		Date todaysDate = new Date();
		DefaultTableModel model = new DefaultTableModel();
		ArrayList<Object> columnsName = new ArrayList<Object>();
		
		columnsName.add("Tipo Licencia");
		columnsName.add("Fecha Emision"); 
		columnsName.add("Fecha Expiracion");
		columnsName.add("Observacion");
		
		for(Object o : columnsName) { //Agrego las columnas al TableModel
			model.addColumn(o);
		}
		
		for(License l : licenses) { //Me quedo con las licencias vencidas
			if(l.getExpiryDate().compareTo(todaysDate) <= 0) {
				result.add(l);
			}
		}
		
		for(License l : result) { //Agrego la informacion de cada licencia al TableModel
			Object[] object = new Object [] {
					l.getLicenseType().toString(), 
					l.getEmisionDate().toString(),
					l.getExpiryDate().toString(),
					l.getObservation().toString()
			};
			model.addRow(object);
		}
		
		table.setModel(model);
		
	}
	
	/**
	 * Este metodo se comunica con la capa DAO para recuperar una lista de licencias del titular 
	 * compuesta por una licencia de cada tipo que posea, ya sea vigente o vencida cuya fecha de 
	 * vencimiento en caso de estar vencida sea la mas cercana a la actual.
	 * @param id entero que corresponde a la primary key de un titular
	 * @return lista de licencias resultante.
	 */
	public List<License> findLastLicensesOfTitular(Integer id) { 
		return licenseDAO.findLastLicensesOfTitular(id);
	}
	
	public void loadLicensesTable(JTable table, Boolean filter) {
		List<License> licenses = licenseDAO.findAllLicenses();
		Date todaysDate = new Date();
		
		if(filter) {
			licenses = licenses.stream()
					.filter(l -> l.getExpiryDate().compareTo(todaysDate) <= 0)
					.collect(Collectors.toList());
		}
		
		DefaultTableModel model = new DefaultTableModel();
		ArrayList<Object> columnsName = new ArrayList<Object>();
		
		columnsName.add("Tipo Licencia");
		columnsName.add("Fecha Emision"); 
		columnsName.add("Fecha Expiracion");
		columnsName.add("Observacion");
		
		for(Object o: columnsName) { //Agrego las columnas al TableModel
			model.addColumn(o);
		}
		
		for(License l: licenses) { //Agrego la informacion de cada licencia al TableModel
			Object[] object = new Object [] {
					l.getLicenseType().toString(), 
					l.getEmisionDate().toString(),
					l.getExpiryDate().toString(),
					l.getObservation().toString()
			};
			model.addRow(object);
		}
		table.setModel(model);
	}

}
