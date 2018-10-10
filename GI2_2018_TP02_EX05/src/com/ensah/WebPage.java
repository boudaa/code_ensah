package com.ensah;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe pour modéliser une page web
 * 
 * @author boudaa
 *
 */
public class WebPage {

	/** le titre de la page */
	private String titre;

	/** le corps de la page */
	private String body;

	/**
	 * Constructeur
	 * 
	 * @param pTitre
	 * @param pBody
	 */
	public WebPage(String pTitre, String pBody) {

		titre = pTitre;
		body = pBody;

	}

	public double getDensity(String pMot) {

		// On compte le nombre de fois pMot est apparu dans le corps de la page
		int index = 0, comp = 0, r;

		do {
			// indexe de la première occurence en cherchant à partir de index

			r = body.indexOf(pMot, index);

			if (r >= 0) {
				comp++;
			}

			// mettre à jour le déclage
			index = r + pMot.length();

		} while (r != -1);

		return (comp * pMot.length()) / (0.5 * body.length());

	}

	public static void main(String[] args) {

		WebPage p = new WebPage("p1", "test ko test test");

		System.out.println(p.getDensityWithRegEx("test"));
		System.out.println(p.getDensity("test"));

		// ce test va montrer les limites de la méthode getDensity
		// WebPage p1 = new WebPage("p1", "testt ko testt testt");
		//
		// System.out.println(p1.getDensityWithRegEx("test"));
		// System.out.println(p1.getDensity("test"));

	}

	public double getDensityWithRegEx(String pMot) {

		Matcher matcher = Pattern.compile("\\b" + pMot + "\\b").matcher(body);

		int comp = 0;
		// on va parcourir tout les résultats
		while (matcher.find()) {

			// System.out.println(matcher.start());
			// System.out.println(matcher.end());

			comp++;
		}

		return (comp * pMot.length()) / (0.5 * body.length());

	}

	@Override
	public String toString() {
		return "WebPage [titre=" + titre + "]";
	}

	public String getTitre() {
		return titre;
	}

	public String getBody() {
		return body;
	}

	public void setTitre(String pTitre) {
		titre = pTitre;
	}

	public void setBody(String pTitre) {
		body = pTitre;
	}

}
