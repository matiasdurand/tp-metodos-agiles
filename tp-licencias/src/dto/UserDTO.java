package dto;

import domain.TypeId;

public class UserDTO {

	private Integer id;
	private String username;
	private String password;
	private Boolean superUser;
	private TypeId typeId;
	private String personalId;
	private String name;
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
	
	public String getPersonalId() {
		return personalId;
	}
	
	public void setPersonalId(String personalId) {
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
	
}
