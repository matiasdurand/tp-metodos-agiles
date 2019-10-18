package validators;

import java.util.ArrayList;
import java.util.List;

import DTOs.TitularDTO;

public class AdressValidator implements Validator<TitularDTO> {

	@Override
	public List<String> validate(TitularDTO info) {
		List<String> errors = new ArrayList<String>();
		
		String adress = info.getAdress();
		
		if(adress == null) {
			errors.add("Lo sentimos, hubo un error.");
		}
		else {
			if(adress.isEmpty()) {
				errors.add("Debe completar la direccion del titular.");
			}
		}
		
		return errors;
	}

}
