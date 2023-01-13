package com.Se.OnlineLibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Se.OnlineLibrary.entity.MyBookList;
import com.Se.OnlineLibrary.repository.MyBookRepository;

@Service
public class MyBookListService {
	@Autowired
	
	private  MyBookRepository mybookRepository;
	public void saveMyBooks(MyBookList book)
	{
		mybookRepository.save(book);
	}
	public List<MyBookList> getAllMyBooks()
	{
		return mybookRepository.findAll();
	}
	public void deleteById(int id)
	{
		mybookRepository.deleteById(id);
	}
}
