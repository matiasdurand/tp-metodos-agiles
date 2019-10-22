package useful;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.LicenseController;
import domain.License;
import domain.LicenseType;

class LicenseCostCalculatorTest {

	private LicenseController licenseController = LicenseController.getInstance();
	private License license1 = new License();
	private License license2 = new License();
	
	
	@BeforeEach
	void testLoadData() {
		license1.setLicenseType(LicenseType.A);
		license1.setValidity(1);
		
		license2.setLicenseType(LicenseType.C);
		license2.setValidity(5);
	}
	
	@Test
	void testCalculateLicenseCost() {
		assertEquals(20.00, licenseController.calculateLicenseCost(license1));
		assertEquals(47.00, licenseController.calculateLicenseCost(license2));
	}

}
