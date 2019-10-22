package controllers;

import java.util.ArrayList;
import java.util.List;

import DAOs.TitularDAO;
import DAOs.TitularDAOSQL;
import DTOs.TaxPayerDTO;
import DTOs.TitularDTO;
import domain.License;
import domain.Titular;
import domain.TypeId;
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
	
	public Titular registerTitular(TitularDTO info) {
		
		Titular titular = new Titular();
		
		titular.setTypeId(info.getTypeId());
		titular.setPersonalId(info.getPersonalId());
		titular.setName(info.getName());
		titular.setSurname(info.getSurname());
		titular.setAdress(info.getAdress());
		titular.setBirthday(info.getBirthday());
		titular.setBloodType(info.getBloodType());
		titular.setOrganDonor(info.getOrganDonor());
		
		Runnable r = () -> {
			titularDAO.save(titular);
		};
		Thread thread = new Thread(r);
		thread.start();
		
		return titular;
	}
	
	public void titularLocator(TypeId typeId, Long personalIdFragment) {
		
		List<Titular> coincidences = titularDAO.findAllByPersonalId(typeId, personalIdFragment);
		
		if(coincidences.isEmpty()) { 
			// TODO mostrar mensajito "No hay coincidencias."
		}
		else {
			List<TitularDTO> coincidencesDTO = createTitularDTO(coincidences);
			// TODO actualizar lista con el resultado de la busqueda
		}
	}
	
	private List<TitularDTO> createTitularDTO(List<Titular> titulares) {
		List<TitularDTO> titularDTOs = new ArrayList<TitularDTO>();
		
		for(Titular titular: titulares) {
			TitularDTO titularDTO = new TitularDTO();
			titularDTO.setId(titular.getId());
			titularDTO.setTypeId(titular.getTypeId());
			titularDTO.setPersonalId(titular.getPersonalId());
			titularDTO.setName(titular.getName());
			titularDTO.setSurname(titular.getSurname());
			titularDTO.setAdress(titular.getAdress());
			titularDTO.setBirthday(titular.getBirthday());
			titularDTO.setBloodType(titular.getBloodType());
			titularDTO.setOrganDonor(titular.getOrganDonor());
			titularDTOs.add(titularDTO);
		}
		
		return titularDTOs;
	}

	public Titular findTitular(Integer id) {
		return titularDAO.find(id);
	}
	
	public Boolean existsTitular(TypeId typeId, Long personalId) {
		
		Titular titular = titularDAO.findByPersonalId(typeId, personalId);
		
		if(titular == null) {
			return false;
		}
		return true;
	}
	
}
