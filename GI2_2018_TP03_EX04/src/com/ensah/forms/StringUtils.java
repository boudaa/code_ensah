package com.ensah.forms;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Offre un ensemble de méthode utilitaires
 * 
 * @author boudaa
 *
 */
public class StringUtils {

	public static boolean isCube(String pCube) {

		return "#CUBE".equals(pCube);

	}

	public static List<String> readFile(String pFileName) throws IOException {

		List<String> lignes = FileManager.readFileLines(pFileName);

		return lignes;

	}

	// Version sans expression réguliere
	public static Cube extraireCubeV1(String pLine) throws MalformedException {

		String[] tokens = pLine.split(";");

		if (tokens.length != 3)
			throw new MalformedException("Le format d'un cube n'est pas respecté");

		Point3D centre = extrairePoint(tokens[0]);

		double c;
		double d;

		try {
			c = Double.parseDouble(tokens[1]);
			d = Double.parseDouble(tokens[2]);

		} catch (NumberFormatException ex) {

			throw new MalformedException("Les coordonnées doivent etre des nombres", ex);
		}

		return new Cube(centre, c, d);

	}

	// version avec expression refuliere
	public static Cube extraireCubeV2(String pLine) throws MalformedException {

		// Expression régulière pour vérifier la syntaxe d'un cube qui doit etre de
		// forme: x y z;w;v
		String regEx = "^((\\d+\\.?\\d*) (\\d+\\.?\\d*) (\\d+\\.?\\d*));(\\d+\\.?\\d*);(\\d+\\.?\\d*)$";

		Matcher matcher = Pattern.compile(regEx).matcher(pLine);

		if (!matcher.matches()) {
			throw new MalformedException("Le format d'un cube n'est pas respecté");
		}

		return new Cube(extrairePoint(matcher.group(1)), Double.parseDouble(matcher.group(5)),
				Double.parseDouble(matcher.group(6)));

	}

	public static Point3D extrairePoint(String pPoint3d) throws MalformedException {

		// Expression régulière pour vérifier la syntaxe d'un point qui doit etre de
		// forme: x y z
		String regEx = "^(\\d+\\.?\\d*) (\\d+\\.?\\d*) (\\d+\\.?\\d*)$";

		Matcher matcher = Pattern.compile(regEx).matcher(pPoint3d);

		if (!matcher.matches()) {
			throw new MalformedException("Le point doit avoir 3 coordonnées");
		}

		return new Point3D(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)),
				Double.parseDouble(matcher.group(3)));

		/*
		 * on peut aussi utiliser split
		 * 
		 * String[] tokens = pPoint3d.split(" ");
		 * 
		 * return new Point3D(Double.parseDouble(tokens[0]),
		 * Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
		 */
	}

}
