package com.bank.core.bll;

/**
 * Fabrique les services metiers
 * 
 * @author boudaa
 *
 */
public class ManagerFactory {

	/** l'unique instance de cette classe * */
	private static ManagerFactory staticInstance = null;

	/**
	 * Constructeur
	 */
	protected ManagerFactory() {
		// interdir l'instantiation volentairement
	}

	/**
	 * Retourne l'unique instance de cette fabrique
	 */
	public static ManagerFactory getInstance() {

		if (staticInstance == null) {
			staticInstance = new ManagerFactory();
		}
		return staticInstance;
	}

	/**
	 * Permet de construire un service metier
	 * 
	 * @param pClasseName : nom du service metier
	 * @return
	 */
	public IManager getManager(String pClasseName) {

		IManager manager = null;
		try {
			System.out.println(pClasseName);
			// On charge la classe par reflexion
			Class<?> c = Class.forName(pClasseName);
			// instantaion du DAO
			manager = (IManager) c.getConstructor().newInstance();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return manager;

	}

}
