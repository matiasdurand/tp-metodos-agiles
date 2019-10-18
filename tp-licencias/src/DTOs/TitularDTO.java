package DTOs;

import java.util.Date;

import domain.BloodType;
import domain.TypeId;

public class TitularDTO {

	private TypeId typeId;
	private Long personalId;
	private String name;
	private String surname;
	private String adress;
	private Date birthday;
	private BloodType bloodType;
	private Boolean organDonor;
	
	
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
	
	
}
