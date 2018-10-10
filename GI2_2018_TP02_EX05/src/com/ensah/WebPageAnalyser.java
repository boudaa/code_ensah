package com.ensah;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Correction de TP
 * 
 * Ecole Nationale des Sciences Appliquées Al Hoceima
 * 
 * @author boudaa
 *
 */
public class WebPageAnalyser {

	/**
	 * Version sans utilisé les expressions regulière
	 * 
	 * @param page
	 * @return
	 */
	public static List<String> getAllH1TitlesFromWP(WebPage page) {

		int index1 = 0, index2 = 0, indexRes1 = 0, indexRes2 = 0;

		// pour y stocker la liste des titres h1 trouvés
		List<String> h1TitlesList = new ArrayList<String>();

		// le corps de la page
		String pageBody = page.getBody();

		// tant que on trouve ce qu'on veut
		while (indexRes1 != -1 && indexRes2 != -1) {

			// chercher une balise h1
			indexRes1 = pageBody.indexOf("<h1>", index1);
			// et sa fin, qui doit etre existante directement apres <h1> si la page est well
			// formed
			indexRes2 = pageBody.indexOf("</h1>", index2);

			// Si nous avons trouver ce qu'on veut on va stocker le titre dans la liste (le
			// décalage +4 car <h1> contient 5 chars)
			if (indexRes1 >= 0 && indexRes2 >= 0) {
				h1TitlesList.add(page.getBody().substring(indexRes1 + 4, indexRes2));

			}
			// décalage avec 4 (=length(<h1>)
			index1 = indexRes1 + 4;

			// décalage avec 5 (=length(</h1>)
			index2 = indexRes2 + 5;

		}

		// à la fin on retourne la liste
		return h1TitlesList;

	}

	/**
	 * Version de getAllH1TitlesFromWP avec les expressions régulières
	 * 
	 * @param page
	 * @return
	 */
	public static List<String> getAllH1TitlesFromWPWithRegEx(WebPage page) {

		// le corps de la page
		String pageBody = page.getBody();

		// on crée le matcher (remarquer que nous avons utiliser *? (Reluctant quantifier)
		//pour plus d'information voir https://www.geeksforgeeks.org/quantifiers-in-java/
		// en francais vous pouvez IX. Quantificateurs sur : https://cyberzoide.developpez.com/tutoriels/java/regex/
		//
		Matcher matcher = Pattern.compile("<h1>(.*?)</h1>").matcher(pageBody);

		// liste qui va contenir les résultats de la recherche
		List<String> listh = new ArrayList<String>();

		// on va parcourir tout les résultats
		while (matcher.find()) {

			// prendre que le texte entre les balise (ayant le groupe 1, le groupe 0 est tjs
			// donnée à toute l'expression)
			listh.add(matcher.group(1));
		}

		return listh;

	}

	/**
	 * Permet de trouver les pages qui contient au moin un titre contenant un mot
	 * passé comme paramètre
	 * 
	 * @param pages
	 * @return
	 */
	public static List<WebPage> getEarthquakeWB(WebPage[] pages, String pWord) {

		List<WebPage> lpages = new ArrayList<WebPage>();

		for (WebPage it : pages) {

			List<String> hs = getAllH1TitlesFromWP(it);

			for (String itp : hs) {

				// on ajouter \b pour indiquer que la recherche doit s'effectuer à l'extrémité
				// des mots
				if (itp.matches(".*\\b" + pWord + "\\b.*")) {

					lpages.add(it);

					// une seul occurrence est suffisante donc pas la peine d'ajouter d'autres
					// itérations
					break;
				}
			}

		}

		return lpages;
	}

	public static void main(String[] args) {

		String body = "<body><h1>Un nouveau tremblement de terre dans la région d’Al Hoceima </h1>"
				+ "<p>Une nouvelle secousse sismique …</p>"
				+ "<h1>Séisme à Al Hoceima : 15 blessés selon le ministère de la Santé</h1><p>Le séisme qui a concerné la région du Rif …</p>"
				+ "<h1>Séisme entre le Maroc et l'Espagne</h1>"
				+ "<p>Un séisme de magnitude 6,1 s’est produit lundi matin,…</p><h1>tremblement à d’Al Hoceima</h1></body>";

		WebPage p = new WebPage("page de teste", body);

		List<String> l = getAllH1TitlesFromWPWithRegEx(p);

		for (String it : l) {
			System.out.println(it);
		}

		WebPage p1 = new WebPage("p1", "<h1>test ko</h1>");
		WebPage p2 = new WebPage("p2", "<h1>test Séismek</h1>");
		WebPage p3 = new WebPage("p3", "<h1>test Séisme test</h1>");
		WebPage p4 = new WebPage("p4", "<h1>Séismes test</h1>");
		WebPage p5 = new WebPage("p5", "<h1>test Séisme</h1>");
		WebPage p6 = new WebPage("p6", "<h1>Séisme test</h1>");

		WebPage[] pages = { p1, p2, p3, p4, p5, p6 };

		List<WebPage> selectedPages = getEarthquakeWB(pages, "Séisme");

		System.out.println(selectedPages);

	}
}
