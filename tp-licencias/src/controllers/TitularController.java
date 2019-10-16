package controllers;

import java.util.ArrayList;
import java.util.List;

import validators.CompositeValidator;
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

	private List<String> validate(TitularDTO info) {
		
		new List<Validator<TitularDTO>> validators = new ArrayList<Validator<TitularDTO>>();
		
		validators.add(new NameValidator<TitularDTO>());
		
		Validator<TitularDTO> validator = new CompositeValidator<TitularDTO>(validators);
		
		return validator.validate(info);
		
	}
	
	
	
	
}
