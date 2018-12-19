package com.many_to_one_bidirectionelle;

import javax.persistence.*;

@Entity
public class ModuleBi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModule;

	private String nomModule;

	private int coff;
///////////////////////////////////////////////////////////////////////////
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "etudiant_id")
	private EtudiantBi etudiant;
//////////////////////////////////////////////////////////////////////
	public ModuleBi() {
	}

	public ModuleBi(String nomModule, int coff) {
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

	public EtudiantBi getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantBi etudiant) {
		this.etudiant = etudiant;
	}

}
