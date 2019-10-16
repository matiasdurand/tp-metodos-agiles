package validators;

import java.util.ArrayList;
import java.util.List;

public class CompositeValidator<T> implements Validator <T> {
	
	private final List<Validator<T>> validators; 
	
	public CompositeValidator(List<Validator<T>> validators) {
		this.validators = validators; 
	}
	
	@Override
	public List<String> validate(T info){
		
		List<String> errors = new ArrayList<String>();
		
		for(Validator<T> validator: validators) {
			errors.addAll(validator.validate(info));
		}
		
		return errors;
	}
}
