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
import javax.persistence.Table;

import builders.Build;
import domain.User;

/**
 * 
 * @author LENOVO
 *
 */

@Entity
@Table(name="UserMovement")
public class UserMovement {

	public enum Action {
		ALTA,
		MODIFICACION
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_UserMovement")
	private int id; 
	
	@Enumerated(EnumType.STRING)
	@Column (name="Action")
	private Action action;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@Column (name="LocalDate")
	private Date dateTime;
	
	@ManyToOne
	@JoinColumn(name = "id_userModified")
	private User userModified;
	
	
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
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public User getUserModified() {
		return userModified;
	}
	public void setUserModified(User userModified) {
		this.userModified = userModified;
	}

	/**
	 * 
	 * @author LENOVO
	 *
	 */
	public static class Builder implements Build<UserMovement> {
		
		private Action action;
		private User user;
		private Date dateTime;
		private User userModified;
		
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
		public Builder setUserModified(User userModified) {
			this.userModified = userModified;
			return this;
		}
		@Override
		public UserMovement build() {
			UserMovement userMovement = new UserMovement();
			userMovement.setAction(this.action);
			userMovement.setUser(this.user);
			userMovement.setDateTime(this.dateTime);
			userMovement.setUserModified(this.userModified);
			return userMovement;
		}
		
	}
	
	
	
	
}
