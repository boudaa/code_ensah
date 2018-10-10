package com.ensah.exercices;

/**
 * Correction de l'exercice 04 - TP 01
 * 
 * @author boudaa
 * 
 *         ENSAH Al Hoceima 2018/2019
 *
 */
public class NombreAmis {

	/**
	 * Méthode pour calculer la somme des diviseurs propres
	 * 
	 */
	public static int sommeDiviseurPropre(int n) {

		int sum = 0;
		for (int i = 1; i < n; i++) {
			if (n % i == 0) {
				sum += i;
			}

		}
		return sum;
	}

	/**
	 * Méthode pour afficher les nombres amis
	 * 
	 */
	public static void amisV1(int n) {
		for (int i = 1; i < n; i++) {

			for (int j = i + 1; j < n; j++) {

				if (sommeDiviseurPropre(i) == j && sommeDiviseurPropre(j) == i) {

					System.out.println(i + " et " + j + " sont amis");

				}
			}
		}
	}

	public static void amisV2(int n) {

		int sum1 = 0, sum2 = 0;

		for (int i = 1; i < n; i++) {

			sum1 = sommeDiviseurPropre(i);
			if (sum1 > i) {
				sum2 = sommeDiviseurPropre(sum1);

			} else {
				continue;
			}

			if (i != sum1 && sum2 == i) {

				System.out.println(i + " et " + sum1 + " sont amis");

			}
		}
	}

	public static void main(String[] args) {

		long startTime1, endTime1, startTime2, endTime2;
		// on execute les deux versions et on affiche le temps d'execution de chacune
		int n = 2000;
		startTime1 = System.nanoTime();
		amisV1(n);
		endTime1 = System.nanoTime();

		System.out.println("Temps V1 =" + (endTime1 - startTime1) + " nanosecondes");

		System.out.println("-------------------------");

		startTime2 = System.nanoTime();
		amisV2(n);
		endTime2 = System.nanoTime();
		System.out.println("Temps V2 =" + (endTime2 - startTime2) + " nanosecondes");

		System.out.println("-----------COMPARAISON-------------");

		System.out.println("V2 est presque " + (endTime1 - startTime1) / (endTime2 - startTime2) + " fois rapide");

	}

}
