package dao;

import java.util.List;

import audit.LicenseMovement;

public interface LicenseMovementDAO extends GenericDAO<LicenseMovement, Integer> {
	
	public List<LicenseMovement> findAllLicenseMovements();

}
