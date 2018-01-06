package com.web.actions.users;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.Commentaire;
import com.bo.Destination;
import com.bo.WordPolarity;
import com.opensymphony.xwork2.ActionSupport;
import com.services.DestinationService;
import com.services.SentimentAnalysis;
import com.web.actions.BaseAction;

@ResultPath(value = "/pages/")
public class UserAction extends BaseAction {

	@Autowired
	private DestinationService destinationService;

	private List<Destination> destinations;

	private Destination destination;

	private Commentaire userComment;

	@Action(value = "/addDestination", results = { @Result(name = "success", location = "addDestinationForm.jsp") })
	public String addDestination() {

		destinationService.addDestination(destination);

		addActionMessage("Destination correctement ajoutée");

		return SUCCESS;

	}

	@Actions({
	@Action(value = "/", results = { @Result(name = "success", location = "listDistinations.jsp") }),

	@Action(value = "/getAllDestinations", results = { @Result(name = "success", location = "listDistinations.jsp") })

	})

	public String getAllDestinationss() {

		destinations = destinationService.getAllDestinations();

		return SUCCESS;

	}

	@Action(value = "/commenter", results = { @Result(name = "success", location = "addCommentForm.jsp")

	})
	public String showAddCommentForm() {

		// On récupère l'id de la destination

		getSession().setAttribute("idDestination", Long.valueOf(getRequest().getParameter("id")));

		return SUCCESS;

	}

	@Action(value = "/addComment", results = {
			@Result(name = "success", type = "redirectAction", location = "getAllDestinations")

	})
	public String addComment() {

		// On récupère de la session la destination

		Long id = (Long) getSession().getAttribute("idDestination");

		// TODO : si destination est introuvable
		Destination destination = destinationService.getDestinationById(id);

		destination.addCommentaire(userComment);

		destinationService.updateDestination(destination);

		return SUCCESS;

	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public List<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}

	public Commentaire getUserComment() {
		return userComment;
	}

	public void setUserComment(Commentaire userComment) {
		this.userComment = userComment;
	}

}