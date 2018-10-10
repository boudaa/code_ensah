package com.bo;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private int id;
	private String title;
	private int numberOfPages;
	private double Price;
	private String isbn;
	private String kind;

	public List<Author> authors = new ArrayList<Author>();

	public Book() {

	}

	public void addAuthor(Author pAuthor) {
		authors.add(pAuthor);
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

}
