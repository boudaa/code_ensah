package com.web.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.Qcm;
import com.bo.Question;
import com.bo.security.Utilisateur;
import com.exceptions.AuthentificationFailureException;
import com.services.QcmService;
import com.services.UtilisateurService;
import com.web.BaseAction;

// Pour les actions de cette classe on les intercepte avec la pile d'intecepteur
// userStack définit dans le fichier strust.xml
// cette pile permet d'authentifier l'utilisateur et de vérifier ses droit
// En effet l'admin ne peut pas executer les actions de cette classe car
// QcmUserInterceptor va l'interdire d'accéder

@InterceptorRef(value = "userStack")

// path des vues de cette action
@ResultPath("/private/user/")

// Résultat global partagé par toutes les méthodes de cette classes
@Results({ @Result(name = "login", location = "/public/login.jsp"),

})

/**
 * Cette classe implémente les actions liées au jeu QCM
 * 
 * 
 * @author Tarik BOUDAA
 *
 */
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

		// On stocke le résultat du QCM dans la base de données en appelant le
		// service métier
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

		// On appel le service métier pour récupérer le best score de tous les
		// joueurs
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
