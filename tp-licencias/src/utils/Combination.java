package utils;

import domain.LicenseType;

public class Combination {

	private LicenseType licenseType;
	private Integer validity;
	
	
	public Combination() {
		
	}
	
	public Combination(LicenseType licenseType, Integer validity) {
		this.licenseType = licenseType;
		this.validity = validity;
	}
	
	public LicenseType getLicenseType() {
		return licenseType;
	}
	
	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}
	
	public Integer getValidity() {
		return validity;
	}
	
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((licenseType == null) ? 0 : licenseType.hashCode());
		result = prime * result + ((validity == null) ? 0 : validity.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Combination other = (Combination) obj;
		if (licenseType != other.licenseType)
			return false;
		if (validity == null) {
			if (other.validity != null)
				return false;
		} else if (!validity.equals(other.validity))
			return false;
		return true;
	}
	
}
