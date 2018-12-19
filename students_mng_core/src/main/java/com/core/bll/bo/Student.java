package com.core.bll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStudent;

	private String firstName;

	private String lastName;

	@Column(nullable = false, unique = true)
	private String cne;

	private String email;

	public Student() {
	}

	public Student(String firstName, String lastName, String email, String pCne) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		cne = pCne;
	}

	public Long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}

}
