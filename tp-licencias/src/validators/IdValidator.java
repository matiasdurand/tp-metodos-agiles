package validators;

import java.util.ArrayList;
import java.util.List;

import DTOs.TitularDTO;
import domain.TypeId;

public class IdValidator implements Validator<String,TitularDTO> {

	@Override
	public List<String> validate(TitularDTO info) {
		List<String> errors = new ArrayList<String>();
		
		Long personalId = info.getPersonalId();
		TypeId typeId = info.getTypeId();
		
		if(typeId == null || personalId == null) {
			errors.add("Lo sentimos, hubo un error.");
		}
		
		return errors;
	}

	
	
}
