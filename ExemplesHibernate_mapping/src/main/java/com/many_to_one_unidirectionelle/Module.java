package com.many_to_one_unidirectionelle;

import javax.persistence.*;

@Entity
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModule;

	private String nomModule;

	private int coff;
	
	public Module() {
	}

	public Module( String nomModule, int coff) {
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

}
