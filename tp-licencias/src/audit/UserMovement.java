package audit;

import java.time.LocalDate;

import builders.Build;
import domain.User;

/**
 * 
 * @author LENOVO
 *
 */
public class UserMovement {

	private enum Action {
		ALTA,
		MODIFICACION
	}
	
	private int id; 
	private Action action;
	private User user;
	private LocalDate dateTime;
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
	public LocalDate getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDate dateTime) {
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
		private LocalDate dateTime;
		private User userModified;
		
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
