package domain;

import java.util.Date;

import builders.Build;

/**
 * Clase de dominio Licencia.
 * @author Juan Suppicich & Matias Durand
 *
 */
public class License {
	
	private Integer id;
	private Titular titular; 
	private LicenseType licenseType;
	private Boolean expirated;
	private Date emisionDate;
	private Date expiryDate;
	private String observation;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Titular getTitular() {
		return titular;
	}
	public void setTitular(Titular titular) {
		this.titular = titular;
	}
	public LicenseType getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}
	public Boolean getExpirated() {
		return expirated;
	}
	public void setExpirated(Boolean expirated) {
		this.expirated = expirated;
	}
	public Date getEmisionDate() {
		return emisionDate;
	}
	public void setEmisionDate(Date emisionDate) {
		this.emisionDate = emisionDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	/**
	 * Clase estatica constructora de licencia.
	 * @author Juan Suppicich & Matias Durand
	 * Por defecto contruye una licencia con atributo expirated = false
	 * y emisionDate = fecha actual.
	 */
	public static class Builder implements Build<License>{
		
		private LicenseType licenseType;
		private Boolean expirated;
		private Date emisionDate;
		private Date expiryDate;
		private String observation;
		
		public Builder() {
			this.expirated = false;
			this.emisionDate = new Date();
		}
		
		public Builder setLicenseType(LicenseType licenseType) {
			this.licenseType = licenseType;
			return this;
		}
		
		public Builder setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
			return this;
		}
		
		public Builder setObservation(String observation) {
			this.observation = observation;
			return this;
		}
		
		@Override
		public License build() {
			License license = new License();
			license.setLicenseType(this.licenseType);
			license.setExpirated(this.expirated);
			license.setEmisionDate(this.emisionDate);
			license.setExpiryDate(this.expiryDate);
			license.setObservation(this.observation);
			return license;
		}
	}
	

}
