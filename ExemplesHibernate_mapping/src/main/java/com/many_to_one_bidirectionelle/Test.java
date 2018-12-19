package com.many_to_one_bidirectionelle;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.DaoForTest;
import com.dao.SessionFactoryBuilder;
import com.many_to_one_unidirectionelle.Etudiant;

public class Test {



	public static void main(String[] args) {

		//Test du Cas Many to one bidirectionnelle 

		DaoForTest dao = new DaoForTest();

		// Création de deux objets Etudiant tansitoires
		EtudiantBi etd = new EtudiantBi();
		etd.setNom("boudaa");
		etd.setPrenom("Mohamed");

		ModuleBi m1 = new ModuleBi("Java", 2);
		ModuleBi m2 = new ModuleBi("C++", 1);
		
		
		etd.addModule(m1);
		etd.addModule(m2);


		dao.save(etd);

	}
}
