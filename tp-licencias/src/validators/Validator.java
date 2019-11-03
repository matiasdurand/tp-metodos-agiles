package validators;

import java.util.List;

/**
 * Esta interfaz define el metodo validate(), el cual deberan implementar todas las clases validadoras. 
 * @author Suppicich Juan & Matias Durand
 *
 * @param <D> tipo de dato generico que representa el tipo de dato del cual esta compuesta 
 * la lista que se retorna.
 * @param <T> tipo de dato generico que reprensenta el tipo de dato del objeto que se validará.
 * 
 * @see http://raulavila.com/2015/04/patron-composite/
 * @see https://refactoring.guru/design-patterns/composite
 */
public interface Validator <D,T> {
	
	public List<D> validate(T info);

}
