package audit;

import java.time.LocalDate;

import builders.Build;
import domain.License;
import domain.User;

/**
 * Esta clase representa un item de historial de licencia. 
 * @author LENOVO
 *
 */
public class LicenseMovement {

	private enum Action {
		ALTA,
		RENOVACION,
	}
	
	private int id;
	private Action action;
	private User user; 
	private LocalDate dateTime; //GUARDA FECHA Y HORA(hora,min,seg)
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
	public LocalDate getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDate dateTime) {
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
		private LocalDate dateTime;
		private License license;
		
		public Builder() {
			this.dateTime = LocalDate.now();
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
