package validators;

import java.util.ArrayList;
import java.util.List;

import DTOs.TitularDTO;


public class NameValidator implements Validator<String,TitularDTO> {

	@Override
	public List<String> validate(TitularDTO info) {
		List<String> errors = new ArrayList<String>();
		
		String name = info.getName();
		String surname = info.getSurname();
		
		if(name == null || surname == null) {
			errors.add("Lo sentimos, hubo un error.");
		}
		else {
			if(name.isEmpty()) {
				errors.add("Debe completar el nombre del titular.");
			}
			
			if(surname.isEmpty()) {
				errors.add("Debe completar el apellido del titular.");
			}
		}
		
		return errors;
	}
	
}
