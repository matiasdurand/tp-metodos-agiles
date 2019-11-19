package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import domain.TypeId;

import dto.TitularDTO;

class TitularControllerTest {

	private TitularController titularController = TitularController.getInstance();
	
	private TitularDTO titularDTO1 = new TitularDTO();
	private TitularDTO titularDTO2 = new TitularDTO();
	
	
	@Test //CASO 1: Datos del titular correctos.
	void testValidateCorrectTitularData() {
		
		titularDTO1.setTypeId(TypeId.DNI);
		titularDTO1.setPersonalId("40406072");
		titularDTO1.setName("Matias");
		titularDTO1.setSurname("Durand");
		titularDTO1.setAdress("Alvear 3483");
		titularDTO1.setBloodType("A+");
		titularDTO1.setOrganDonor(false);
		
		LocalDate birthdate = LocalDate.of(1997, 12, 11);
		Date b = Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		titularDTO1.setBirthdate(b);
		
		assertEquals(0, titularController.validate(titularDTO1).size());
	}
	
	@Test //CASO 2: Datos del titular incorrectos.
	void testValidateIncorrectTitularData() {
		
		titularDTO2.setTypeId(TypeId.DNI);
		titularDTO2.setPersonalId("A0406072");
		titularDTO2.setName("");
		titularDTO2.setSurname("");
		titularDTO2.setAdress("");
		titularDTO2.setBloodType("A+");
		titularDTO2.setOrganDonor(true);
		
		LocalDate birthdate = LocalDate.of(2004, 12, 11);
		Date b = Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		titularDTO2.setBirthdate(b);
		
		assertEquals(5, titularController.validate(titularDTO2).size());
	}

}
