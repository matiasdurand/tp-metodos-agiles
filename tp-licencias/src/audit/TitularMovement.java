package audit;

import java.time.LocalDate;

import builders.Build;
import domain.Titular;
import domain.User;

/**
 * 
 * @author LENOVO
 *
 */
public class TitularMovement {
	
	private enum Action {
		ALTA,
		MODIFICACION
	}
	
	private int id;
	private Action action;
	private User user; 
	private LocalDate dateTime; //GUARDA FECHA Y HORA(hora,min,seg)
	private Titular titular;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
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
	public Titular getTitular() {
		return titular;
	}
	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	/**
	 * 
	 * @author LENOVO
	 *
	 */
	public static class Builder implements Build<TitularMovement> {
		
		private Action action;
		private User user; 
		private LocalDate dateTime; 
		private Titular titular;
		
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
		public Builder setTitular(Titular titular) {
			this.titular = titular;
			return this;
		}
		@Override
		public TitularMovement build() {
			TitularMovement titularMovement = new TitularMovement();
			titularMovement.setAction(this.action);
			titularMovement.setDateTime(this.dateTime);
			titularMovement.setUser(this.user);
			titularMovement.setTitular(this.titular);
			return titularMovement;
		}
		
		
	}

}
