package domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="licencia")
public class License {
	
	
	@ManyToOne
	@JoinColumn(name = "id_titular")
	private Titular titular;
	
	@Enumerated(EnumType.STRING)
	@Column (name="clase")
	private LicenseType licenseType;
	
	@Transient
	private Boolean expirated;
	
	@Column (name="fecha_emision")
	private Date emisionDate;
	
	@Column (name="fecha_vencimiento")
	private Date expiricyDate;
	
	@Transient
	private Integer validity;
	
	@Column (name="observaciones")
	private String observation;
	
	@Column (name="motivo_emision")
	private String emmisionMotive;
	
	
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
	public String getEmmisionMotive() {
		return emmisionMotive;
	}
	public void setEmmisionMotive(String emmisionMotive) {
		this.emmisionMotive = emmisionMotive;
	}
	

}
