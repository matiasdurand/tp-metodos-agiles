package dao;

import java.util.ArrayList;
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
	
	@Override
	public List<License> findValidLicensesOfTitular(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<License> findLastLicensesOfTitular(Integer id){			
		List<License> licenses = new ArrayList<License>();
		License LicenciaA = findLastLicenseOfTitularClassA(id);
		License LicenciaB = findLastLicenseOfTitularClassB(id);
		License LicenciaC = findLastLicenseOfTitularClassC(id);
		License LicenciaD = findLastLicenseOfTitularClassD(id);
		License LicenciaE = findLastLicenseOfTitularClassE(id);
		License LicenciaF = findLastLicenseOfTitularClassF(id);
		License LicenciaG = findLastLicenseOfTitularClassG(id);
		if(LicenciaA!=null) {
			licenses.add(LicenciaA);
		}
		if(LicenciaB!=null) {
			licenses.add(LicenciaB);
		}
		if(LicenciaC!=null) {
			licenses.add(LicenciaC);
		}
		if(LicenciaD!=null) {
			licenses.add(LicenciaD);
		}
		if(LicenciaE!=null) {
			licenses.add(LicenciaE);
		}
		if(LicenciaF!=null) {
			licenses.add(LicenciaF);
		}
		if(LicenciaG!=null) {
			licenses.add(LicenciaG);
		}
		return licenses;
	}
	
	private License findLastLicenseOfTitularClassA(int id){
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			License LicenciaA = (License) session.createSQLQuery("SELECT * FROM licencia l WHERE l.id_titular="+id+" and l.clase ='A' and l.fecha_emision IN (SELECT max(lA.fecha_emision) FROM licencia lA WHERE lA.id_titular="+id+" and clase= 'A')").addEntity(License.class).getSingleResult();
			return LicenciaA;
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
	
	private License findLastLicenseOfTitularClassB(int id){
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			License LicenciaB = (License) session.createSQLQuery("SELECT * FROM licencia l WHERE l.id_titular="+id+" and l.clase ='B' and l.fecha_emision IN (SELECT max(lB.fecha_emision) FROM licencia lB WHERE lB.id_titular="+id+" and clase= 'B')").addEntity(License.class).getSingleResult();
			return LicenciaB;
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
	
	private License findLastLicenseOfTitularClassC(int id){
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			License LicenciaC = (License) session.createSQLQuery("SELECT * FROM licencia l WHERE l.id_titular="+id+" and l.clase ='C' and l.fecha_emision IN (SELECT max(lC.fecha_emision) FROM licencia lC WHERE lC.id_titular="+id+" and clase= 'C')").addEntity(License.class).getSingleResult();
			return LicenciaC;
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
	
	private License findLastLicenseOfTitularClassD(int id){
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			License LicenciaD = (License) session.createSQLQuery("SELECT * FROM licencia l WHERE l.id_titular="+id+" and l.clase ='D' and l.fecha_emision IN (SELECT max(lD.fecha_emision) FROM licencia lD WHERE lD.id_titular="+id+" and clase= 'D')").addEntity(License.class).getSingleResult();
			return LicenciaD;
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
	
	private License findLastLicenseOfTitularClassE(int id){
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			License LicenciaE = (License) session.createSQLQuery("SELECT * FROM licencia l WHERE l.id_titular="+id+" and l.clase ='E' and l.fecha_emision IN (SELECT max(lE.fecha_emision) FROM licencia lE WHERE lE.id_titular="+id+" and clase= 'E')").addEntity(License.class).getSingleResult();
			return LicenciaE;
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
	
	private License findLastLicenseOfTitularClassF(int id){
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			License LicenciaF = (License) session.createSQLQuery("SELECT * FROM licencia l WHERE l.id_titular="+id+" and l.clase ='F' and l.fecha_emision IN (SELECT max(lF.fecha_emision) FROM licencia lF WHERE lF.id_titular="+id+" and clase= 'F')").addEntity(License.class).getSingleResult();
			return LicenciaF;
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
	
	private License findLastLicenseOfTitularClassG(int id) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			License LicenciaG = (License) session.createSQLQuery("SELECT * FROM licencia l WHERE l.id_titular="+id+" and l.clase ='G' and l.fecha_emision IN (SELECT max(lG.fecha_emision) FROM licencia lG WHERE lG.id_titular="+id+" and clase= 'G')").addEntity(License.class).getSingleResult();
			return LicenciaG;
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