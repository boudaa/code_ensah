package com.ensah;

import java.util.Random;

/**
 * Solution de l'exercice 01, dont lequel il est demandé d'implémenter deux
 * algorithmes simples pour générer une chaine de caractères aléatoire
 * 
 * @author boudaa
 *
 */
public class PasswordGeneration {

	/**
	 * Génère une chaine aléatoire
	 * 
	 * @param n
	 *            : nombre de caractères de la chaine
	 * @return la chaine aléatoire
	 */
	public static String generatePassword(int n) {

		// ici on considère que nous voulons avoir des acaractères ayant un code dans
		// l'intervalle [33 ,126[

		// tableau de caractères
		char[] tab = new char[n];

		int i = 0;

		// cette classe permet de générer des nombres aléatoires
		Random r = new Random();

		while (i < n) {

			// on génère un nombre aléatoire par la méthode r.nextInt(93) dans l'intervalle
			// 0,93 puis on applique un décalage pour retrouver l'interval 33-126
			// le resultat est converit en char puis stocké dans le tableau de caractères
			tab[i++] = (char) (33 + r.nextInt(93));

		}

		// on convertit le tableau de caractères en une chaine de caractères
		return new String(tab);

	}

	/**
	 * implémentation avec le deuxième algorithme demandé dans la question 02
	 * 
	 * @param n
	 *            : nombre de caractères de la chaine
	 * @return la chaine aléatoire
	 */
	public static String generatePasswordV2(int n) {

		// on contruit le tableau qui stocke l'ensemble des caractères autorisés
		char[] tab = new char[94];
		for (int i = 33, j = 0; i < 126; i++, j++) {
			tab[j] = (char) i;
		}

		// Chaine vide
		StringBuilder password = new StringBuilder();

		// On génère la chaine aléatoire
		while (password.length() < n) {

			// On génère un nombre entier qui represente un indice dans le tableau tab puis
			// on accède au caractère associé puis on l'ajoute à la fin de la chaine
			// alatoire
			// randomInt est une méthode qui nous avons codé en se basant sur la méthode
			// Math.Random voir ci-dessous son code
			password.append(tab[randomInt(0, 93)]);

		}

		// conversion en chaine de caractère puis retour du résultat
		return password.toString();

	}

	/**
	 * Méthode qui retourne un entier dans l'intervalle [a,b[
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int randomInt(int a, int b) {

		// on a 0<Math.random()<1
		// donc (b-a)* 0 < (b-a) Math.random() < (b-a) => (b-a)* 0 +a < (b-a)
		// Math.random()+a < (b-a)+a
		// a < (b-a) Math.random() + a < b

		return (int) (a + (b - a) * Math.random());
	}

	public static void main(String[] args) {

		// Avec le premier algorithme
		System.out.println(generatePassword(8));

		// avec le deuxième
		System.out.println(generatePasswordV2(8));

	}

}
