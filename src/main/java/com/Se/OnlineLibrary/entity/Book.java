package com.Se.OnlineLibrary.entity;

import javax.persistence.*;
import javax.persistence.Entity;


@Entity
public class Book extends com.Se.OnlineLibrary.entity.Entity {
	private String name;
	private String author;
	private String price;
	private String category;
	private String publisher;

	@ManyToOne
	private User borrowingUser;

	public User getBorrowingUser() {
		return borrowingUser;
	}

	public void setBorrowingUser(User borrowingUser) {
		this.borrowingUser = borrowingUser;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
}
