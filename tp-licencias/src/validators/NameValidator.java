package validators;

import java.util.ArrayList;
import java.util.List;

public class NameValidator<T> implements Validator<T> {

	@Override
	public List<String> validate(T info) {
		List<String> errors = new ArrayList<String>();
		
		String name = info.getName();
		String surname = info.getSurname();
		
		if(name.isEmpty()) {
			errors.add("Debe completar el nombre del titular.");
		}
		
		if(surname.isEmpty()) {
			errors.add("Debe completar el apellido del titular.");
		}
		
		return errors;
	}
	
}
