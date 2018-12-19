package com.many_to_one_bidirectionelle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.many_to_one_unidirectionelle.Module;

@Entity
public class EtudiantBi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEtudiant; // Obligatoire d'avoir un identifiant de type Objet
	// non pas long int , .... pluto Long /Integrer

	private String nom;

	private String prenom;

///////// Les Méthodes addModule et removeModule  très importantes/////////
	@OneToMany(mappedBy = "etudiant", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("nomModule asc")
	private List<ModuleBi> modules = new ArrayList<ModuleBi>();

	public void addModule(ModuleBi pModule) {
		modules.add(pModule);
		pModule.setEtudiant(this);
	}

	public void removeModule(ModuleBi pModule) {
		modules.remove(pModule);
		pModule.setEtudiant(null);
	}

////////////////////////////////////////////////////////
	public String toString() {
		return "Etudiant [id=" + idEtudiant + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	public Long getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public List<ModuleBi> getModules() {
		return modules;
	}

	public void setModules(List<ModuleBi> modules) {
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
