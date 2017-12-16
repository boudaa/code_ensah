package com.services;

import java.util.List;

import com.bo.Qcm;
import com.bo.Question;

public interface QcmService {

	public Qcm buildQcm(int pN);
	
	public void saveQcm(Qcm pQcm);
	
	public List<Question> getAllQuestions();
	
	public  Qcm getBestScore();

}
