package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import dao.TitularDAO;
import dao.TitularDAOSQL;
import domain.License;
import domain.Titular;
import domain.TypeId;
import dto.TitularDTO;
import validators.AdressValidator;
import validators.BirthdateValidator;
import validators.BloodValidator;
import validators.CompositeValidator;
import validators.IdValidator;
import validators.NameValidator;
import validators.Validator;

/**
 * Esta es la clase controladora de Titulares. Singleton.
 * Implementa los metodos que seran llamados desde la GUI.
 * Se comunica con la capa DAO para recuperar, persistir o actualizar datos. 
 * @author Juan Suppicich & Matias Durand
 *
 */
public class TitularController {
	
	private static TitularController _INSTANCE = new TitularController();
	private TitularDAO titularDAO = new TitularDAOSQL(Titular.class);
	
	
	private TitularController () { 
	}
	
	public static TitularController getInstance() { 
		return _INSTANCE;
	}	

	/**
	 * Este metodo valida los datos de un titular ingresados por pantalla, delegando la validacion 
	 * a cada clase validadora concreta. 
	 * @param titularDTO datos del titular.
	 * @return Lista de Strings con todos los errores encontrados.
	 */
	public List<String> validate(TitularDTO info) {
		List<Validator<String,TitularDTO>> validators = new ArrayList<Validator<String,TitularDTO>>();
		
		validators.add(new IdValidator());
		validators.add(new NameValidator());
		validators.add(new BirthdateValidator());
		validators.add(new AdressValidator());
		validators.add(new BloodValidator());
		
		Validator<String,TitularDTO> validator = new CompositeValidator<String,TitularDTO>(validators);
		
		return validator.validate(info);
		
	}
	
	/**
	 * Este metodo instancia un nuevo titular y lo persiste en la base de datos, en un hilo secundario. 
	 * @param titularDTO datos del titular.
	 * @param license licencia que ha sido emitida y corresponde al nuevo titular.
	 */
	public void registerTitular(TitularDTO titularDTO, License license) {
		
		Titular titular = new Titular.Builder()
				.setTypeId(titularDTO.getTypeId())
				.setPersonalId(Long.parseLong(titularDTO.getPersonalId()))
				.setName(titularDTO.getName())
				.setSurname(titularDTO.getSurname())
				.setAdress(titularDTO.getAdress())
				.setBirthdate(titularDTO.getBirthdate())
				.setBloodType(titularDTO.getBloodType())
				.setOrganDonor(titularDTO.getOrganDonor())
				.addLicense(license)
				.build();
		
		license.setTitular(titular);
		
		saveTitular(titular);
		
	}
	
	/**
	 * Este metodo localiza un contribuyente en la base de datos de mediante el tipo 
	 * y numero de documento. Delega la busqueda a la capa DAO.
	 * @param typeId tipo de documento del titular.
	 * @param personalId numero de documento del titular.
	 * @return un dto con los datos del titular que posee dicho tipo y numero de documento.
	 */
	public TitularDTO titularLocator(TypeId typeId, Long personalId) {
		
		Titular titular = titularDAO.findByPersonalId(typeId, personalId);
		
		if (titular!=null) {
			return createTitularDTO(titular);
		}else {
			return null;
		}
	}
	
	/**
	 * Este metodo construye un dto de titular. Objeto POJO.
	 * @param titular objeto del tipo Titular.
	 * @return el dto con los datos del titular.
	 */
	private TitularDTO createTitularDTO(Titular titular) {
		
		TitularDTO titularDTO = new TitularDTO();
		
		titularDTO.setId(titular.getId());
		titularDTO.setTypeId(titular.getTypeId());
		titularDTO.setPersonalId(titular.getPersonalId().toString());
		titularDTO.setName(titular.getName());
		titularDTO.setSurname(titular.getSurname());
		titularDTO.setAdress(titular.getAdress());
		titularDTO.setBirthdate(titular.getBirthdate());
		titularDTO.setBloodType(titular.getBloodType());
		titularDTO.setOrganDonor(titular.getOrganDonor());

		return titularDTO;
	}

	/** 
	 * Este metodo comprueba si existe o no un titular en la base de datos de titulares 
	 * que coincida con el tipo y numero de documento.
	 * @param typeId tipo de documento del titular.
	 * @param personalId numero de documento del titular.
	 * @return true en el caso de que exista un titular con dicho documento o false en el 
	 * caso de que no exista.
	 */
	public Boolean existsTitular(TypeId typeId, Long personalId) {
		
		Titular titular = titularDAO.findByPersonalId(typeId, personalId);
		
		if(titular == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Este metodo se comunica con la capa DAO para recuperar un titular de la base de datos.
	 * @param id entero que coincide a la primary key de un titular.
	 * @return objeto del tipo Titular o null en el caso de que no exista.
	 */
	public Titular findTitular(Integer id) {
		return titularDAO.find(id);	
	}
	
	/**
	 * Este metodo se comunica con la capa DAO para guardar un titular en la base de datos.
	 * @param titular titular a ser perdurado en la base de datos.
	 */
	public void saveTitular(Titular titular) {
		titularDAO.save(titular);
	}
	
	/**
	 * Este metodo se comunica con la capa DAO para recuperar un titular de la base de datos
	 * por tipo y numero de documento.
	 * @param typeId tipo de documento del titular.
	 * @param personalId numero de documento del titular.
	 * @return objeto del tipo Titular o null en el caso de que no exista.
	 */
	public Titular findTitularByPersonalId(TypeId typeId, Long personalId) {
		return titularDAO.findByPersonalId(typeId, personalId);
	}
	
	/**
	 * Este metodo agrega una licencia a la listas de licencias de un titular y delega la 
	 * actualizacion de base de datos a la capa DAO.
	 * @param id entero que corresponde a la primary key de un titular.
	 * @param license licencia que sera añdida a la lista.
	 */
	public void addTitularsLicense(Integer id, License license) {
		Titular titular = findTitular(id);
		titular.getLicenses().add(license);
		license.setTitular(titular);
		titularDAO.update(titular);
	}
	
	/**
	 * Este metodo carga el Combo Box de tipos de documentos.
	 * @param comboBox objeto del tipo ComboBox a ser cargado.
	 */
	public void loadTypeIdComboBox(JComboBox<TypeId> comboBox) {
		for(TypeId typeId: TypeId.values()) {
			comboBox.addItem(typeId);
		}
		comboBox.setSelectedItem(TypeId.DNI);
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
}
