package com.decorators;

import org.displaytag.decorator.TableDecorator;

import com.bo.Destination;

public class DestinationDecorator extends TableDecorator {

	public String getAddCommentLink() {

		Destination destination = (Destination) getCurrentRowObject();
		Long idprod = destination.getId();

		return "<a href=\"commenter?id=" + idprod + "\">Ajouter un commentaire</a>";

	}

}