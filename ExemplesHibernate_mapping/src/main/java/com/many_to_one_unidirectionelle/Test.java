package com.many_to_one_unidirectionelle;

import java.util.ArrayList;
import java.util.List;

import com.dao.DaoForTest;


public class Test {
	public static void main(String[] args) {

		
		//Test du Cas Many to one unidrectionnelle 
		DaoForTest dao = new DaoForTest();

		// Création de deux objets Etudiant tansitoires
		Etudiant etd = new Etudiant();
		etd.setNom("boudaa");
		etd.setPrenom("Mohamed");

		Module m1 = new Module("Java", 2);
		Module m2 = new Module("C++", 1);
		List<Module> modules = new ArrayList<Module>();
		modules.add(m1);
		modules.add(m2);
		
		etd.setModules(modules);
		
		dao.save(etd);

	}
}
