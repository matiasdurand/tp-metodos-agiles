package domain;

import java.util.Date;

import builders.Build;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase de dominio Licencia. Corresponde a una entidad de base de datos.
 * @author Juan Suppicich & Matias Durand
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
	
	@Override
	public String toString() {
		return "License [id=" + id + ", titular=" + titular + ", licenseType=" + licenseType + ", emisionDate="
				+ emisionDate + ", expiryDate=" + expiryDate + ", observation=" + observation + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		License other = (License) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	/**
	 * Clase estatica constructora de licencia.
	 * @author Juan Suppicich & Matias Durand
	 * Por defecto contruye una licencia con atributo expirated = false
	 * y emisionDate = fecha actual.
	 */
	public static class Builder implements Build<License> {
		
		private LicenseType licenseType;
		private Date emisionDate;
		private Date expiryDate;
		private String observation;
		
		public Builder() {
			this.emisionDate = new Date();
			
			//PARA PROBAR LA CARGA DE LICENCIAS PROFESIONALES EN EL COMBOBOX
			/*LocalDate ld = LocalDate.of(2017, 8, 2);
			this.emisionDate = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());*/
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
