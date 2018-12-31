package swing_long_task;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Cette classe corrige le prbobleme FreezeIHM vue précédement dans la classe
 * FreezeIHMExample. Ceci on executant la tache longue dans un thread séparé
 * 
 * @author boudaa
 *
 */
public class FreezeIHMSolutionWithEDTExample extends JFrame {

	private JButton launcherBtn = new JButton("Launch");
	private JButton testBtn = new JButton("Test");
	private JTextField textField = new JTextField("Bonjour je suis un simple test");

	public FreezeIHMSolutionWithEDTExample() {

		setTitle("Freeze IHM Solution");
		JPanel pan = new JPanel();
		pan.add(textField);
		pan.add(launcherBtn);
		pan.add(testBtn);
		add(pan, BorderLayout.SOUTH);

		launcherBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// pour résoudre le problème on va utiliser un thread séparé pour exécuter la
				// tache longue

				Runnable task = new Runnable() {
					int i = 0;

				
					// Méthode dans laquelle on définit la tache à executer dans le thread
					public void run() {

						while (true) {

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
							//// For more information See :
							//// https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html#threading
							///////////////////////////////////////////////////////////////////////////////////////////////////

							
							// Je veux mettre à jour l'IHM donc je ne dois pas le faire depuis ce thread, je
							// dois poster une tache dans l'EDT
							SwingUtilities.invokeLater(new Runnable() {

								@Override
								public void run() {
									textField.setText("La date/heure est : " + new Date());
								}
							});

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
		new FreezeIHMSolutionWithEDTExample();
	}

}
