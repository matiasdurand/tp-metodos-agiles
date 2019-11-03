package dao;

import java.io.Serializable;
import java.util.List;

/**
 * Esta interfaz define los metodos genericos (CRUD) para recuperar, actualizar, guardar y listar 
 * entidades de la base de datos. Es la interfaz padre de la estructura DAO para la base de datos 
 * del sistema.  
 * @author 
 *
 * @param <T> tipo de dato generico que representa la entidad del dominio. 
 * @param <ID> tipo de dato generico que representa un parametro de busqueda.
 * @see http://www.cursohibernate.es/doku.php?id=unidades:07_arquitectura:03_dao
 */
public interface GenericDAO<T, ID extends Serializable> {
	
	T create();
	void save(T entity);
	T update (T entity);
	T find(ID id);
	List<T> findAll();

}
