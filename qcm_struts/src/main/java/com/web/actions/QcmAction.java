package com.web.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.Qcm;
import com.bo.Question;
import com.bo.security.Utilisateur;
import com.exceptions.AuthentificationFailureException;
import com.services.QcmService;
import com.services.UtilisateurService;
import com.web.BaseAction;


@ResultPath("/private/user/")

public class QcmAction extends BaseAction implements SessionAware {

	/** Le service de la couche metier permettant la gestion des QCM */

	@Autowired
	private QcmService qcmService;

	/** Le service de la couche metier permettant la gestion des utilisateurs */

	@Autowired
	private UtilisateurService userService;

	/** Présente un QCM */
	private Qcm qcm;

	/** référence sur le meilleur qcm score */
	private Qcm bestQcm;

	/**
	 * les noms des radios boutons dans la pahe JSP
	 * (reponseName[0],reponseName[1],..)
	 */
	private int[] reponseName = new int[10];

	private Map<String, Object> session;

	/**
	 * Action de creation et d'affichage d'un QCM
	 * 
	 */
	@Action(value = "buildQcm", results = { @Result(name = "success", location = "qcm.jsp"),
			@Result(name = "error", location = "error.jsp"), })
	public String buildQcm() {

		// Générer le QCM par 8 questions aléatoirement de la base de données
		qcm = qcmService.buildQcm(8);

		// On stocke le QCM dans la session
		getSession().setAttribute("qcm", qcm);

		return SUCCESS;
	}

	/** Sauvgarde le QCM généré avec le score en base de données */
	@Action(value = "repondreQCM", results = { @Result(name = "success", location = "score.jsp"),
			@Result(name = "error", location = "error.jsp"), })
	public String repondreQCM() {

		// On récupère le QCM de la session
		qcm = (Qcm) getSession().getAttribute("qcm");

		// On clacule le score

		List<Question> lQuestions = new ArrayList<Question>(qcm.getQuestions());
		int score = 0;

		for (int i = 0; i < lQuestions.size(); i++) {

			// Si bon réponse on incrémente le score
			if (reponseName[i] == lQuestions.get(i).getReponse()) {

				score++;
			}

		}
		Utilisateur userQcm = null;
		try {
			Utilisateur user = (Utilisateur) session.get("user");

			userQcm = userService.loadUserByUsername(user.getLogin());

		} catch (AuthentificationFailureException e) {
			return ERROR;
		}

		qcm.setUtilisateur(userQcm);
		// le score
		qcm.setScore(score);
		// Date de réalisation
		qcm.setDateRea(new Date());

		// Sauvgarder le QCM en base de données
		qcmService.saveQcm(qcm);

		return SUCCESS;
	}

	@Action(value = "bestScore", results = { @Result(name = "success", location = "bestScore.jsp"),
			@Result(name = "error", location = "error.jsp"), })
	public String bestScore() {

		bestQcm = qcmService.getBestScore();
		return SUCCESS;
	}

	public QcmService getQcmService() {
		return qcmService;
	}

	public void setQcmService(QcmService qcmService) {
		this.qcmService = qcmService;
	}

	public Qcm getQcm() {

		return qcm;
	}

	public void setQcm(Qcm qcm) {
		this.qcm = qcm;
	}

	public int[] getReponseName() {
		return reponseName;
	}

	public void setReponseName(int[] reponseName) {
		this.reponseName = reponseName;
	}

	public UtilisateurService getUserService() {
		return userService;
	}

	public void setUserService(UtilisateurService userService) {
		this.userService = userService;
	}

	public Qcm getBestQcm() {
		return bestQcm;
	}

	public void setBestQcm(Qcm bestQcm) {
		this.bestQcm = bestQcm;
	}

	public void setSession(Map<String, Object> pSession) {
		session = pSession;

	}

}
