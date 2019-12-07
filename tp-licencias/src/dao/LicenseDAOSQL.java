package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.NoResultException;

import domain.License;

/**
 * Esta clase es una implementacion para SQL (utilizando Hibernate) de la interfaz LicenseDAO.
 * @author 
 *
 */
public class LicenseDAOSQL extends GenericDAOSQL<License, Integer> implements LicenseDAO {

	public LicenseDAOSQL(Class<License> type) {
		super(type);
	}
 
	@Override
	public List<License> findAllLicenses() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			@SuppressWarnings("unchecked")
			List<License> licenses = (List<License>) session.createQuery("from License").getResultList(); 
			return licenses;
		}
		catch(NoResultException e) {
			return null;
		}
		finally {
			session.getTransaction().commit();
			session.close();
			factory.close();
	
		}
	}

	@Override

	public List<License> findValidLicensesOfTitular(Integer id) {
		// TODO Auto-generated method stub
		/**
		 * Este metodo debe devolver una lista con las licencias vigentes del titular. 
		 */
		return null;
	}

	public List<License> findLastLicensesOfTitular(Integer id) {
		// TODO Auto-generated method stub
		/*Este metodo tiene que devolver una lista de licencias que posee el titular
		la lista debe tener UNA licencia de cada tipo que posea el titular, ya sea vigente 
		o expirada, en el caso de que tenga una licencia expirada debe ser la ULTIMA expirada 
		Por ejemplo, si el titular tiene una licencia tipo A vigente y dos tipo B expiradas 
		la lista resultante debe estar compuesta por la licencia tipo A vigente y la ultima licencia 
		tipo B expirada. 
		Borrar este comentario, por mas informacion comunicarse de inmediato al grupo :p 
		 * */
		 
		return null;
	}
}