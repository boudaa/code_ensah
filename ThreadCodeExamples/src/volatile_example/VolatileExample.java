package volatile_example;

import java.util.Scanner;

/*
 *  If we try to access a volatile variable twice,
 *   it will be accessed twice from the hardware memory,
 *    not the cache. In this way, volatile variable avoids optimization
 */
class VolatileExample extends Thread {

	private volatile boolean running = true;

	public void run() {

		/*
		 * In a multi-threaded system this code may not work correctly. If some other
		 * thread accesses the variable and changes it to false, the thread running this
		 * code block may not know about the change immediately. This may result in the
		 * loop being executed more than necessary and may result in data integrity
		 * problems and it is a hard very hard error to find and fix
		 */

		/*
		 * Le langage Java fournit également une alternative, une forme plus faible de
		 * synchronisation : les variables volatiles. Celles-ci permettent de s’assurer
		 * que les modifications apportées à une variable seront systématiquement
		 * répercutées à tous les autres threads. Lorsqu’un champ est déclaré volatile,
		 * le compilateur et l’environnement d’exécution sont prévenus que cette
		 * variable est partagée et que les opérations sur celle-ci ne doivent pas être
		 * réarrangées avec d’autres opérations sur la mémoire. Les variables volatiles
		 * ne sont pas placées dans des registres ou autres caches qui les masqueraient
		 * aux autres processeurs ; la lecture d’une variable volatile renvoie donc
		 * toujours la dernière valeur qui y a été écrite par un thread quelconque.
		 */
		while (running) {

			try {

				System.out.println("Hello");
				Thread.sleep(0);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

	public void shudown() {
		running = false;
	}

	public static void main(String[] args) {
		VolatileExample processor = new VolatileExample();
		processor.start();
		System.out.println("Press a key");
		new Scanner(System.in).nextLine();
		processor.shudown();
	}

}