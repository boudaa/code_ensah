package com.simu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bo.Utilisateur;

//Design Pattern Singleton 

public class MemoryDb {

	private static final MemoryDb uniqueInstance = new MemoryDb();

	private List<Utilisateur> users = new ArrayList<Utilisateur>();

	// Interdir l'instantiation
	private MemoryDb() {

		Utilisateur u = new Utilisateur(Long.valueOf(1), "test", "test", "test", "test", 0, 0);
		users.add(u);

	}

	/**
	 * Permet de récupérer l'unique instance de l'objet MemoryDb
	 * 
	 * @return l'unique instance de MemoryDb
	 */
	public static MemoryDb getUniqueInstance() {

		return uniqueInstance;
	}

	public void addUser(Utilisateur u) {
		users.add(u);
	}

	public List<Utilisateur> getAll() {
		return users;
	}

}

// public class MemoryDb {
//
// private static MemoryDb uniqueInstance = null;
//
// private MemoryDb() {
// }
//
// public synchronized static MemoryDb getUniqueInstance() {
// if (uniqueInstance == null) {
// uniqueInstance = new MemoryDb();
// }
//
// return uniqueInstance;
// }
//
// }
