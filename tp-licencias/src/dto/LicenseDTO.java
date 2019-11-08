package dto;

import java.util.Date;

import domain.LicenseType;

public class LicenseDTO {
	
	private Integer id;
	private LicenseType licenseType;
	private String observation;
	private Date expiricyDate;
	
	
	public Date getExpiricyDate() {
		return expiricyDate;
	}
	public void setExpiricyDate(Date expiricyDate) {
		this.expiricyDate = expiricyDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LicenseType getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
}
