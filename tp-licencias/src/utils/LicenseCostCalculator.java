package utils;

import java.util.HashMap;
import java.util.Map;

import domain.LicenseType;

/**
 * Clase calculadora de costo de licencia. Singleton. 
 * @author Suppicich Juan & Matias Durand
 *
 */
public class LicenseCostCalculator {

	private static LicenseCostCalculator _INSTANCE = new LicenseCostCalculator();
	private Map<Combination, Double> licenseCosts = new HashMap<Combination, Double>();
	
	
	private LicenseCostCalculator() {
		loadLicenseCosts();
	}
	
	public static LicenseCostCalculator getInstance() {
		return _INSTANCE;
	}
	
	/**
	 * Retorna el costo de la licencia para la combinacion recibida 
	 * como parametro.
	 * @param combination
	 * @return el costo de la licencia.
	 */
	public Double getLicenseCost(Combination combination) {
		return licenseCosts.get(combination);
	}
	
	/**
	 * Carha el HashMap asocionado a cada combinacion su costo.
	 */
	private void loadLicenseCosts() {
	
		licenseCosts.put(new Combination(LicenseType.A, 1), 20.00);
		licenseCosts.put(new Combination(LicenseType.A, 3), 25.00);
		licenseCosts.put(new Combination(LicenseType.A, 4), 30.00);
		licenseCosts.put(new Combination(LicenseType.A, 5), 40.00);
		
		licenseCosts.put(new Combination(LicenseType.B, 1), 20.00);
		licenseCosts.put(new Combination(LicenseType.B, 3), 25.00);
		licenseCosts.put(new Combination(LicenseType.B, 4), 30.00);
		licenseCosts.put(new Combination(LicenseType.B, 5), 40.00);
		
		licenseCosts.put(new Combination(LicenseType.C, 1), 23.00);
		licenseCosts.put(new Combination(LicenseType.C, 3), 30.00);
		licenseCosts.put(new Combination(LicenseType.C, 4), 35.00);
		licenseCosts.put(new Combination(LicenseType.C, 5), 47.00);
		
		licenseCosts.put(new Combination(LicenseType.D, 1), 29.00);
		licenseCosts.put(new Combination(LicenseType.D, 3), 39.00);
		licenseCosts.put(new Combination(LicenseType.D, 4), 44.00);
		licenseCosts.put(new Combination(LicenseType.D, 5), 59.00);
		
		licenseCosts.put(new Combination(LicenseType.E, 1), 29.00);
		licenseCosts.put(new Combination(LicenseType.E, 3), 39.00);
		licenseCosts.put(new Combination(LicenseType.E, 4), 44.00);
		licenseCosts.put(new Combination(LicenseType.E, 5), 59.00);
		
		licenseCosts.put(new Combination(LicenseType.F, 1), 20.00);
		licenseCosts.put(new Combination(LicenseType.F, 3), 25.00);
		licenseCosts.put(new Combination(LicenseType.F, 4), 30.00);
		licenseCosts.put(new Combination(LicenseType.F, 5), 40.00);
		
		licenseCosts.put(new Combination(LicenseType.G, 1), 20.00);
		licenseCosts.put(new Combination(LicenseType.G, 3), 25.00);
		licenseCosts.put(new Combination(LicenseType.G, 4), 30.00);
		licenseCosts.put(new Combination(LicenseType.G, 5), 40.00);

	}
	
}
