package com.bank.gui;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import com.bank.core.bll.CompteManger;
import com.bank.core.bll.CompteOperationException;
import com.bank.core.bll.ManagerFactory;
import com.bank.core.bll.bo.Client;
import com.bank.core.bll.bo.Compte;
import com.bank.core.dao.DataAccessLayerException;
import com.bank.core.dao.ObjectNotFoundException;
import com.utils.DateUtils;

/**
 * La partie HIM de l'application
 * 
 * @author boudaa
 *
 */
public class ConsoleApp {

	public static int mainMenu() {

		System.out.println("--------APPLICATION GESTION COMPTE----------");

		System.out.println("1- Créer un compte  ");
		System.out.println("2- Débiter un compte ");
		System.out.println("3- Créditer un compte ");
		System.out.println("4- Virement ");
		System.out.println("5- Afficher un compte");
		System.out.println("6- Afficher tous les comptes");
		System.out.println("7- Quitter");
		System.out.println("Choisir une option ");

		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();

		return s;

	}

	public static void main(String[] args) {

		CompteManger compteManager = (CompteManger) ManagerFactory.getInstance()
				.getManager("com.bank.core.bll.CompteManagerImpl");

		do {
			Scanner sc = new Scanner(System.in);

			int choice = mainMenu();

			switch (choice) {
			case 1:
				Client p = new Client();
				System.out.println("Entrer le nom de la personne");
				p.setNom(sc.nextLine());
				System.out.println("Entrer le prénom de la personne");
				p.setPrenom(sc.nextLine());

				System.out.println("Entrer CIN ");
				p.setCin(sc.nextLine());

				System.out.println("Entrer Adresse ");
				p.setAdresse(sc.nextLine());

				System.out.println("Date naissance au format jj/mm/aaaa");

				boolean ok = false;

				do {
					try {
						p.setDateNaissance(DateUtils.convertStringToDate("dd/MM/yyyy", sc.nextLine()));
						ok = true;
					} catch (ParseException e1) {

						System.out.println("La date naissance au format jj/mm/aaaa");
					}
				} while (!ok);

				Compte newCompte;
				try {
					newCompte = compteManager.createCompte(p);
					System.out.println("Compte crée avec succés");
					System.out.println(newCompte);
				} catch (DataAccessLayerException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}

				break;

			case 2:
				Compte compte = null;
				try {
					System.out.println("Entrer le numéro de compte");
					compte = compteManager.getCompteByNum(sc.nextLong());
					System.out.println("Entrer le montant de débit");

					compteManager.debiter(compte, sc.nextDouble());

				} catch (ObjectNotFoundException ex) {

					System.err.println("le compte indiqué n'existe pas");
				}

				catch (CompteOperationException e) {

					System.err.println(e.getMessage());

				} catch (DataAccessLayerException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}

				break;

			case 3:
				System.out.println("Entrer le numéro de compte");
				Compte compte2 = null;
				try {
					compte2 = compteManager.getCompteByNum(sc.nextLong());
					System.out.println(compte2);
					System.out.println("Entrer le montant de crédit");
					compteManager.crediter(compte2, sc.nextDouble());

				} catch (ObjectNotFoundException ex) {

					System.err.println("le compte indiqué n'existe pas");

				} catch (DataAccessLayerException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}
				break;

			case 4:
				try {
					System.out.println("Entrer le numéro de compte à débiter ");
					Compte c1 = compteManager.getCompteByNum(sc.nextLong());

					System.out.println("Entrer le numéro de compte à cr�diter ");
					Compte c2 = compteManager.getCompteByNum(sc.nextLong());

					System.out.println("Entrer le montant de virement");
					compteManager.virement(c1, c2, sc.nextDouble());
				} catch (ObjectNotFoundException ex) {

					System.err.println("le compte indiqué n'existe pas");
				}

				catch (CompteOperationException e) {

					System.err.println(e.getMessage());

				} catch (DataAccessLayerException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}
				break;

			case 5:

				System.out.println("Entrer le numéro de compte à afficher ");
				try {
					System.out.println(compteManager.getCompteByNum(sc.nextLong()).toString());
				} catch (ObjectNotFoundException ex) {

					System.err.println("le compte indiqué n'existe pas");
				}

				catch (DataAccessLayerException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}

				break;

			case 6:

				try {
					List<Compte> listComptes = compteManager.showBanque();

					for (Compte it : listComptes) {

						System.out.println(it);
					}

				} catch (DataAccessLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case 7:

				System.exit(0);

			default:
				System.out.println("veuillez choisir une option de 1 à 7");
				break;
			}

		} while (true);
	}

}
