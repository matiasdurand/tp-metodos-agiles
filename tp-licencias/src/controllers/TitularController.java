package controllers;

import java.util.ArrayList;
import java.util.List;

import DTOs.TitularDTO;
import domain.Titular;
import validators.AdressValidator;
import validators.BirthdayValidator;
import validators.BloodValidator;
import validators.CompositeValidator;
import validators.IdValidator;
import validators.NameValidator;
import validators.Validator;

public class TitularController {
	
	private TitularController _INSTANCE = null;
	
	private TitularController () { 
		
	}
	
	public TitularController getInstance() { 
		if(_INSTANCE == null) { 
			_INSTANCE = new TitularController();
		}
		return _INSTANCE;
	}	

	public List<String> validate(TitularDTO info) {
		
		List<Validator<TitularDTO>> validators = new ArrayList<Validator<TitularDTO>>();
		
		validators.add(new IdValidator());
		validators.add(new NameValidator());
		validators.add(new BirthdayValidator());
		validators.add(new AdressValidator());
		validators.add(new BloodValidator());
		
		Validator<TitularDTO> validator = new CompositeValidator<TitularDTO>(validators);
		
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
	
	
}
