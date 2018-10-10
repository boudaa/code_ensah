package com.ensah;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainProg {

	public static void main(String[] args) {

		// Exemple d'un code source

		// Création des étudiants
		Etudiant etdSamir = new Etudiant("R121", "GI", "Erradi", "Tarik",
				"12515455", "sali@gmail.com");

		// ....

		// Création des profs
		Prof prof1 = new Prof("...", "...", "Salimi", "...", "...",
				"...@gmail.com");
		Prof prof2 = new Prof("...", "...", "Ramali", "...", "...",
				"...@gmail.com");
		Prof prof3 = new Prof("...", "...", "Nafili", "...", "...",
				"...@gmail.com");
		Prof prof4 = new Prof("...", "...", "Bahi", "...", "...",
				"...@gmail.com");

		// Création des livrables
		Livrable imprimable = new Rapport("texte du rapport", 12, "fr");
		CodeSource code = new CodeSource("code java", "java");

		// Construction de la liste des livrables
		List<Livrable> livrables = new ArrayList<Livrable>();
		livrables.add(imprimable);
		livrables.add(code);

		// Construction d'un jury
		List<Prof> profs = new ArrayList<Prof>();
		profs.add(prof2);
		profs.add(prof3);
		profs.add(prof4);

		Jury jury = new Jury(profs);

		// Construction d'un PFE
		Pfe pfe = new Pfe("sujet pfe", new Date(), "Atos", livrables, 15.,
				prof1, etdSamir);

		// On affecte un jury à ce PFE
		pfe.affecterJury(jury);

		// On affiche les livrables de ce PFE
		pfe.afficheLivrable();
	}

}
