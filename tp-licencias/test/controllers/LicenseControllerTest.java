package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import domain.LicenseType;
import domain.TypeId;

import dto.TitularDTO;

class LicenseControllerTest {

	private LicenseController licenseController = LicenseController.getInstance();
	
	private TitularDTO titularDTO1 = new TitularDTO();
	private Date expiryDateExpected1;
	
	/*private TitularDTO titularDTO2 = new TitularDTO();
	private Date expiryDateExpected2;*/
	
	private Date expiryDate;
	
	
	@Test //CASO 1: Titular mayor de 21 años.
	void testCalculateExpiryDateOverTwentyOne() {
		
		LocalDate birthdate = LocalDate.of(1997, 12, 11);
		Date b = Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		LocalDate expiryDate = LocalDate.of(2024, 12, 11);
		expiryDateExpected1 = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		titularDTO1.setTypeId(TypeId.DNI);
		titularDTO1.setPersonalId("40406072");
		titularDTO1.setBirthdate(b);
		
		assertEquals(expiryDateExpected1, licenseController.calculateExpiryDate(titularDTO1));
	}
	
	@Test //CASO 2: Titular menor de 21 años.
	void testCalculateExpiryDateUnderTwentyOne() {
		
		/*LocalDate birthdate = LocalDate.of(2000, 12, 11);
		Date b = Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		expiryDate = LocalDate.of(2001, 12, 11);
		expiryDateExpected2 = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		titularDTO2.setTypeId(TypeId.DNI);
		titularDTO2.setPersonalId("43068970");
		titularDTO2.setBirthdate(b);
		
		assertEquals(expiryDateExpected2, licenseController.calculateExpiryDate(titularDTO2));*/
	}
	
	@Test //CASO 1: Licencia de clase A y fecha de expiración en el próximo año (vigencia igual a un año).
	void testCalculateLicenseCost() {
		
		LocalDate expDate = LocalDate.of(2020, 12, 11);
		this.expiryDate = Date.from(expDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		assertEquals(20.00, licenseController.calculateLicenseCost(LicenseType.A, expiryDate));
	}

}
