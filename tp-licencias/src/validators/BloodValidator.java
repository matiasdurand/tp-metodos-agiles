package validators;

import java.util.ArrayList;
import java.util.List;

import dto.TitularDTO;

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
