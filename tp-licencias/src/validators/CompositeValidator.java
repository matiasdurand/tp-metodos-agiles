package validators;

import java.util.ArrayList;
import java.util.List;

public class CompositeValidator<D,T> implements Validator <D,T> {
	
	private final List<Validator<D,T>> validators; 
	
	public CompositeValidator(List<Validator<D,T>> validators) {
		this.validators = validators; 
	}

	@Override
	public List<D> validate(T info) {

		List<D> result = new ArrayList<D>();
		
		for(Validator<D,T> validator: validators) {
			result.addAll(validator.validate(info));
		}
		
		return result;
	}
	
	
}
