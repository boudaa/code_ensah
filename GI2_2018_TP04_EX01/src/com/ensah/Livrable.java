package com.ensah;

/**
 * Permet d'isoler PFE des implémentations des livrables
 * 
 * Pour assurer une maintenabilité facile du code
 * 
 * (Ajout de d'autres types de livrables n'impactera pas le code de la classe
 * PFE)
 * 
 * En plus, elle permet d'imposer à tous les livrables d'etre imprimables càd
 * donnent une implémentation de la méthode print
 * 
 * @author BOUDAA
 *
 */
public interface Livrable {

	public void print();

}
