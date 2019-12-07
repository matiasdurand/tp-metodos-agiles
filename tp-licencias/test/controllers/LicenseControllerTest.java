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
	private Date expectedExpiryDate1;
	
	private TitularDTO titularDTO2 = new TitularDTO();
	private Date expectedExpiryDate2;
	
	private TitularDTO titularDTO3 = new TitularDTO();
	private Date expectedExpiryDate3;
	
	private Date expiryDate;
	
	
	@Test
	void testCalculateLicenseCost() {
		
		LocalDate expiryLocalDate = LocalDate.of(2020, 12, 11);
		this.expiryDate = Date.from(expiryLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		assertEquals(28.00, licenseController.calculateLicenseCost(LicenseType.A, expiryDate));
	}
	
	@Test
	void testCalculateExpiryDateOverTwentyOne() {
		
		titularDTO1.setTypeId(TypeId.DNI);
		titularDTO1.setPersonalId("40000000");
		
		LocalDate birthdate = LocalDate.of(1997, 12, 11);
		Date b = Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		titularDTO1.setBirthdate(b);
		
		LocalDate expectedExpiryDate = LocalDate.of(2024, 12, 11);
		expectedExpiryDate1 = Date.from(expectedExpiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		assertEquals(expectedExpiryDate1, licenseController.calculateExpiryDate(titularDTO1));
	}
	
	@Test
	void testCalculateExpiryDateUnderTwentyOneFirstTime() {
		
		titularDTO2.setTypeId(TypeId.DNI);
		titularDTO2.setPersonalId("42000000");
		
		LocalDate birthdate = LocalDate.of(2000, 12, 11);
		Date b = Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		titularDTO2.setBirthdate(b);
		
		LocalDate expectedExpiryDate = LocalDate.of(2020, 12, 11);
		expectedExpiryDate2 = Date.from(expectedExpiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	
		assertEquals(expectedExpiryDate2, licenseController.calculateExpiryDate(titularDTO2));
	}
	
	@Test
	void testCalculateExpiryDateUnderTwentyOne() {
		
		titularDTO3.setTypeId(TypeId.DNI);
		titularDTO3.setPersonalId("42000001");
		
		LocalDate birthdate = LocalDate.of(2000, 12, 11);
		Date b = Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		titularDTO3.setBirthdate(b);
		
		LocalDate expectedExpiryDate = LocalDate.of(2022, 12, 11);
		expectedExpiryDate3 = Date.from(expectedExpiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	
		assertEquals(expectedExpiryDate3, licenseController.calculateExpiryDate(titularDTO3));
	}

}
