package validators;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es la encargada de crear la estructura de arbol del patron composite, 
 * delega las validaciones a las clases validadoras concretas o a otras clases composite. 
 * @author Juan Suppicich & Matias Durand
 * 
 * @param <D> 
 * @param <T>
 * 
 */
public class CompositeValidator<D,T> implements Validator <D,T> {
	
	private final List<Validator<D,T>> validators; 
	
	public CompositeValidator(List<Validator<D,T>> validators) {
		this.validators = validators; 
	}
	
	/**
	 * Este metodo delega las validaciones a los objetos que se encuentran en la lista validators
	 * Retorna una lista con los resultados obtenidos.
	 */
	@Override
	public List<D> validate(T info) {

		List<D> result = new ArrayList<D>();
		
		
		
		for(Validator<D,T> validator: validators) {
			result.addAll(validator.validate(info));
		}
		
		return result;
	}
	
}
