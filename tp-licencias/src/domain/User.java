package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class User {
	
	@Column (name="nombre_usuario")
	private String username;

	@Column (name="password")
	private String password;
	
	
	
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



	

}
