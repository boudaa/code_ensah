package thread_creation_examples;

/*
 * Définition d'une classe permettant de créer un thread avec la méthode par héritage de la classe Thread
 */
public class ByExtendingThreadClass extends Thread {

	// Constructeur de cette classe
	// on peut avoir d'autres constructeurs
	public ByExtendingThreadClass(String pThreadName) {
		// Appel de constructeur de la classe de base
		super(pThreadName);
	}

	// Méthode dans laquelle on définit la tache à executer dans le thread
	public void run() {
		System.out.println("Nom du thread : " + getName());

		int i = 0;
		while (true) {
			System.out.println(i++);

			// permet de faire une pause de l'execution du thread
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		// Construire l'objet thread
		ByExtendingThreadClass th = new ByExtendingThreadClass("Thread Test");

		// On démarre le thread. Attention : le lancement de thread se fait par start et
		// non pas par la méthode run
		th.start();

	}

}
