package com.many_to_many_bidirectionelle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.many_to_one_unidirectionelle.Module;

@Entity
public class EtudiantMMBi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEtudiant; // Obligatoire d'avoir un identifiant de type Objet
	// non pas long int , .... pluto Long /Integrer

	private String nom;

	private String prenom;
	
///////// La Méthode addModule   très importante/////////
//////////////////////////////////////////////////////////////////////
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Etudiant_Module", joinColumns = {
			@JoinColumn(name = "Etudiant_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "MODULE_ID", nullable = false, updatable = false) })
	private List<ModuleMMBi> modules = new ArrayList<ModuleMMBi>();

	public void addModule(ModuleMMBi pModule) {
		modules.add(pModule);
		pModule.getEtudiant().add(this);
	}
/////////////////////////////////////////////////////////////////////

	public String toString() {
		return "Etudiant [id=" + idEtudiant + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	public Long getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public List<ModuleMMBi> getModules() {
		return modules;
	}

	public void setModules(List<ModuleMMBi> modules) {
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
