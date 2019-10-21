package controllers;

import java.util.ArrayList;
import java.util.List;

import DAOs.TitularDAO;
import DAOs.TitularDAOSQL;
import DTOs.TaxPayerDTO;
import DTOs.TitularDTO;
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
	
	public void registerTitular(TitularDTO info) {
		
		Titular titular = new Titular();
		
		titular.setTypeId(info.getTypeId());
		titular.setPersonalId(info.getPersonalId());
		titular.setName(info.getName());
		titular.setSurname(info.getSurname());
		titular.setAdress(info.getAdress());
		titular.setBirthday(info.getBirthday());
		titular.setBloodType(info.getBloodType());
		titular.setOrganDonor(info.getOrganDonor());
		
		//completar
		
	}
	
	public void titularLocator(TypeId typeId, Long id) {
		
		TitularDTO titularDTO = titularDAO.find(typeId, id);
		
		if(titularDTO == null) { 
			//mostrar popup que el titular no existe y que lo tiene que dar de alta
		}
		else {
			//actualizar lista con el resultado de la busqueda
		}

	}
	
	public Titular findTitular(Integer id) {
		return titularDAO.find(id);
	}
	
}
