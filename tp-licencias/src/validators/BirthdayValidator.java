package validators;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import DTOs.TitularDTO;

public class BirthdayValidator implements Validator<TitularDTO> {

	@Override
	public List<String> validate(TitularDTO info) {
		List<String> errors = new ArrayList<String>();
		
		LocalDate birthday = info.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
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
