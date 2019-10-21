package DAOs;

import java.io.Serializable;
import java.util.List;

/* Esta clase implementa de manera generica el acceso a la base de datos SQL (mediante Hibernate),
 * de esta clase heredaran las implementaciones de cada interfaz DAO, de esta manera se evita duplicar codigo en cada entidad*/

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
