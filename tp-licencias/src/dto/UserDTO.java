package dto;

public class UserDTO {

	private Integer idUser;
	private String username;
	private String password;
	private Boolean superUser;
	

	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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
	
}
