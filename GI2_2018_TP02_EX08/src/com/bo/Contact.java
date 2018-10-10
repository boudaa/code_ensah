package com.bo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {

	private String fax;
	private String phoneNumber;
	private String email;
	private String adress;

	public Contact() {

	}

	public Contact(String fax, String phoneNumber, String email, String adress) {
		this.fax = fax;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.adress = adress;
	}

	public boolean isEmailValideWithoutRegEx(String pEmail) {

		// On supprime les espaces à la fin et au début
		pEmail = pEmail.trim();

		// On divise avec le séparateur @
		String[] mailTokens = pEmail.split("@");

		// on doit avoir deux sous chaines apres séparation avec @
		if (mailTokens.length != 2 || mailTokens[0].isEmpty() || mailTokens[1].isEmpty())
			return false;

		// on sépare avec '.'
		String[] domainTokens = mailTokens[1].split("\\.");

		// on doit avoir deux sous chaines
		if (domainTokens.length != 2 || mailTokens[0].isEmpty() || mailTokens[1].isEmpty())
			return false;

		return true;
	}

	public boolean isEmailValide(String pEmail) {

		// on suppose que l'email s'ecrit xxx@yyy.zz avec xxx chaine de 4 à 30 char yyy
		// de 2 à 6 char et zz un nom de domaine parmis : info,com,fr,org,ma

		Pattern emailPattern = Pattern.compile("^\\w{4,30}@\\w{2,6}\\.(info|com|fr|org|ma)$");

		// créer les objets Matcher associés
		Matcher m = emailPattern.matcher(pEmail);

		if (m.matches()) {
			return true;
		}

		return false;
	}

	/**
	 * On suppose que le CIN s'écrit première LETTRE en maj et 6 exp : chiffres :
	 * R112121
	 * 
	 * @param pCin
	 * @return
	 */
	public boolean isCinValideWithoutRegEx(String pCin) {

		// traitement de pCin

		if (pCin.charAt(0) < 'A' || pCin.charAt(0) > 'Z')

			return false;

		if (pCin.length() != 7)
			return false;

		for (int i = 1; i < pCin.length(); i++) {
			if (pCin.charAt(i) < '0' || pCin.charAt(i) > '9') {
				return false;
			}

		}

		return true;
	}

	public boolean isCinValide(String pCin) {

		Pattern cinPattern = Pattern.compile("^[A-Z]{1}\\d{6}$");

		// créer les objets Matcher associés
		Matcher m = cinPattern.matcher(pCin);

		if (m.matches()) {
			return true;
		}

		return false;

	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	public void showContact() {
		System.out.println(
				"[fax=" + fax + ", phoneNumber=" + phoneNumber + ", email=" + email + ", adress=" + adress + "]");
	}

}
