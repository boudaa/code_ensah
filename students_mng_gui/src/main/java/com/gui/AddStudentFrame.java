package com.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.core.bll.bo.Student;
import com.core.bll.services.IStudentMangerService;
import com.core.bll.services.ServiceFactory;

public class AddStudentFrame extends JFrame {

	private LabledTextField firstName = new LabledTextField("First Name");
	private LabledTextField lastName = new LabledTextField("Last Name");
	private LabledTextField email = new LabledTextField("Email");
	private LabledTextField cne = new LabledTextField("CNE");

	private IStudentMangerService service = ServiceFactory.getServicesFactory().getStudentManagerService();

	public AddStudentFrame() {

		JPanel contentPan = new JPanel();

		JPanel menuPan = new JPanel();

		JButton saveBtn = new JButton("Save");
		JButton canceBtn = new JButton("Cancel");
		menuPan.add(saveBtn);
		menuPan.add(canceBtn);

		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Student student = new Student(firstName.getText(), lastName.getText(), email.getText(), cne.getText());

				service.addStudent(student);
				
				JOptionPane.showMessageDialog(null, "Etudiant ajouté");

			}
		});

		setTitle("Ajout etudiant");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		contentPan.setLayout(new BoxLayout(contentPan, BoxLayout.Y_AXIS));

		contentPan.add(firstName);
		contentPan.add(lastName);
		contentPan.add(email);
		contentPan.add(cne);

		add(menuPan, BorderLayout.SOUTH);
		add(contentPan, BorderLayout.CENTER);

		pack();
		setVisible(true);

	}

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {

		SwingUtilities.invokeAndWait(new Runnable() {

			@Override
			public void run() {
				new AddStudentFrame();
			}
		});

	}

}
