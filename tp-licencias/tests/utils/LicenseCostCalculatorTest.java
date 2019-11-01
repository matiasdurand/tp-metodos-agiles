package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.LicenseController;

import domain.LicenseType;

class LicenseCostCalculatorTest {

	private LicenseController licenseController = LicenseController.getInstance();
	
	private Date expiryDate1 = null;
	private Date expiryDate2 = null;
	
	
	@BeforeEach
	void testLoadData() {
		
		LocalDate expiryDate = LocalDate.of(2020, 12, 11);
		expiryDate1 = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		expiryDate = LocalDate.of(2024, 12, 11);
		expiryDate2 = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
	}
	
	@Test
	void testCalculateLicenseCost() {
		assertEquals(20.00, licenseController.calculateLicenseCost(LicenseType.A, expiryDate1));
		assertEquals(47.00, licenseController.calculateLicenseCost(LicenseType.C, expiryDate2));
	}

}
