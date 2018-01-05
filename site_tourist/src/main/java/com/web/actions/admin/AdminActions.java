package com.web.actions.admin;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.WordPolarity;
import com.opensymphony.xwork2.ActionSupport;
import com.services.SentimentAnalysis;

@ResultPath(value = "/pages/")
public class AdminActions extends ActionSupport {

	@Autowired
	private SentimentAnalysis saService;

	private WordPolarity wordPolarity;

	@Action(value = "/addWord", results = { @Result(name = "success", location = "addWordForm.jsp") })
	public String addWord() {

		saService.addWord(wordPolarity);

		addActionMessage("Mot correctement ajouté");

		return SUCCESS;

	}

	public WordPolarity getWordPolarity() {
		return wordPolarity;
	}

	public void setWordPolarity(WordPolarity wordPolarity) {
		this.wordPolarity = wordPolarity;
	}

}
