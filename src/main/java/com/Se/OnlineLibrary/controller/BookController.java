package com.Se.OnlineLibrary.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.Se.OnlineLibrary.entity.User;
import com.Se.OnlineLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.Se.OnlineLibrary.entity.Book;
import com.Se.OnlineLibrary.service.BookService;


@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;

	@GetMapping("/book_register")
	public String bookRegister()
	{
		return "bookRegister";
	}
	@GetMapping("/books")
	public ModelAndView getAllBook()
	{
		List<Book>list=bookService.getAllBook();
		return new ModelAndView("bookList","book",list);
	}
	@GetMapping("/available_books")
	public ModelAndView getAvailableBook()
	{
		List<Book>list=bookService.getAllBook()
			.stream()
			.filter(book -> book.getBorrowingUser() == null).collect(Collectors.toList());
		return new ModelAndView("bookList","book", list);
	}
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book book)
	{
		bookService.save(book);
		return "redirect:/available_books";
	}
	@GetMapping("/my_books")
	public String getMyBooks(Model model)
	{
		User user = getLoggedInUser();
		Collection<Book> list = user.getBorrowedBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id)
	{
		return "redirect:/my_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model)
	{
		Book b = bookService.getBookById(id);
		model.addAttribute("book",b);
		return"bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id)
	{
		bookService.deleteById(id);
		return "redirect:/available_books";
	}
	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id)
	{
		Book book = bookService.getBookById(id);
		book.setBorrowingUser(null);
		bookService.save(book);
		return "redirect:/my_books";
	}

	@RequestMapping("/borrow/{id}")
	public String borrow(@PathVariable("id") int id)
	{
		Book book = bookService.getBookById(id);
		User user = getLoggedInUser();
		book.setBorrowingUser(user);
		bookService.save(book);
		return "redirect:/my_books";
	}

	private User getLoggedInUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.findByUsername(userDetails.getUsername());
	}
}
