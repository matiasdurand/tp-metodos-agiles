package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import builders.Build;

@Entity
@Table(name="usuario")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;

	@Column (name="nombre_usuario")
	private String username;

	@Column (name="password")
	private String password;
	
	private Boolean superUser;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getsuperUser() {
		return superUser;
	}
	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}
	
	public static class Builder implements Build<User>{

		private String username;
		private String password;
		private Boolean superUser;
		
		
		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public Builder setSuperUser(Boolean superUser) {
			this.superUser = superUser;
			return this;
		}

		@Override
		public User build() {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setSuperUser(superUser);
			return user;
		}
		
	}

}
