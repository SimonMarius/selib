package com.Se.OnlineLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Se.OnlineLibrary.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

}
