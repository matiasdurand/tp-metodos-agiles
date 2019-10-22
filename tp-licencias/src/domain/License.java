package domain;

import java.util.Date;

public class License {
	
	private Integer id;
	private Titular titular; 
	private LicenseType licenseType;
	private Boolean expirated;
	private Date emisionDate;
	private Date expiricyDate;
	private Integer validity;
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
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	

}
