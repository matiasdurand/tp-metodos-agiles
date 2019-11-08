package domain;

import java.util.Date;

import builders.Build;

public class License {
	
	private Integer id;
	private Titular titular; 
	private LicenseType licenseType;
	private Boolean expirated;
	private Date emisionDate;
	private Date expiricyDate;
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
	public Date getExpiricyDate() {
		return expiricyDate;
	}
	public void setExpiricyDate(Date expiricyDate) {
		this.expiricyDate = expiricyDate;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	public static class Builder implements Build<License>{
		
		private LicenseType licenseType;
		private Boolean expirated;
		private Date emisionDate;
		private Date expiricyDate;
		private String observation;
		
		public Builder() {
			this.expirated = false;
			this.emisionDate = new Date();
		}
		
		public Builder setLicenseType(LicenseType licenseType) {
			this.licenseType = licenseType;
			return this;
		}
		
		public Builder setExpiricyDate(Date expiricyDate) {
			this.expiricyDate = expiricyDate;
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
			license.setExpiricyDate(this.expiricyDate);
			license.setObservation(this.observation);
			return license;
		}
	}
	

}
