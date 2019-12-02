package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.NoResultException;

import domain.License;
import domain.Titular;

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
	public List<License> findExpiredLicenses() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			@SuppressWarnings("unchecked")
			List<License> licenses = (List<License>) session.createQuery("from License where fecha_vencimiento < current_date").getResultList(); 
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
	public List<License> findActiveLicenses() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			@SuppressWarnings("unchecked")
			List<License> licenses = (List<License>) session.createQuery("from License where fecha_vencimiento > current_date").getResultList(); 
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
	
	
	public List<License> findLastLicenseOfTitular(int id){
		//creamos factory
				SessionFactory factory = createFactory();
				//creamos session BD
				Session session = createSession(factory);
				//creamos consulta HQL
				try {
					@SuppressWarnings("unchecked")
					License LicenciaA = (License) session.createQuery("from Licencia where id_titular = '" + id + "' and clase ='A' and fecha_emision in ( select max(fecha_emision) from Licencia where id_titular= '" + id +"' and clase= 'A')").getSingleResult();
					License LicenciaB = (License) session.createQuery("from Licencia where id_titular = '" + id + "' and clase ='B' and fecha_emision in ( select max(fecha_emision) from Licencia where id_titular= '" + id +"' and clase= 'B')").getSingleResult();
					License LicenciaC = (License) session.createQuery("from Licencia where id_titular = '" + id + "' and clase ='C' and fecha_emision in ( select max(fecha_emision) from Licencia where id_titular= '" + id +"' and clase= 'C')").getSingleResult();
					License LicenciaD = (License) session.createQuery("from Licencia where id_titular = '" + id + "' and clase ='D' and fecha_emision in ( select max(fecha_emision) from Licencia where id_titular= '" + id +"' and clase= 'D')").getSingleResult();
					License LicenciaE = (License) session.createQuery("from Licencia where id_titular = '" + id + "' and clase ='E' and fecha_emision in ( select max(fecha_emision) from Licencia where id_titular= '" + id +"' and clase= 'E')").getSingleResult();
					License LicenciaF = (License) session.createQuery("from Licencia where id_titular = '" + id + "' and clase ='F' and fecha_emision in ( select max(fecha_emision) from Licencia where id_titular= '" + id +"' and clase= 'F')").getSingleResult();
					License LicenciaG = (License) session.createQuery("from Licencia where id_titular = '" + id + "' and clase ='G' and fecha_emision in ( select max(fecha_emision) from Licencia where id_titular= '" + id +"' and clase= 'G')").getSingleResult();
					
					List<License> licenses = new ArrayList<License>();
					licenses.add(LicenciaA);
					licenses.add(LicenciaB);
					licenses.add(LicenciaC);
					licenses.add(LicenciaD);
					licenses.add(LicenciaE);
					licenses.add(LicenciaF);
					licenses.add(LicenciaG);
					
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
}