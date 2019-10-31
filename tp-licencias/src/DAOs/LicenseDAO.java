package DAOs;

import java.util.List;

import domain.License;

public interface LicenseDAO extends GenericDAO<License, Integer> {

	List<License> findAllLicenses();
	
}