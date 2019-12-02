package dao;

import java.util.List;

import domain.License;

/**
 * Esta interfaz define los metodos CRUD especificos correspondiente a la entidad Licencia.
 * @author 
 *
 */
public interface LicenseDAO extends GenericDAO<License, Integer> {

	public List<License> findAllLicenses();
	
	public List<License> findExpiredLicenses();
	
	public List<License> findActiveLicenses();
}