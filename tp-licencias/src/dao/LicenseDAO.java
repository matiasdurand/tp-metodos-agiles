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

	public  List<License> findValidLicensesOfTitular(Integer id);

	public List<License> findLastLicensesOfTitular(Integer id);

}