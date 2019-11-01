package validators;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import dto.TitularDTO;

public class BirthdateValidator implements Validator<String,TitularDTO> {

	@Override
	public List<String> validate(TitularDTO info) {
		List<String> errors = new ArrayList<String>();
		
		LocalDate birthday = info.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		if(birthday == null) {
			errors.add("Lo sentimos, hubo un error.");
		}
		else {
			LocalDate actual = LocalDate.now();
			Long age = ChronoUnit.YEARS.between(birthday, actual);
		
			if(age <= 17) {
				errors.add("Debe ser mayor de 17 para solicitar una licencia.");
			}
		}
		
		return errors;
	}

	
	
}
