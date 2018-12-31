package swing_long_task;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Cette classe corrige le prbobleme FreezeIHM vue précédement dans la classe
 * FreezeIHMExample. Ceci on executant la tache longue dans un thread séparé
 * 
 * @author boudaa
 *
 */
public class FreezeIHMSolutionExample extends JFrame {

	private JButton launcherBtn = new JButton("Launch");
	private JButton testBtn = new JButton("Test");

	public FreezeIHMSolutionExample() {

		setTitle("Freeze IHM Solution");
		JPanel pan = new JPanel();
		pan.add(launcherBtn);
		pan.add(testBtn);
		add(pan, BorderLayout.SOUTH);

		launcherBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// pour résoudre le problème on va utiliser un thread séparé pour exécuter la
				// tache longue

				Runnable task = new Runnable() {

					//////////////////////////////////////////////////////////////////////////////////////////////////
					////
					//// ATTENTION :
					//// ******************
					//// Vous n'avez pas le droit d'appeler
					//// directement ici une méthode Swing, car la majorité
					//// ne sont pas thread safe !!!
					//// Si vous avez besoin d'appeler des méthodes Swing
					//// alors il faut le faire via les méthodes
					//// invokeAndWait(Runnable runnable) ou invokeLater(Runnable runnable)
					//// de la classe EventQueue ou SwingUtilities (ou d'autres techniques équivalentes)
					//// permettant donc d'executer la méthode dans le thread EDT.
					//// Voir un exemple dans  : FreezeIHMSolutionWithEDTExample
					//// Pour plus d'informations voir :
					//// https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html#threading
					///////////////////////////////////////////////////////////////////////////////////////////////////

					// Méthode dans laquelle on définit la tache à executer dans le thread
					public void run() {

						int i = 0;
						while (true) {

							System.out.println(Thread.currentThread().getName() + " : " + (i++));

							// permet de faire une pause de l'execution du thread
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};

				// Création d'un Thread
				Thread th = new Thread(task);

				// On démarre le thread.
				th.start();

			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();

		setVisible(true);
	}

	public static void main(String[] args) {

		// On remarque que le problème est résolu
		new FreezeIHMSolutionExample();
	}

}
