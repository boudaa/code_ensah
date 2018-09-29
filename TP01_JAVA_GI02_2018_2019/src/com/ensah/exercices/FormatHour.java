package com.ensah.exercices;

/**
 * Correction de l'exercice 02 - TP 01
 * 
 * @author boudaa
 * 
 *         ENSAH Al Hoceima 2018/2019
 *
 */
public class FormatHour {

	public String FormatHoure(int pNumberToFormatint) {

		int hour = 0, minute = 0, secondes = 0;

		if (pNumberToFormatint >= 3600) {
			hour = pNumberToFormatint / 3600;
		}

		// Le reste
		pNumberToFormatint = pNumberToFormatint - hour * 3600;

		if (pNumberToFormatint >= 60) {
			minute = pNumberToFormatint / 60;
		}

		secondes = pNumberToFormatint - minute * 60;

		return hour + " h " + minute + " m " + secondes + " s ";

	}

	public static void main(String[] args) {
		int n;
		// On va lire le paramètre passé au programme depuis le tableau args
		if (args.length != 1)  {
			// Convertir en entier (cette insctruction va declencher une erreur (exception)
			// si le nombre
			// n'est pas un entier)
			n = Integer.parseInt(args[0]);

			FormatHour a = new FormatHour();
			System.out.println(a.FormatHoure(n));

		} else {
			System.out.println("ArgError, il faut passer un argment entier à la commande FormatHour");
		}

	}

}
