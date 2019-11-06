package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="titular")
public class Titular {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_titular")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column (name="tipo_documento")
	private TypeId typeId;
	
	@Column (name="numero_documento")
	private Long personalId;
	
	@Column (name="nombre")
	private String name;
	
	@Column (name="apellido")
	private String surname;
	
	@Column (name="direccion")
	private String adress;
	
	@Column (name="fecha_nacimiento")
	private Date birthday;
	
	@Enumerated(EnumType.STRING)
	@Column (name="grupo_sanguineo")
	private BloodType bloodType;
	
	
	@Column (name="es_donante")
	private Boolean organDonor;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private int usuarioCreador;
	
	@OneToMany(mappedBy="id_titular",
			cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	private List<License> licenses = new ArrayList<License>(); 
	
	
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public BloodType getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}
	public Boolean getOrganDonor() {
		return organDonor;
	}
	public void setOrganDonor(Boolean organDonor) {
		this.organDonor = organDonor;
	}
	public List<License> getLicenses() {
		return licenses;
	}
	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	} 
	
	public void SetUsuarioCreador(int id) {
		this.usuarioCreador=id;
	}
	
	public int getUsuarioCreador() {
		return this.usuarioCreador;
	}
	
	public void setid (int id) {
		this.id=id;
	}
	
	public int getid() {
		return this.id;
	}
}
