package com.test;

import java.util.List;

import com.bo.Etudiant;
import com.dao.HibernateEtudiantDaoImpl;

public class TestProgram {
	public static void main(String[] args) {

		HibernateEtudiantDaoImpl dao = new HibernateEtudiantDaoImpl();

		// Création de deux objets Etudiant tansitoires
		Etudiant etd1 = new Etudiant();
		etd1.setNom("boudaa");
		etd1.setPrenom("Mohamed");

		Etudiant etd2 = new Etudiant();
		etd2.setNom("boudaa");
		etd2.setPrenom("Tarik");

		// enregister dans la base de données les deux objets (les rendre persistants)
		dao.save(etd1);
		dao.save(etd2);

		System.out.println("Etudiant sauvegardé, son id est " + etd1.getId());
		System.out.println("Etudiant sauvegardé, son id est " + etd2.getId());

		// Récupérer un étudiant par son identifiant de la base de données
		Etudiant etd3 = dao.findById(Long.valueOf(etd1.getId()));
		System.out.println("Etudiant bien récupé de la base de données " + etd3);

		// On affiche la liste des étudiants en base de données aynt le nom boudaa
		List<Etudiant> list = dao.finByName("boudaa");

		System.out.println("La liste des étudiants  ayant le nom boudaa");
		for (Etudiant it : list) {
			System.out.println(it);
		}

		// Supprimer un étudiant
		dao.delete(etd3.getId());

		// afficher à nouveau tous les enregistrement en base
		System.out.println("Les étudiants enregistrés en base de données après suppression : ");
		list = dao.findAll();
		for (Etudiant it : list) {
			System.out.println(it);
		}

		// Méttre à jour le nom d'un étudiant
		etd2.setNom("Alami");
		// attacher à la session pour que les mises à jours prennent effet
		dao.update(etd2);

		// afficher à nouveau tous les enregistrement en base
		System.out.println("Les étudiants enregistrés en base de données : ");
		list = dao.findAll();
		for (Etudiant it : list) {
			System.out.println(it);
		}

	}
}
