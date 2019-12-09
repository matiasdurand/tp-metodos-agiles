package audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import builders.Build;
import domain.License;
import domain.User;

/**
 * Esta clase representa un item de historial de licencia. 
 * @author LENOVO
 *
 */
@Entity
@Table(name="LicenseMovement")
public class LicenseMovement {

	public enum Action {
		ALTA,
		RENOVACION,
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_licensemovement")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column (name="Action")
	private Action action;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user; 
	
	@Column (name="LocalDate")
	private Date dateTime;

	@OneToOne
	@JoinColumn(name = "id_licencia")
	private License license;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	/**
	 * 
	 * @author LENOVO
	 *
	 */
	public static class Builder implements Build<LicenseMovement> {
		
		private Action action;
		private User user; 
		private Date dateTime;
		private License license;
		
		public Builder() {
			this.dateTime = new Date();
		}
		
		public Builder setAction(Action action) {
			this.action = action;
			return this;
		}
		public Builder setUser(User user) {
			this.user = user;
			return this;
		}
		public Builder setLicense(License license) {
			this.license = license;
			return this;
		}
		@Override
		public LicenseMovement build() {
			LicenseMovement licenseMovement = new LicenseMovement();
			licenseMovement.setAction(this.action);
			licenseMovement.setUser(this.user);
			licenseMovement.setDateTime(this.dateTime);
			licenseMovement.setLicense(this.license);
			return licenseMovement;
		}
		
		
	}
	

}
