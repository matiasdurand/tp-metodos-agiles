package validators;

import java.util.ArrayList;
import java.util.List;

import domain.TypeId;
import dto.TitularDTO;

/**
 * Clase validadora concreta de numero de documento. 
 * @author Suppicich Juan & Matias Durand
 *
 */
public class IdValidator implements Validator<String,TitularDTO> {

	@Override
	public List<String> validate(TitularDTO info) {
		List<String> errors = new ArrayList<String>();
		
		String personalId = info.getPersonalId();
		TypeId typeId = info.getTypeId();
		
		if(typeId == null || personalId == null) {
			errors.add("Lo sentimos, hubo un error.");
		}
		else {
			if(personalId.matches("[0-9]+")) {
				try {
					@SuppressWarnings("unused")
					Long id = Long.parseLong(personalId);
				}
				catch (NumberFormatException e) {
					System.out.print(e);
				}
				finally {
				}
			}
			else {
				errors.add("Documento invalido.");
			}
		}
		
		return errors;
	}

	
	
}
