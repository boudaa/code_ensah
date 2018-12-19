package com.many_to_many_bidirectionelle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class ModuleMMBi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModule;

	private String nomModule;

	private int coff;
///////// La Méthode addModule   très importante/////////
//////////////////////////////////////////////////////////////////
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "modules")
	private List<EtudiantMMBi> etudiant = new ArrayList<EtudiantMMBi>();

	public void addEtudiant(EtudiantMMBi etd) {
		etudiant.add(etd);
		etd.getModules().add(this);
	}
//////////////////////////////////////////////////////////////////

	public ModuleMMBi() {
	}

	public ModuleMMBi(String nomModule, int coff) {
		this.nomModule = nomModule;
		this.coff = coff;
	}

	public Long getIdModule() {
		return idModule;
	}

	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}

	public String getNomModule() {
		return nomModule;
	}

	public void setNomModule(String nomModule) {
		this.nomModule = nomModule;
	}

	public int getCoff() {
		return coff;
	}

	public void setCoff(int coff) {
		this.coff = coff;
	}

	public List<EtudiantMMBi> getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(List<EtudiantMMBi> etudiant) {
		this.etudiant = etudiant;
	}

}
