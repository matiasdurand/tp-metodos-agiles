package validators;

import java.util.ArrayList;
import java.util.List;

import dto.TitularDTO;

/**
 * Clase validadora concreta de tipo de sangre y donante. 
 * @author Suppicich Juan & Matias Durand
 *
 */
public class BloodValidator implements Validator<String,TitularDTO>{

	@Override
	public List<String> validate(TitularDTO info) {
		List<String> errors = new ArrayList<String>();
		
		String bloodType = info.getBloodType();
		Boolean organDonor = info.getOrganDonor();
		
		if(bloodType == null || organDonor == null) {
			errors.add("Lo sentimos, hubo un error.");
		}
		
		return errors;
	}

}
