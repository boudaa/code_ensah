package com.web.decorators;

import org.displaytag.decorator.TableDecorator;

import com.app.business.bo.Book;

public class BookActionDecorator extends TableDecorator {

	private String deleteBookLink;

	private String modifyBookLink;

	public String getDeleteBookLink() {

		Book book = (Book) getCurrentRowObject();
		Long idbook = book.getId();

		this.deleteBookLink = "<a href=\"deleteBook?idbook=" + idbook
				+ "\">Supprimer</a>";

		return this.deleteBookLink;
	}

	public String getModifyBookLink() {
		Book book = (Book) getCurrentRowObject();
		Long idbook = book.getId();

		this.modifyBookLink = "<a href=\"showFormUpdate?idbook=" + idbook
				+ "\">modifier</a>";
		return modifyBookLink;
	}

}