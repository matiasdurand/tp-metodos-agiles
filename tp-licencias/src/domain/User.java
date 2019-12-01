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
	
	@Column (name="es_root")
	private Boolean superUser;
	
	@Enumerated(EnumType.STRING)
	@Column (name="tipo_documento")
	private TypeId typeId;
	
	@Column (name="numero_documento")
	private Long personalId;
	
	@Column (name="nombre")
	private String name;

	@Column (name="apellido")
	private String surname;
	
	
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
	
	public Boolean getSuperUser() {
		return superUser;
	}

	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}

	public TypeId getTypeId() {
		return typeId;
	}
	
	public void setTypeId(TypeId typeId) {
		this.typeId = typeId;
	}
	
	public Long getPersonalId() {
		return personalId;
	}
	
	public void setPersonalId(Long personalId) {
		this.personalId = personalId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Clase estatica constructora de Usuario. 
	 * @author Juan Suppicich & Matias Durand
	 */
	public static class Builder implements Build<User> {

		private String username;
		private String password;
		private Boolean superUser;
		private TypeId typeId;
		private Long personalId;
		private String name;
		private String surname; 
		
		
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

		public Builder setTypeId(TypeId typeId) {
			this.typeId = typeId;
			return this;
		}
		
		public Builder setPersonalId(Long personalId) {
			this.personalId = personalId;
			return this;
		}
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setSurname(String surname) { 
			this.surname = surname;
			return this;
		}

		@Override
		public User build() {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setSuperUser(superUser);
			user.setTypeId(typeId);
			user.setPersonalId(personalId);
			user.setName(name);
			user.setSurname(surname);
			return user;
		}
		
	}
	
}
