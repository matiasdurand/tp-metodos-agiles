package validators;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.LicenseController;

import domain.LicenseType;
import domain.TypeId;

import dto.TitularDTO;

class LicenseTypeValidator {

	private LicenseController licenseController = LicenseController.getInstance();
	
	private TitularDTO titularDTO1 = new TitularDTO();
	
	//private TitularDTO titularDTO2 = new TitularDTO();
	
	
	@BeforeEach
	void testLoadData() {
		
		//CASO TITULAR A DAR DE ALTA
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
		
		
		//CASO TITULAR YA EXISTENTE
		/*titularDTO2.setTypeId(TypeId.DNI);
		titularDTO2.setPersonalId("40662222");
		titularDTO2.setName("Juan");
		titularDTO2.setSurname("Suppicich");
		
		b = LocalDate.of(1997, 8, 21);
		birthdate = Date.from(b.atStartOfDay(ZoneId.systemDefault()).toInstant());
		titularDTO2.setBirthday(birthdate);
		
		titularDTO2.setAdress("1 de Mayo 3251");
		titularDTO2.setBloodType("A+");
		titularDTO2.setOrganDonor(false);*/
		
	}
	
	@Test
	void testLoadLicenseTypeComboBox() {
		
		JComboBox<LicenseType> licenseTypesComboBox = new JComboBox<LicenseType>();
		
		licenseController.loadLicenseTypeComboBox(licenseTypesComboBox, titularDTO1, true);
		
		assertEquals(4, licenseTypesComboBox.getItemCount());
		
	}

}
