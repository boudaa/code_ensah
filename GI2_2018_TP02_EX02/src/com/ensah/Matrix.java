package com.ensah;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * 
 * @author boudaa
 *
 */
public class Matrix {

	/**
	 * Permet d'afficher une matrice
	 * 
	 * @param tab
	 */
	public static void printMatrix(int[][] tab) {

		for (int i = 0; i < tab.length; i++) {

			for (int j = 0; j < tab[i].length; j++) {
				System.out.print(tab[i][j] + "\t");
			}
			System.out.println();
		}

	}

	/**
	 * Retoune les points cols de la matrices sous forme d'une liste d'objets
	 * 
	 * @param tab
	 * @return
	 */
	public static List<PointCol> getPointsCols(int[][] tab) {

		// contient la liste des points cols non encore finalisée
		List<PointCol> tempPointColList = new ArrayList<PointCol>();

		// contient la liste finale des points cols
		List<PointCol> pointColList = new ArrayList<PointCol>();

		// on determine les maximas sur les lignes
		for (int i = 0; i < tab.length; i++) {

			PointCol max = new PointCol(i, 0, tab[i][0]);

			for (int j = 0; j < tab[i].length; j++) {

				if (tab[i][j] > max.getValue()) {
					max.setValue(tab[i][j]);
					max.setI(i);
					max.setJ(j);
				}
			}

			// on ajoute aussi tous les maximums (éléments ayant meme valeur que max)
			for (int j = 0; j < tab[i].length; j++) {
				if (max.getValue() == tab[i][j]) {
					tempPointColList.add(new PointCol(i, j, tab[i][j]));
				}
			}
		}

		// on determine les minimas sur les colonnes

		for (int j = 0; j < tab[0].length; j++) {

			// on determine le minimum sur chaque colonne
			PointCol min = new PointCol(0, j, tab[0][j]);

			for (int i = 0; i < tab.length; i++) {

				if (tab[i][j] < min.getValue()) {
					min.setValue(tab[i][j]);
					min.setI(i);
					min.setJ(j);
				}
			}

			PointCol tempPointCol = null;
			// Prendre en compte toutes les valeurs égale à min sur une colonne
			for (int i = 0; i < tab.length; i++) {
				tempPointCol = new PointCol(i, j, tab[i][j]);
				// attention la méthode contains fait un appel automatique à la méthode equals ,
				// ne pas redéfinir equals correctement donnera pas le bon résultat
				// si il est un minimum sur la colonne et il est déjà dans la liste temporaire
				// alors c'est un point col
				if (min.getValue() == tab[i][j] && tempPointColList.contains(tempPointCol)) {
					pointColList.add(tempPointCol);

				}
			}

		}

		return pointColList;

	}

	/**
	 * Retoune les points cols de la matrices sous forme d'une liste d'objets
	 * 
	 * @deprecated cette version est incomplète, elle est donc remplacé par la
	 *             méthode @see getPointsCols
	 * @param tab
	 * @return
	 */
//	public static List<PointCol> getPointCols(int[][] tab) {
//
//		// contient la liste des points cols non encore finalisée
//		List<PointCol> tempPointCol = new ArrayList<PointCol>();
//
//		// contient la liste finale des points cols
//		List<PointCol> pointColList = new ArrayList<PointCol>();
//
//		// on determine les maximas sur les lignes
//		for (int i = 0; i < tab.length; i++) {
//
//			PointCol max = new PointCol(i, 0, tab[i][0]);
//
//			for (int j = 0; j < tab[i].length; j++) {
//
//				if (tab[i][j] > max.getValue()) {
//					max.setValue(tab[i][j]);
//					max.setI(i);
//					max.setJ(j);
//				}
//			}
//
//			tempPointCol.add(max);
//		}
//
//		// on determine les minimas sur les colonnes
//
//		for (int j = 0; j < tab[0].length; j++) {
//
//			// on determine le minimum sur chaque colonne
//			PointCol min = new PointCol(0, j, tab[0][j]);
//
//			for (int i = 0; i < tab.length; i++) {
//
//				if (tab[i][j] < min.getValue()) {
//					min.setValue(tab[i][j]);
//					min.setI(i);
//					min.setJ(j);
//				}
//			}
//
//			// si il est déjà dans la liste temporaire alors c'est un point col
//			// attention la méthode contains fait un appel automatique à la méthode equals ,
//			// ne pas redéfinir equals correctement donnera pas le bon résultat
//			if (tempPointCol.contains(min)) {
//				pointColList.add(min);
//			}
//		}
//
//		return pointColList;
//
//	}

	/**
	 * Permet de lire une matrice au clavier
	 * 
	 * @return
	 */
	public static int[][] readMatrix() {

		Scanner sc = new Scanner(System.in);

		System.out.println("nombre de lignes");
		int linges = sc.nextInt();
		System.out.println("nombre de colonnes");
		int cols = sc.nextInt();
		int[][] tab = new int[linges][cols];
		for (int i = 0; i < linges; i++) {

			for (int j = 0; j < cols; j++) {
				System.out.println("(" + i + "," + j + ")");
				tab[i][j] = sc.nextInt();
			}
		}

		sc.close();

		return tab;

	}

	public static void main(String[] args) {

		// lire et afficher la matrice
		// printMatrix(readMatrix());

		// tester point cols
		int[][] t = { { 2, 3, 3 }, { 1, 0, 1 }, { 13, 11, 10 }, { 18, 21, 90 } };

		printMatrix(t);

		System.out.println(getPointsCols(t));
	}

}
