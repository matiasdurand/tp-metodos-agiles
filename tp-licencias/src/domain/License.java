package domain;

import java.util.Date;

import builders.Build;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase de dominio Licencia. Corresponde a una entidad de base de datos.
 * @author Juan Suppicich & Matias Durand
 *
 */
@Entity
@Table(name="licencia")
public class License {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_licencia")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_titular")
	private Titular titular;
	
	@Enumerated(EnumType.STRING)
	@Column (name="clase")
	private LicenseType licenseType;

	@Column (name="fecha_emision")
	private Date emisionDate;
	
	@Column (name="fecha_vencimiento")
	private Date expiryDate;

	@Column (name="observaciones")
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
	/*public String getEmmisionMotive() {
		return emmisionMotive;
	}
	public void setEmmisionMotive(String emmisionMotive) {
		this.emmisionMotive = emmisionMotive;
	}*/
	
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
			license.setEmisionDate(this.emisionDate);
			license.setExpiryDate(this.expiryDate);
			license.setObservation(this.observation);
			return license;
		}
	}
	

}
