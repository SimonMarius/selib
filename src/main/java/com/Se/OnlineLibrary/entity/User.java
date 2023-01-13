package com.Se.OnlineLibrary.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@javax.persistence.Entity
@Table(name = "applicationuser")
public class User extends Entity {
	private String username;
	private String password;
	private String passwordConfirm;
	private String email;
	@OneToMany(mappedBy = "borrowingUser")
	private Collection<Book> borrowedBooks;
	private boolean isAdmin;

	public User() {
		this.borrowedBooks = new ArrayList<>();
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Book> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(Collection<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}
}
