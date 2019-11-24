package dto;

import java.util.Date;

import domain.LicenseType;

/**
 * Clase tipo POJO utilizada para transferencia de datos de licencia con la interfaz. 
 * Patron data transfer object. 
 * @author Juan Suppicich & Matias Durand
 *
 */
public class LicenseDTO {
	
	private Integer id;
	private LicenseType licenseType;
	private String observation;
	private Date expiryDate;
	private Date emisionDate;
	
	
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
