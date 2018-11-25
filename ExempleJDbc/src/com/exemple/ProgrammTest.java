package com.exemple;

import java.util.Date;

import com.exception.DataBaseException;

public class ProgrammTest {

	public static void main(String[] args) {

	
		
		try {
			
			Etudiant etd = new Etudiant("R1254", "Boudaa", "Mohamed", "Imzouren", new Date());
			
			EtudiantDao etdDao = new EtudiantDao();
			
			etdDao.add(etd);
			
			//TODO : Testez vous meme les autres méthodes ici
			
			
			
		} catch (DataBaseException e) {
			
			e.printStackTrace();
		}
		
		

	}

}
