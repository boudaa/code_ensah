package com.ensah.exercices;

import java.util.Scanner;

/**
 * Correction de l'exercice 08 - TP 01
 * 
 * @author boudaa
 * 
 *         ENSAH Al Hoceima 2018/2019
 *
 */
public class Armstrong {

	public static void main(String[] args) {

		// Lire le nombre au clavier
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		scanner.close();

		// Vérifier que c'est un nombre d'Amstrong
		int r;
		int sum = 0;
		int n = number;
		do {

			//On obtient les chiffres constituant un nombre
			r = n % 10;

			n = n / 10;

			// Somme des cubes
			sum += r * r * r;

		} while (n > 0);

		if (sum == number) {
			System.out.println("Est un nombre d'Amstrong");
		} else {
			System.out.println("Il n'est pas un nombre d'Amstrong");
		}
	}

}
