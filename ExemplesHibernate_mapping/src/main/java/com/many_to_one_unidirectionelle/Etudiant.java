package com.many_to_one_unidirectionelle;

import java.util.List;

import javax.persistence.*;

@Entity
public class Etudiant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEtudiant; // Obligatoire d'avoir un identifiant de type Objet
	// non pas long int , .... pluto Long /Integrer

	private String nom;

	private String prenom;
///////////////////////////////////////////////////////////////////////////
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "etudiant_id")
	@OrderBy("nomModule asc")
	private List<Module> modules;
///////////////////////////////////////////////////////////////////////////
	public String toString() {
		return "Etudiant [id=" + idEtudiant + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	public Long getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
