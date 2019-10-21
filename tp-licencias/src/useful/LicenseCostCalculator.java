package useful;

import java.util.HashMap;
import java.util.Map;

public class LicenseCostCalculator {

	private static LicenseCostCalculator _INSTANCE = new LicenseCostCalculator();
	private Map<String[], Double> licenseCosts = new HashMap<String[], Double>();
	
	
	private LicenseCostCalculator() {
		loadLicenseCosts();
	}
	
	public static LicenseCostCalculator getInstance() {
		return _INSTANCE;
	}
	
	public Double getLicenseCost(String[] key) {
		return licenseCosts.get(key);
	}
	
	private void loadLicenseCosts() {
		String[] key = new String[2]; 
		
		key[0]="A"; 
		key[1]="1"; licenseCosts.put(key, 20.00);
		key[1]="3"; licenseCosts.put(key, 25.00);
		key[1]="4"; licenseCosts.put(key, 30.00);
		key[1]="5"; licenseCosts.put(key, 40.00);
		
		key[0]="B"; 
		key[1]="1"; licenseCosts.put(key, 20.00);
		key[1]="3"; licenseCosts.put(key, 25.00);
		key[1]="4"; licenseCosts.put(key, 30.00);
		key[1]="5"; licenseCosts.put(key, 40.00);
		
		key[0]="C"; 
		key[1]="1"; licenseCosts.put(key, 23.00);
		key[1]="3"; licenseCosts.put(key, 30.00);
		key[1]="4"; licenseCosts.put(key, 35.00);
		key[1]="5"; licenseCosts.put(key, 47.00);
		
		key[0]="D"; 
		key[1]="1"; licenseCosts.put(key, 29.00);
		key[1]="3"; licenseCosts.put(key, 39.00);
		key[1]="4"; licenseCosts.put(key, 44.00);
		key[1]="5"; licenseCosts.put(key, 59.00);
		
		key[0]="E"; 
		key[1]="1"; licenseCosts.put(key, 29.00);
		key[1]="3"; licenseCosts.put(key, 39.00);
		key[1]="4"; licenseCosts.put(key, 44.00);
		key[1]="5"; licenseCosts.put(key, 59.00);
		
		key[0]="F"; 
		key[1]="1"; licenseCosts.put(key, 20.00);
		key[1]="3"; licenseCosts.put(key, 25.00);
		key[1]="4"; licenseCosts.put(key, 30.00);
		key[1]="5"; licenseCosts.put(key, 40.00);
		
		key[0]="G"; 
		key[1]="1"; licenseCosts.put(key, 20.00);
		key[1]="3"; licenseCosts.put(key, 25.00);
		key[1]="4"; licenseCosts.put(key, 30.00);
		key[1]="5"; licenseCosts.put(key, 40.00);
	}
	
}
