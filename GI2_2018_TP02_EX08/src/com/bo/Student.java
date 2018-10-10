package com.bo;

import java.util.ArrayList;
import java.util.List;

import com.exceptions.ObjectNotFoundException;

/**
 * Represente un étudiant
 * 
 * @author Boudaa
 *
 */
public class Student {

	private String nom;
	private String prenom;
	private int age;
	private String cin;
	
	public Contact contact;
	public List<Loan> loans = new ArrayList<Loan>();

	public Student() {

	}

	public Student(String nom, String prenom, int age, String cin,
			Contact contact) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.cin = cin;
		this.contact = contact;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (cin == null) {
			if (other.cin != null)
				return false;
		} else if (!cin.equals(other.cin))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	public void showDetails() {
		System.out.println("Information de l'étudiant [nom=" + nom
				+ ", prenom=" + prenom + ", age=" + age + ", cin=" + cin);

		System.out.println("Contact : ");
		System.out.println(contact);
	}

	public void addLoan(Loan pLoan) {

		loans.add(pLoan);

	}

	public Loan findLoanById(int id) {

		for (Loan it : loans) {

			if (it.getId() == id)
				return it;
		}
		return null;
	}

	// version améliorée

	public Loan findLoanById2(int id) throws ObjectNotFoundException {

		Loan searchedLoan = null;
		for (Loan it : loans) {

			if (it.getId() == id) {
				searchedLoan = it;
				break;

			}
		}

		if (searchedLoan == null)
			throw new ObjectNotFoundException();

		return searchedLoan;
	}

	public void deleteLoan(int id) {
		Loan lLoan = findLoanById(id);
		if (lLoan != null) {
			loans.remove(lLoan);
		}

	}

	// version améliorée
	public void deleteLoan2(int id) throws ObjectNotFoundException {
		Loan lLoan = findLoanById2(id);
		if (lLoan != null) {
			loans.remove(lLoan);
		}

	}

}
