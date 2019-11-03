package builders;

/**
 * Interfaz que define el metodo Build() el cual sera implementado en los builders 
 * de las distintas clases de dominio. 
 * @author Juan Suppicich & Matias Durand
 * @param <T> objeto a contruir.
 * @see https://refactoring.guru/design-patterns/builder 
 * @see https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
 */
public interface Build<T> {
	
	T build();
	
}
