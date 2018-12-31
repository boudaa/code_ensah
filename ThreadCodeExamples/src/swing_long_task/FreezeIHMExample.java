package swing_long_task;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Cette classe montre que l'execution d'une tache longue suite à un click sur
 * un bouton bloquera l'interface de l'application, ceci car les méthodes de
 * gestion des événements s'exécutent dans l'EDT (le thread responsable sur la
 * gestion des composants graphiques)
 * 
 * @author boudaa
 *
 */
public class FreezeIHMExample extends JFrame {

	private JButton launcherBtn = new JButton("Launch");
	private JButton testBtn = new JButton("Test");

	public FreezeIHMExample() {

		setTitle("Freeze IHM Example");
		JPanel pan = new JPanel();
		pan.add(launcherBtn);
		pan.add(testBtn);
		add(pan, BorderLayout.SOUTH);

		launcherBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Une tache longue
				int i = 0;
				while (true) {
					System.out.println(Thread.currentThread().getName() + " : " + (i++));

					// permet de faire une pause de l'execution du thread
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}

			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {

		// à l'execution cliquer sur Launch puis remarquer que l'interface devient non
		// réactive et l'application donc est bloquée
		new FreezeIHMExample();
	}

}
