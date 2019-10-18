package validators;

import java.util.ArrayList;
import java.util.List;

import DTOs.TitularDTO;
import domain.BloodType;

public class BloodValidator implements Validator<TitularDTO>{

	@Override
	public List<String> validate(TitularDTO info) {
		List<String> errors = new ArrayList<String>();
		
		BloodType bloodType = info.getBloodType();
		Boolean organDonor = info.getOrganDonor();
		
		if(bloodType == null || organDonor == null) {
			errors.add("Lo sentimos, hubo un error.");
		}
		
		return errors;
	}

}
