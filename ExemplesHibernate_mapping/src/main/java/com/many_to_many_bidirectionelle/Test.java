package com.many_to_many_bidirectionelle;



import com.dao.DaoForTest;


public class Test {



	public static void main(String[] args) {

		DaoForTest dao = new DaoForTest();

		// Création de deux objets Etudiant tansitoires
		EtudiantMMBi etd = new EtudiantMMBi();
		etd.setNom("boudaa");
		etd.setPrenom("Mohamed");

		ModuleMMBi m1 = new ModuleMMBi("Java", 2);
		ModuleMMBi m2 = new ModuleMMBi("C++", 1);
		
		
		etd.addModule(m1);
		etd.addModule(m2);


		dao.save(etd);

	}
}
