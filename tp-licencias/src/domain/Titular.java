package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import builders.Build;

public class Titular {
	
	private Integer id;
	private TypeId typeId;
	private Long personalId;
	private String name;
	private String surname;
	private String adress;
	private Date birthdate;
	private String bloodType;
	private Boolean organDonor;
	private List<License> licenses = new ArrayList<License>(); 
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
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
	
	public static class Builder implements Build<Titular>{

		private TypeId typeId;
		private Long personalId;
		private String name;
		private String surname;
		private String adress;
		private Date birthdate;
		private String bloodType;
		private Boolean organDonor;
		private List<License> licenses = new ArrayList<License>(); 
		
		
		public Builder() {
			
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
		
		public Builder setAdress(String adress) {
			this.adress = adress;
			return this;
		}
		
		public Builder setBirthdate(Date birthdate) {
			this.birthdate = birthdate;
			return this;
		}
		
		public Builder setBloodType(String bloodType) { 
			this.bloodType = bloodType;
			return this;
		}
		
		public Builder setOrganDonor(Boolean organDonor) {
			this.organDonor = organDonor;
			return this;
		}
		
		public Builder addLicense(License license) {
			this.licenses.add(license);
			return this;
		}

		@Override
		public Titular build() {
			Titular titular = new Titular();
			titular.setTypeId(this.typeId);
			titular.setPersonalId(this.personalId);
			titular.setName(this.name);
			titular.setSurname(this.surname);
			titular.setAdress(this.adress);
			titular.setBirthdate(this.birthdate);
			titular.setBloodType(this.bloodType);
			titular.setOrganDonor(this.organDonor);
			return titular;
		}
		
	}
	
}
