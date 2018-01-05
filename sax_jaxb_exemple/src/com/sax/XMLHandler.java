package com.sax;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Le gestionnaire d'événements est une classe qui hérite de DefaultHandler
 * 
 * @author TBOUDAA
 *
 */
public class XMLHandler extends DefaultHandler {

	private String node = null;


	
	/**
	 * Méthode qui va s'executer au début du parcing 
	 */
	public void startDocument() throws SAXException {
		System.out.println("Début du parsing");
	}

	/**
	 * Méthode qui va s'executer à la fin du parsing
	 */
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing");
	}

	/**
	 * s'execute au début de chaque élément
	 */
	public void startElement(String namespaceURI, String lname, String nodeName,
			Attributes litsAttributs) throws SAXException {

		System.out.println("-----------------ELEMENT--------------------");
		
		
		// cette variable contient le nom du nœud qui a déclenché l'événement
		
		System.out.println("nom du noeud qui a déclenché l'événement = " + nodeName);
		
		node = nodeName;

		// Cette dernière contient la liste des attributs du nœud
		if (litsAttributs != null) {
			for (int i = 0; i < litsAttributs.getLength(); i++) {
				// nous récupérons le nom de l'attribut
				String aname = litsAttributs.getLocalName(i);
				// Et nous affichons sa valeur
				System.out.println("Attribut " + aname + " valeur : "
						+ litsAttributs.getValue(i));
			}
		}
	}
	
	
	
	/**
	 * permet de récupérer la valeur d'un nœud
	 */  
	public void characters(char[] data, int start, int end){   
	   System.out.println("*******************characters****************************");
	
	   
	   System.out.println(start);
	   System.out.println(end);
	   
	   String str = new String(data, start, end);
	   
	   System.out.println("Donnée du nœud " + node + " : " + str);
	   
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("Fin de l'élément " + qName);
	}
}