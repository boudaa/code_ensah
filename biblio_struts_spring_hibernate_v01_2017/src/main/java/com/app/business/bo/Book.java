package com.app.business.bo;

import java.util.Date;


/**
 * 
 * Cette classe modélise un livre
 * 
 * @author Boudaa
 *
 */
public class Book {

	private Long id;
	private String title;
	private double price;
	private String description;
	private String isbn;
	private Integer nbOfPage;
	private boolean illustrations = false;
	private Theme theme;
	
	private Date dateAchat;

	private String emailAuteur;

	public Book() {
	}

	public Book(String title, Float price, String description, String isbn,
			Integer nbOfPage, Boolean illustrations) {

		this.title = title;
		this.price = price;
		this.description = description;
		this.isbn = isbn;
		this.nbOfPage = nbOfPage;
		this.illustrations = illustrations;
	}

	public Book(String title, Float price, String description, String isbn,
			Integer nbOfPage, Boolean illustrations, Theme theme) {

		this.title = title;
		this.price = price;
		this.description = description;
		this.isbn = isbn;
		this.nbOfPage = nbOfPage;
		this.illustrations = illustrations;
		this.theme = theme;

	}

	

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price
				+ ", description=" + description + ", isbn=" + isbn
				+ ", nbOfPage=" + nbOfPage + ", illustrations=" + illustrations
				+ ", theme=" + theme + ", emailAuteur=" + emailAuteur + "]";
	}
	// Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getNbOfPage() {
		return nbOfPage;
	}

	public void setNbOfPage(Integer nbOfPage) {
		this.nbOfPage = nbOfPage;
	}

	public boolean isIllustrations() {
		return illustrations;
	}

	public void setIllustrations(boolean illustrations) {
		this.illustrations = illustrations;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public String getEmailAuteur() {
		return emailAuteur;
	}

	public void setEmailAuteur(String emailAuteur) {
		this.emailAuteur = emailAuteur;
	}

	
	

	
}
