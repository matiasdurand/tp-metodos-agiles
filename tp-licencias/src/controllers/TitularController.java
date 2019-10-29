package controllers;

import java.util.ArrayList;
import java.util.List;

import dao.TitularDAO;
import dao.TitularDAOSQL;
import domain.License;
import domain.Titular;
import domain.TypeId;
import dto.TitularDTO;
import validators.AdressValidator;
import validators.BirthdayValidator;
import validators.BloodValidator;
import validators.CompositeValidator;
import validators.IdValidator;
import validators.NameValidator;
import validators.Validator;

public class TitularController {
	
	private static TitularController _INSTANCE = new TitularController();
	private TitularDAO titularDAO = new TitularDAOSQL();
	
	private TitularController () { 
	}
	
	public static TitularController getInstance() { 
		return _INSTANCE;
	}	

	public List<String> validate(TitularDTO info) {
		List<Validator<String,TitularDTO>> validators = new ArrayList<Validator<String,TitularDTO>>();
		
		validators.add(new IdValidator());
		validators.add(new NameValidator());
		validators.add(new BirthdayValidator());
		validators.add(new AdressValidator());
		validators.add(new BloodValidator());
		
		Validator<String,TitularDTO> validator = new CompositeValidator<String,TitularDTO>(validators);
		
		return validator.validate(info);
		
	}
	
	public void registerTitular(TitularDTO info, License license) {
		
		Titular titular = new Titular();
		
		titular.setTypeId(info.getTypeId());
		titular.setPersonalId(Long.parseLong(info.getPersonalId()));
		titular.setName(info.getName());
		titular.setSurname(info.getSurname());
		titular.setAdress(info.getAdress());
		titular.setBirthday(info.getBirthday());
		titular.setBloodType(info.getBloodType());
		titular.setOrganDonor(info.getOrganDonor());
		titular.getLicenses().add(license);
		
		Runnable r = () -> {
			saveTitular(titular);
		};
		Thread thread = new Thread(r);
		thread.start();
		
	}
	
	public TitularDTO titularLocator(TypeId typeId, Long personalId) {
		
		Titular titular = titularDAO.findByPersonalId(typeId, personalId);
		
		return createTitularDTO(titular);
	
	}
	
	private TitularDTO createTitularDTO(Titular titular) {
		
		TitularDTO titularDTO = new TitularDTO();
		
		titularDTO.setId(titular.getId());
		titularDTO.setTypeId(titular.getTypeId());
		titularDTO.setPersonalId(titular.getPersonalId().toString());
		titularDTO.setName(titular.getName());
		titularDTO.setSurname(titular.getSurname());
		titularDTO.setAdress(titular.getAdress());
		titularDTO.setBirthday(titular.getBirthday());
		titularDTO.setBloodType(titular.getBloodType());
		titularDTO.setOrganDonor(titular.getOrganDonor());

		return titularDTO;
	}

	public Boolean existsTitular(TypeId typeId, Long personalId) {
		
		Titular titular = titularDAO.findByPersonalId(typeId, personalId);
		
		if(titular == null) {
			return false;
		}
		return true;
	}
	
	public Titular findTitular(Integer id) {
		return titularDAO.find(id);	
	}
	
	public void saveTitular(Titular titular) {
		titularDAO.save(titular);
	}
	
	public Titular findTitularByPersonalId(TypeId typeId, Long personalId) {
		return titularDAO.findByPersonalId(typeId, personalId);
	}
	
	public void addTitularsLicense(Integer id, License license) {
		
		Runnable r = () -> {
			Titular titular = findTitular(id);
			titular.getLicenses().add(license);
			titularDAO.update(titular);
		};
		Thread thread = new Thread(r);
		thread.start();
		
	}
	
}
