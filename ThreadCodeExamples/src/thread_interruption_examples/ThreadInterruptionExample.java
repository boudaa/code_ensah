package thread_interruption_examples;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Cette classe implémente une fenetre de deux boutons, le premier sert à créer
 * des threads et l'autre à les interompre
 * 
 * @author boudaa
 *
 */
public class ThreadInterruptionExample extends JFrame {

	// Bouton permettant de lancer les threads
	private JButton launcherBtn = new JButton("Launch");
	// Bouton permettant d'arreter les threads
	private JButton interruptBtn = new JButton("Interrupt");

	// stocke les références sur les objets threads crées
	private List<Thread> threadList = new ArrayList<Thread>();

	public ThreadInterruptionExample() {

		// titre de la fentre
		setTitle("Thread interruption example");

		// construction d'un paneau de boutons
		JPanel pan = new JPanel();
		pan.add(launcherBtn);
		pan.add(interruptBtn);

		// ajouter le paneau sur la fentre
		add(pan, BorderLayout.SOUTH);

		// Ajouter les actions à executer en cas de clicks sur les boutons

		// Cas du bouton qui lance les threads
		launcherBtn.addActionListener(new ActionListener() {

			// méthode qui s'exécutera en cas d'une action sur le bouton
			public void actionPerformed(ActionEvent e) {

				// Créer la tache du thread
				Runnable task = new Runnable() {

					// Méthode dans laquelle on définit la tache à executer dans le thread
					public void run() {

						int i = 0;
						try {
							while (!Thread.currentThread().isInterrupted()) {

								System.out.println("Name=" + Thread.currentThread().getName() + " : " + (i++));

								// permet de faire une pause de l'execution du thread

								Thread.sleep(2000);
							}
						} catch (InterruptedException e) {
							System.out.println("Demande d'interruption recue quand le thread était dans l'état sleep");

							// On arrete le thread
							Thread.currentThread().interrupt();

						} finally {
							System.out.println("Bloc finally");
							// nettoyage, si nécessaire
						}
					}
				};

				// Construire l'objet thread
				Thread th = new Thread(task);

				// on l'ajoute dans la liste
				threadList.add(th);

				// On démarre le thread
				th.start();

			}

		});

		interruptBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// on interompre tous les threads
				for (Thread it : threadList) {
					it.interrupt();
				}
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();

		setVisible(true);
	}

	public static void main(String[] args) {
		new ThreadInterruptionExample();
	}

}
