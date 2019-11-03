package dao;

import java.io.Serializable;
import java.util.List;


/**
 * Esta clase es una implementacion generica para SQL (utilizando Hibernate) de la interfaz GenericDAO
 * de esta clase heredaran las implementaciones de cada interfaz DAO, de esta manera se evita duplicar codigo
 * en cada clase.
 * @author 
 *
 * @param <T> tipo de dato generico que representa la entidad del dominio.  
 * @param <ID> tipo de dato generico que representa un parametro de busqueda.
 */
public class GenericDAOSQL<T, ID extends Serializable> implements GenericDAO<T,ID> {
		
	@Override
	public T create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T find(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
