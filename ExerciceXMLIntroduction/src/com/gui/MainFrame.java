package com.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.bo.Animal;

/**
 * Fentre principale de l'application
 * 
 * @author Tarik BOUDAA
 *
 */
public class MainFrame extends JFrame {

	/** Paneau conteneur */
	private JanglePanel janglePan;

	/** Permet de lancer les déplacements du lion */
	private JButton btnLion = new JButton("Déplacer Lion");

	/** Permet de lancer les déplacements du Lapin */
	private JButton btnLapin = new JButton("Déplacer Lapin");

	private JLabel stateLabel = new JLabel("...........");

	public MainFrame() {

		// Créer le conteneur
		janglePan = new JanglePanel();

		// Donner un titre à la fentre
		setTitle("Application XML Introduction");
		// Le size de la fenetre, normalement faut le calculer dynamiquement en
		// fonction de l'écran
		setSize(600, 500);

		// La fermeture de la fentre doit arreter le processus de l'application
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// traitelent de l'évenemt associé au bouton déplacer lion

		btnLion.addActionListener(new ActionListener() {

			// S'execute lorsqu'on clique sur le bouton
			public void actionPerformed(ActionEvent evn) {

				// On affiche dans l'interface l'état
				stateLabel.setText("Le lion est en mouvement");

				// On affiche dans la console le thread qui execute le code
				// suivant
				displayUtils("Déplacement d'un lion...");

				// L'animal qui se trouve dans la position 0 (le lion dans notre
				// cas)
				janglePan.choisirAnimal(0);

				// executer dans un nouveau thread le traitement long qu'est le
				// déplacement d'un animal
				doLongTask();
			}

		});

		btnLapin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evn) {

				// On affiche dans l'interface l'état
				stateLabel.setText("Le lapin est en mouvement");

				// On affiche dans la console le thread qui execute le code
				// suivant
				displayUtils("Déplacement d'un lapin...");

				// L'animal qui se trouve dans la position 1 (le lapin dans
				// notre
				// cas)
				janglePan.choisirAnimal(1);

				// executer dans un nouveau thread le traitement long qu'est le
				// déplacement d'un animal
				doLongTask();
			}

		});

		// Création d'un paneau qui va contenir les boutons de l'application
		JPanel menuPan = new JPanel();
		// ajout d boutons dans le paneau menu
		menuPan.add(btnLapin);
		menuPan.add(btnLion);

		// le paneau menu est ajouté au nord de la fentre
		add(menuPan, BorderLayout.NORTH);

		// Paneau qui affiche l'état de l'application
		JPanel statePan = new JPanel();
		statePan.add(stateLabel);
		add(statePan, BorderLayout.SOUTH);

		// ajout du paneau principal sur la fentre
		add(janglePan);

		// afficher la fentre
		setVisible(true);

	}

	/**
	 * Méthode permettant de déplacer l'animal
	 */
	public void doLongTask() {

		// on crée un thread pour executer la tache longue
		new Thread(new Runnable() {

			// La tache du thread
			public void run() {

				// On affiche dans la console le thread qui execute le code
				// suivant
				displayUtils("Execution de la tache longue...");

				Animal animal = janglePan.getAnimal();

				// Parcourir la liste des position de l'animal
				while (animal.notEnd()) {

					// On déplace l'animal et on récupère la durée entre deux
					// positions successives
					int speed = janglePan.deplacer();

					// la majorité des méthodes qui manipulent l'inetrface
					// graphique ne doivent pas etre appelées dans un thread
					// autre que l'EDT (Event Dispatch Thread) Pour appeler une
					// méthode dans l'EDT on
					// peut utiliser les
					// méthodes de SwingUtilities par exemple
					// Les méthodes suivantes font l'exception elles peuvent
					// etre appelées en dehors de l'EDT : repaint(),
					// revalidate(), invalidate().

					// Ainsi il est safe d'appeler repaint ici bien qu'on est
					// pas dans l'EDT
					repaint();

					// retrader l'execution pour simuler la vitesse de
					// déplacement
					try {
						Thread.sleep(speed * 100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				// On affiche dans l'interface l'état : ATTENTION il faut faire
				// ça dans l'EDT et non pas dans le thread en cours
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {

						// On affiche dans la console le thread qui execute le
						// code
						// suivant
						displayUtils("Modification de l'état de l'application");

						stateLabel.setText("Fin du mouvement de l'animal");

					}
				});
			}
		}, "Thread Long Task").start(); // on démarre le thread
	}

	public static void displayUtils(String pMsg) {
		Thread currentThread = Thread.currentThread();

		System.out.println("L'opération " + pMsg
				+ " s'execute dans le thread  : " + currentThread.getName());
	}

	public static void main(String[] args) {
		displayUtils("début méthode main");

		// On créer la fentre dans l'EDT (Event Dispatch Thread)

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				displayUtils("Création de la fentre");

				new MainFrame();
			}
		});

	}

}
