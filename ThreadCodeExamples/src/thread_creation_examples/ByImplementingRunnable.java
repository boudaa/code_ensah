package thread_creation_examples;

/*
 * Définition d'une classe permettant de créer un thread avec la méthode par implémentation de l'interface Runnable
 */
public class ByImplementingRunnable implements Runnable {

	// Méthode dans laquelle on définit la tache à executer dans le thread
	public void run() {

		int i = 0;
		while (true) {
			System.out.println(i++);

			// permet de faire une pause de l'execution du thread
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		// Construire l'objet thread
		Thread th = new Thread(new ByImplementingRunnable());

		// On démarre le thread. Attention : le lancement de thread se fait par start et
		// non pas par la méthode run
		th.start();

	}

}
