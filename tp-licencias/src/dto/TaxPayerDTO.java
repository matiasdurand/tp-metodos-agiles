package dto;

import java.util.Date;

/**
 * Clase tipo POJO utilizada para transferencia de datos de contribuyente con la interfaz. 
 * Patron data transfer object. 
 * @author Juan Suppicich & Matias Durand
 *
 */
public class TaxPayerDTO {
	
	private String name; 
	private String surname;
	private Date birthday;
	private String adress;
	
	
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
}
