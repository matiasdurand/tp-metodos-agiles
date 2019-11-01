package validators;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.TitularController;
import domain.TypeId;
import dto.TitularDTO;


class TitularDataValidatorTest {

	private TitularController titularController = TitularController.getInstance();
	
	private TitularDTO titularDTO1 = new TitularDTO();
	private TitularDTO titularDTO2 = new TitularDTO();
	
	
	@BeforeEach
	void testLoadData() {
		
		//CASO DATOS CORRECTOS
		titularDTO1.setTypeId(TypeId.DNI);
		titularDTO1.setPersonalId("40406072");
		titularDTO1.setName("Matias");
		titularDTO1.setSurname("Durand");
		
		LocalDate b = LocalDate.of(1997, 12, 11);
		Date birthdate = Date.from(b.atStartOfDay(ZoneId.systemDefault()).toInstant());
		titularDTO1.setBirthdate(birthdate);
		
		titularDTO1.setAdress("Alvear 3483");
		titularDTO1.setBloodType("A+");
		titularDTO1.setOrganDonor(false);
		
		//CASO DATOS TODOS ERRÓNEOS
		titularDTO2.setTypeId(TypeId.DNI);
		titularDTO2.setPersonalId("A0406072");
		titularDTO2.setName("");
		titularDTO2.setSurname("");
		
		b = LocalDate.of(2004, 12, 11);
		birthdate = Date.from(b.atStartOfDay(ZoneId.systemDefault()).toInstant());
		titularDTO2.setBirthdate(birthdate);
		
		titularDTO2.setAdress("");
		titularDTO2.setBloodType("A+");
		titularDTO2.setOrganDonor(true);
		
	}
	
	@Test
	void testValidate() {
		assertEquals(0, titularController.validate(titularDTO1).size());
		assertEquals(5, titularController.validate(titularDTO2).size());
	}

}

