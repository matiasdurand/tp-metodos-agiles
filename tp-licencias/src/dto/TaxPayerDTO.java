package dto;

/**
 * Clase tipo POJO utilizada para transferencia de datos de contribuyente con la interfaz. 
 * Patron data transfer object. 
 * @author Juan Suppicich & Matias Durand
 *
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import domain.TypeId;

@Entity
@Table(name="contribuyente")
public class TaxPayerDTO {
	@Id
	@Column (name="nro_documento")
	private Long personalId;

	@Enumerated(EnumType.STRING)
	@Column (name="tipo_documento")
	private TypeId typeId;
	
	@Column (name="nombre")
	private String name; 
	
	@Column (name="apellido")
	private String surname;
	
	@Column (name="fecha_nacimiento")
	private Date birthdate;
	
	@Column (name="direccion")
	private String adress;
	

	public Long getPersonalId() {
		return personalId;
	}
	public void setPersonalId(Long personalId) {
		this.personalId = personalId;
	}
	public TypeId getTypeId() {
		return typeId;
	}
	public void setTypeId(TypeId typeId) {
		this.typeId = typeId;
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
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
}
