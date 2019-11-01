package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.LicenseController;
import domain.TypeId;
import dto.TitularDTO;

class ExpiryDateCalculatorTest {

	private LicenseController licenseController = LicenseController.getInstance();
	
	private TitularDTO titularDTO1 = new TitularDTO();
	//private TitularDTO titularDTO2 = new TitularDTO();
	
	private Date expiryDate1 = null;
	//private Date expiryDate2 = null;
	
	
	@BeforeEach
	void testLoadData() {
		
		//CASO EDAD > 21.
		LocalDate b = LocalDate.of(1997, 12, 11);
		Date birthdate = Date.from(b.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		titularDTO1.setTypeId(TypeId.DNI);
		titularDTO1.setPersonalId("40406072");
		titularDTO1.setBirthdate(birthdate);
		
		LocalDate e = LocalDate.of(2024, 12, 11);
		expiryDate1 = Date.from(e.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		//CASO EDAD < 21 SIN LICENCIAS.
		/*b = LocalDate.of(2000, 12, 11);
		birthdate = Date.from(b.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		titularDTO2.setTypeId(TypeId.DNI);
		titularDTO2.setPersonalId("43068970");
		titularDTO2.setBirthday(birthdate);
		
		e = LocalDate.of(2001, 12, 11);
		expiryDate2 = Date.from(e.atStartOfDay(ZoneId.systemDefault()).toInstant());*/
		
	}
	
	@Test
	void testCalculateExpiryDate() {
		assertEquals(expiryDate1, licenseController.calculateExpiryDate(titularDTO1));
		//assertEquals(expiryDate2, licenseController.calculateExpiryDate(titularDTO2));
	}

}

