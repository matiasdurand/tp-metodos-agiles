package dao;

import java.io.Serializable;
import java.util.List;

/* Esta interfaz define los metodos genericos que deben implementar todas las interfaces DAO */

public interface GenericDAO<T, ID extends Serializable> {
	
	T create();
	void save(T entity);
	T update (T entity);
	T find(ID id);
	List<T> findAll();

}
