package com.Se.OnlineLibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Se.OnlineLibrary.entity.Book;
import com.Se.OnlineLibrary.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired UserService userService;
	public Book save(Book book)
	{
		return bookRepository.save(book);
	}
	
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}
	
	public Book getBookById(long id)
	{
		return bookRepository.findById(id).get();
	}
	public void deleteById(long id)
	{
		Book book = bookRepository.findById(id).get();
		if (book.getBorrowingUser() != null) {
			book.setBorrowingUser(null);
			bookRepository.save(book);
		}
		bookRepository.deleteById(id);
	}
}
