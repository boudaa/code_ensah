package com.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.Qcm;
import com.bo.Question;
import com.dao.QcmDao;
import com.dao.QuestionDao;
import com.services.QcmService;

@Service
@Transactional
public class QcmServiceImpl implements QcmService {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private QcmDao qcmDao;

	public Qcm buildQcm(int pN) {

		// Toutes les question de la base de données
		List<Question> questions = getAllQuestions();

		if (pN > questions.size()) {
			throw new IllegalArgumentException("Le nombre de questions doit etre inférieur é " + questions.size());
		}

		int count = 0;

		// Pour stocker les indices déjé utilisées
		int[] usedIndexs = new int[pN];

		Set<Question> randomQuestions = new HashSet<Question>();
		boolean exits;
		int randomIndex;
		Random rand;
		do {

			// On génére aléatoreiement un nombre dans l'intevalle [0,
			// questions.size()[
			rand = new Random();
			randomIndex = rand.nextInt(questions.size());

			// Vérifier si randomIndex n'est pas utilisé
			exits = false;
			for (int it : usedIndexs) {

				if (it == randomIndex) {
					exits = true;
					break;
				}

			}

			if (!exits) {

				randomQuestions.add(questions.get(randomIndex));
				usedIndexs[count++] = randomIndex;

			}

		} while (count < pN);

		Qcm qcm = new Qcm();
		qcm.setQuestions(randomQuestions);

		return qcm;
	}

	public void saveQcm(Qcm pQcm) {

		qcmDao.create(pQcm);
	}

	public Qcm getBestScore() {
		return qcmDao.getMaxQCMScore();
	}

	public List<Question> getAllQuestions() {
		return questionDao.getAll();
	}

	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public QcmDao getQcmDao() {
		return qcmDao;
	}

	public void setQcmDao(QcmDao qcmDao) {
		this.qcmDao = qcmDao;
	}

}
