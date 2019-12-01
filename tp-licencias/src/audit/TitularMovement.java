package audit;

import java.time.LocalDate;

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

import builders.Build;
import domain.Titular;
import domain.User;

/**
 * 
 * @author LENOVO
 *
 */

@Entity
@Table(name="TitularMovement")
public class TitularMovement {
	
	public enum Action {
		ALTA,
		MODIFICACION
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_TitularMovement")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column (name="Action")
	private Action action;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user; 
	
	@Column (name="LocalDate")
	private LocalDate dateTime; //GUARDA FECHA Y HORA(hora,min,seg)
	
	@ManyToOne
	@JoinColumn(name = "id_titular")
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
