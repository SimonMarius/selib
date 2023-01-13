package com.Se.OnlineLibrary.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.Se.OnlineLibrary.entity.Book;
import com.Se.OnlineLibrary.entity.MyBookList;
import com.Se.OnlineLibrary.service.BookService;
import com.Se.OnlineLibrary.service.MyBookListService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	@GetMapping("/book_register")
	public String bookRegister()
	{
		return "bookRegister";
	}
	@GetMapping("/available_books")
	public ModelAndView getAllBook()
	{
		List<Book>list=bookService.getAllBook();
		//ModelAndView model = new ModelAndView();
		//model.setViewName("bookList");
		//model.addObject("book",list);
		//return model;
		return new ModelAndView("bookList","book",list);
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
		List<MyBookList>list =myBookService.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id)
	{
		Book b = bookService.getBookById(id);
		MyBookList mb = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getCategory(),b.getPublisher(),b.getPrice());
		myBookService.saveMyBooks(mb);
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
}
