package com.Se.OnlineLibrary;


import java.util.UUID;

import com.Se.OnlineLibrary.entity.Book;
import com.Se.OnlineLibrary.entity.User;
import com.Se.OnlineLibrary.service.BookService;
import com.Se.OnlineLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		User admin = getAdmin();
		admin = userService.save(admin);
		userService.save(getNormal());

		for (int i = 0; i < 4; i++) {
			Book book = getRandomBook();
			book.setBorrowingUser(admin);
			bookService.save(book);
		}

		for (int i = 0; i < 4; i++) {
			Book book = getRandomBook();
			bookService.save(book);
		}
	}

	private Book getRandomBook() {
		Book book = new Book();
		book.setAuthor(UUID.randomUUID().toString());
		book.setCategory(UUID.randomUUID().toString());
		book.setName(UUID.randomUUID().toString());
		book.setPrice(UUID.randomUUID().toString());
		book.setPublisher(UUID.randomUUID().toString());

		return book;
	}

	private User getAdmin() {
		User admin = new User();
		admin.setUsername("Marius");
		admin.setPassword("Marius");
		admin.setAdmin(true);
		return admin;
	}

	private User getNormal() {
		User normal = new User();
		normal.setUsername("Cret");
		normal.setPassword("Cret");
		return normal;
	}
}
